from flask import Flask, request, jsonify
import traceback
import sys
import numpy as np
import random
from typing import List, Dict, Callable, Tuple
from collections import OrderedDict
from sklearn.cluster import KMeans
import math
import psycopg2
from psycopg2 import sql
from config import DB_CONFIG  # Configuration de la base de données

app = Flask(__name__)



def get_db_connection():
    """
    Establishes a PostgreSQL connection to Render.com using psycopg2.
    Uses the full connection URI for simplicity.
    """
    try:
        conn = psycopg2.connect(
            DB_CONFIG['dsn'],
            connect_timeout=DB_CONFIG['connect_timeout']
        )
        return conn
    except OperationalError as e:
        raise OperationalError(f"Failed to connect to PostgreSQL (Render.com): {e}")

# Fetch recruitment data from the database and initialize parameters
def fetch_recruitment_data(recruitment_id: str) -> Dict:
    """Récupère toutes les données nécessaires pour le recrutement"""
    conn = get_db_connection()
    cursor = conn.cursor()
    
    try:
        # 1. Récupérer les informations de base du recrutement
        cursor.execute("""
            SELECT id, title, description, number_of_posts, salary, location 
            FROM recruitment 
            WHERE id = %s AND available = TRUE
        """, (recruitment_id,))
        recruitment = cursor.fetchone()
        
        if not recruitment:
            raise ValueError("Recrutement non trouvé ou non disponible")
        
        recruitment_data = {
            'id': recruitment[0],
            'title': recruitment[1],
            'description': recruitment[2],
            'number_of_posts': recruitment[3],
            'salary_budget': float(recruitment[4]),
            'location': recruitment[5]
        }
        
        # 2. Récupérer les critères et leurs poids
        cursor.execute("""
            SELECT c.id, c.name, c.description, c.mandatory, rc.weight, rc.threshold
            FROM recruitment_criterion rc
            JOIN criterion c ON rc.criterion_id = c.id
            WHERE rc.recruitment_id = %s
            ORDER BY rc.weight DESC
        """, (recruitment_id,))
        
        criteria = []
        for row in cursor.fetchall():
            criterion = {
                'id': row[0],
                'name': row[1],
                'description': row[2],
                'mandatory': row[3],
                'weight': float(row[4]),
                'threshold': float(row[5]) if row[5] is not None else None
            }
            
            # Vérifier si c'est un critère catégoriel ou numérique
            cursor.execute("SELECT 1 FROM categorical_criterion WHERE id = %s", (row[0],))
            if cursor.fetchone():
                criterion['type'] = 'categorical'
                # Récupérer les valeurs étiquetées
                cursor.execute("""
                    SELECT label, value FROM labeled_value 
                    WHERE categorical_criterion_id = %s
                    ORDER BY value DESC
                """, (row[0],))
                criterion['labeled_values'] = {label: float(value) for label, value in cursor.fetchall()}
            else:
                criterion['type'] = 'numeric'
                cursor.execute("SELECT min_value, max_value FROM numeric_criterion WHERE id = %s", (row[0],))
                min_max = cursor.fetchone()
                criterion['min_value'] = float(min_max[0]) if min_max[0] is not None else None
                criterion['max_value'] = float(min_max[1]) if min_max[1] is not None else None
            
            criteria.append(criterion)
        
        recruitment_data['criteria'] = criteria
        
        # 3. Récupérer les candidats et leurs scores
        cursor.execute("""
            SELECT c.id, c.name, c.phone, 
                   c.location, c.genre, a.ahp_score
            FROM application a
            JOIN candidate c ON a.candidate_id = c.id
            WHERE a.recruitment_id = %s
        """, (recruitment_id,))
        
        candidates = []
        for row in cursor.fetchall():
            candidate = {
                'id': row[0],
                'name': row[1],
                'phone': row[2],
                'location': row[3],
                'genre': row[4],
                'ahp_score': float(row[8]) if row[8] is not None else 0.0
            }
            
            # Récupérer les valeurs des critères pour ce candidat
            cursor.execute("""
                SELECT cv.criterion_id, cv.value
                FROM criterion_value cv
                WHERE cv.application_candidate_id = %s 
                AND cv.application_recruitment_id = %s
            """, (row[0], recruitment_id))
            
            candidate['criteria_values'] = {criterion_id: float(value) 
                                          for criterion_id, value in cursor.fetchall()}
            candidates.append(candidate)
        
        recruitment_data['candidates'] = candidates
        
        return recruitment_data
    
    finally:
        cursor.close()
        conn.close()

def education_constraint(individual: List[int], candidates: List[Dict], min_education: int, penalty: float) -> float:
    education_criteria = [c['criteria_values'].get('education', 0) for c in candidates]
    total = sum(education_criteria[i] for i in individual)
    return max(0, min_education - total) * penalty


def education_score(candidate: dict, threshold: int, criterion: str) -> float:
    """Calculates the education score for a candidate based on a threshold."""
    value = candidate['criteria_values'].get(criterion)
    return math.tanh(value - threshold)




def skills_constraint(individual: List[int], candidates: List[Dict], min_skills: int, penalty: float) -> float:
    skills_criteria = [c['criteria_values'].get('skills', 0) for c in candidates]
    total = sum(skills_criteria[i] for i in individual)
    return max(0, min_skills - total) * penalty

def skills_score(candidate: dict, criterion: str, threshold: float, max: float) -> float:
    """Calculates the skills score for a candidate based on a threshold."""
    value = candidate['criteria_values'].get(criterion)
    if value < threshold:
        return 0
    elif threshold <= value <= max:
        return math.exp((value - threshold)) - 1
    else:
        return math.exp((max - threshold)) - 1



def experience_constraint(individual: List[int], candidates: List[Dict], min_experience: int, penalty: float) -> float:
    exp_criteria = [c['criteria_values'].get('experience', 0) for c in candidates]
    total = sum(exp_criteria[i] for i in individual)
    return max(0, min_experience - total) * penalty

def experience_score(candidate: dict, criterion: str, threshold: float, max: float) -> float:
    """Calculates the experience score for a candidate based on a threshold."""
    value = candidate['criteria_values'].get(criterion)
    if value < threshold:
        return 0
    elif threshold <= value <= max:
        return math.exp((value - threshold)) - 1
    else:
        return math.exp((max - threshold)) - 1




def budget_constraint(individual: List[int], candidates: List[Dict], max_budget: float, penalty: float) -> float:
    salary_criteria = [c['criteria_values'].get('salary', 0) for c in candidates]
    total = sum(salary_criteria[i] for i in individual)
    return max(0, total - max_budget) * penalty

def budget_score(candidate: dict, criteria_id: str, threshold: float) -> float:
    """Calculates the budget score for a candidate based on a threshold."""
    value = candidate['criteria_values'].get(criteria_id)
    return math.tanh(value - threshold)






def diversity_constraint(individual: List[int], candidates: List[Dict], min_female_ratio: float, penalty: float) -> float:
    if len(individual) == 1:
        return 0
    female_count = sum(1 for i in individual if candidates[i]['sex'] == 'female')
    return max(0, min_female_ratio - (female_count/len(individual))) * penalty

def diversity_score(individual: List[Dict], number_of_posts:int, male_ratio: float) -> float:
    """Calculates the diversity score for a candidate based on a threshold"""

    def men_count(individual: List[Dict]) -> int:
        count = 0
        for candidate in individual:
            if candidate['genre'] == 'MALE':
                count += 1
        return count

    gs = men_count(individual)/ number_of_posts

    if gs < male_ratio:
        return gs/male_ratio
    else:
        return (1-gs)/(1-male_ratio)




def region_constraint(individual: List[int], candidates: List[Dict], min_regions: int, penalty: float) -> float:
    if len(individual) == 1:
        return 0
    regions = len(set(candidates[i]['location'] for i in individual))
    return max(0, min_regions - regions) * penalty


def region_score(individual: List[Dict], region_criteria: Dict, number_of_posts:int, max_size: int) -> float:
    """Calculates the region score for a candidate based on a threshold"""

    candidate_locations = [individual.get['location'] for individual in individual]
    regions_involved = []
    for region in region_criteria['labeled_values']:
        if region in candidate_locations:
            count = candidate_locations.count(value)
            regions_involved.append({region: count})


    cappeds = []
    for region in regions_involved:
        for region, count in region.items():
            cappeds.append({region: min(count, max_size)})
    
    total_capped = sum(capped.values())

    proportions = []
    for region, capped in cappeds.items():
        proportions.append({region: capped/total_capped})

    value = (-1*sum([proportions.get(pr)*math.log(proportions.get(pr)) for pr in proportions]))/math.log(len(region_criteria['labeled_values'].items()))

    return value

def fitness_score(individual: List[Dict], criteria: List[Dict], number_of_posts:int) -> float:
    """Calculates the fitness score for an individual based on the criteria values"""

    education_criteria = next((c for c in criteria if c['name'] == 'education'), None)
    experience_criteria = next((c for c in criteria if c['name'] == 'years'), None)
    skills_criteria = next((c for c in criteria if c['name'] == 'skill'), None)
    region_criteria = next((c for c in criteria if c['name'] == 'region'), None)
    salary_criteria = next((c for c in criteria if c['name'] == 'salary'), None)    

    e_score = education_score(sum(individual['ahp_score']*[education_score(individual, 3, education_criteria['id']) for candidate in individual]))
    s_score = skills_score(sum([individual['ahp_score']*skills_score(individual, skills_criteria['id'], skills_criteria['threshold'], skills_criteria['max_value']) for candidate in individual]))
    y_score = experience_score(sum([individual['ahp_score']*experience_score(individual, experience_criteria['id'], experience_criteria['threshold'], experience_criteria['max_value']) for candidate in individual]))
    b_score = budget_score(sum(individual['ahp_score']*[budget_score(individual, salary_criteria['id'], salary_criteria['threshold']) for candidate in individual]))
    r_score = region_score(individual, region_criteria, number_of_posts, region_criteria['threshold'])
    d_score = diversity_score(individual, number_of_posts, 0.5)

    f_score = e_score + s_score + y_score + b_score + r_score + d_score

    return f_score






def calculate_fitness(
    individual: List[int],
    candidates: List[Dict],
    constraint_funcs: List[Callable],
    constraint_params: List[Dict]
) -> float:
    ahp_scores = sum(candidates[i]['ahp_score'] for i in individual)
    
    total_penalty = 0
    for func, params in zip(constraint_funcs, constraint_params):
        total_penalty += func(individual, candidates, **params)
    
    return ahp_scores - total_penalty

def genetic_algorithm(
    recruitment_data: Dict,
    population_size: int = 100,
    generations: int = 100,
    crossover_rate: float = 0.85,
    mutation_rate: float = 0.15,
    elitism_count: int = 2
) -> Tuple[List[int], List[float]]:
    
    candidates = recruitment_data['candidates']
    num_to_select = recruitment_data['number_of_posts']
    
    # Configuration des contraintes basées sur les critères
    constraint_funcs = [
        education_constraint,
        skills_constraint,
        experience_constraint,
        budget_constraint,e
        diversity_constraint,
        region_constraint
    ]
    
    constraint_params = [
        {'min_education': 15, 'penalty': 10},  # Valeurs par défaut, à ajuster
        {'min_skills': 25, 'penalty': 8},
        {'min_experience': 15, 'penalty': 7},
        {'max_budget': recruitment_data['salary_budget'], 'penalty': 5},
        {'min_female_ratio': 0.3, 'penalty': 15},
        {'min_regions': 4, 'penalty': 12}
    ]

    # Initialisation avec K-means clustering
    def initialize():
        if len(candidates) <= num_to_select:
            return [list(range(len(candidates)))]
            
        features = np.array([[c['ahp_score'], 
                            c['criteria_values'].get('education', 0),
                            c['criteria_values'].get('experience', 0)] 
                           for c in candidates])
        
        max_clusters = min(population_size // num_to_select, len(candidates))
        kmeans = KMeans(n_clusters=max_clusters, random_state=42)
        kmeans.fit(features)
        labels = kmeans.labels_

        population = []
        for cluster in range(kmeans.n_clusters):
            cluster_indices = np.where(labels == cluster)[0]
            if len(cluster_indices) >= num_to_select:
                selected = random.sample(cluster_indices.tolist(), num_to_select)
                population.append(selected)
        
        # Compléter si nécessaire avec des sélections aléatoires
        while len(population) < population_size:
            population.append(random.sample(range(len(candidates)), num_to_select))
        
        return population[:population_size]

    def select(population, fitnesses):
        tournament = random.sample(range(len(population)), min(5, len(population)))
        return population[max(tournament, key=lambda i: fitnesses[i])]

    def crossover(parent1, parent2):
        if len(parent1) == 1:
            return parent1.copy(), parent2.copy()
        if random.random() < crossover_rate:
            point = random.randint(1, len(parent1)-1)
            return parent1[:point] + parent2[point:], parent2[:point] + parent1[point:]
        return parent1, parent2

    def mutate(individual):
        if random.random() < mutation_rate:
            idx = random.randint(0, len(individual)-1)
            available = [i for i in range(len(candidates)) if i not in individual]
            if available:
                individual[idx] = random.choice(available)
        return individual

    population = initialize()
    best_fitness_history = []
    
    for generation in range(generations):
        fitnesses = [calculate_fitness(ind, candidates, constraint_funcs, constraint_params) 
                    for ind in population]
        
        best_fitness = max(fitnesses)
        best_fitness_history.append(best_fitness)
        
        new_population = [
            population[i] for i in np.argsort(fitnesses)[-elitism_count:]
        ]
        
        while len(new_population) < population_size:
            parent1 = select(population, fitnesses)
            parent2 = select(population, fitnesses)
            child1, child2 = crossover(parent1, parent2)
            new_population.extend([mutate(child1), mutate(child2)])
        
        population = new_population[:population_size]
    
    fitnesses = [calculate_fitness(ind, candidates, constraint_funcs, constraint_params) 
                for ind in population]
    best_index = np.argmax(fitnesses)
    
    return population[best_index], best_fitness_history

@app.route('/optimize/<recruitment_id>', methods=['GET'])
def optimize(recruitment_id: str):
    try:
        # 1. Récupérer les données depuis la base
        recruitment_data = fetch_recruitment_data(recruitment_id)
        
        if not recruitment_data.get('candidates'):
            return jsonify({
                'error': 'Aucun candidat trouvé pour ce recrutement',
                'recruitment_id': recruitment_id
            }), 404
        
        # 2. Exécuter l'algorithme génétique
        selected_indices, fitness_history = genetic_algorithm(
            recruitment_data=recruitment_data,
            population_size=min(100, len(recruitment_data['candidates'])),
            generations=100
        )
        
        # 3. Préparer la réponse
        selected_candidates = [recruitment_data['candidates'][i] for i in selected_indices]
        
        response = OrderedDict([
            ('recruitment_id', recruitment_data['id']),
            ('recruitment_title', recruitment_data['title']),
            ('number_of_posts', recruitment_data['number_of_posts']),
            ('optimal_team', [c['id'] for c in selected_candidates]),
            ('fitness_score', float(fitness_history[-1])),
            ('total_ahp', round(sum(c['ahp_score'] for c in selected_candidates), 3)),
            ('constraint_checks', OrderedDict([
                ('total_education', round(sum(c['criteria_values'].get('education', 0) 
                                          for c in selected_candidates), 2)),
                ('total_skills', round(sum(c['criteria_values'].get('skills', 0) 
                                       for c in selected_candidates), 2)),
                ('total_experience', round(sum(c['criteria_values'].get('experience', 0) 
                                         for c in selected_candidates), 2)),
                ('total_cost', round(sum(c['criteria_values'].get('salary', 0) 
                                   for c in selected_candidates), 2)),
                ('regions', len(set(c['location'] for c in selected_candidates))),
                ('gender_ratio', round(
                    sum(1 for c in selected_candidates if c['sex'] == 'female') / 
                    len(selected_candidates), 2))
            ])),
            ('num_generations', len(fitness_history))
        ])
        
        return jsonify(response)
    
    except Exception as e:
        exc_type, exc_value, exc_traceback = sys.exc_info()
        error_details = {
            'error': str(e),
            'type': str(exc_type.__name__),
            'recruitment_id': recruitment_id,
            'traceback': traceback.format_exc()
        }
        return jsonify(error_details), 500

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)