from flask import Flask, request, jsonify
import traceback
import sys
import numpy as np
import random
from typing import List, Dict, Callable, Tuple
from collections import OrderedDict

app = Flask(__name__)

def education_constraint(individual: List[int], candidates: List[Dict], min_education: int, penalty: float) -> float:
    total = sum(candidates[i]['education'] for i in individual)
    return max(0, min_education - total) * penalty

def skills_constraint(individual: List[int], candidates: List[Dict], min_skills: int, penalty: float) -> float:
    total = sum(candidates[i]['skills'] for i in individual)
    return max(0, min_skills - total) * penalty

def experience_constraint(individual: List[int], candidates: List[Dict], min_experience: int, penalty: float) -> float:
    total = sum(candidates[i]['experience'] for i in individual)
    return max(0, min_experience - total) * penalty

def budget_constraint(individual: List[int], candidates: List[Dict], max_budget: float, penalty: float) -> float:
    total = sum(candidates[i]['salary'] for i in individual)
    return max(0, total - max_budget) * penalty

def diversity_constraint(individual: List[int], candidates: List[Dict], min_female_ratio: float, penalty: float) -> float:
    if len(individual) == 1:
        return 0
    female_count = sum(1 for i in individual if candidates[i]['gender'] == 'female')
    return max(0, min_female_ratio - (female_count/len(individual))) * penalty

def region_constraint(individual: List[int], candidates: List[Dict], min_regions: int, penalty: float) -> float:
    if len(individual) == 1:
        return 0
    regions = len(set(candidates[i]['region'] for i in individual))
    return max(0, min_regions - regions) * penalty

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
    candidates: List[Dict],
    num_to_select: int,
    constraint_funcs: List[Callable],
    constraint_params: List[Dict],
    population_size: int = 100,
    generations: int = 100,
    crossover_rate: float = 0.85,
    mutation_rate: float = 0.15,
    elitism_count: int = 2
) -> Tuple[List[int], List[float]]:
    
    def initialize():
        return [random.sample(range(len(candidates)), num_to_select) 
                for _ in range(population_size)]

    def select(population, fitnesses):
        tournament = random.sample(range(len(population)), 5)
        return population[max(tournament, key=lambda i: fitnesses[i])]

    def crossover(parent1, parent2):
        if len(parent1) == 1:
            return parent1.copy(), parent2.copy()
        if random.random() < crossover_rate:
            point = random.randint(1, len(parent1)-1)
            return parent1[:point] + parent2[point:], parent2[:point] + parent1[point:]
        return parent1, parent2

    def mutate(individual):
        if len(individual) == 1:
            return individual
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

# ===================== API ENDPOINT =====================
@app.route('/optimize', methods=['POST'])
def optimize():
    try:
        data = request.json
        
   
        if 'candidates' not in data or 'num_to_select' not in data:
            return jsonify({'error': 'Missing required fields'}), 400
        
        candidates = data['candidates']
        num_to_select = int(data['num_to_select'])

        if num_to_select > len(candidates):
            return jsonify({
                'error': f"Cannot select {num_to_select} candidates from {len(candidates)} available",
                'suggestion': f"Set num_to_select to {len(candidates)} or less"
            }), 400
        #print(f"nb to select: {len(candidates):,} XAF")
        
        
        constraint_funcs = [
            education_constraint,
            skills_constraint,
            experience_constraint,
            budget_constraint,
            diversity_constraint,
            region_constraint
        ]
        
        constraint_params = [
            {'min_education': int(data.get('min_education', 15)), 'penalty': 10},
            {'min_skills': int(data.get('min_skills', 25)), 'penalty': 8},
            {'min_experience': int(data.get('min_experience', 15)), 'penalty': 7},
            {'max_budget': float(data.get('max_budget', 2500000)), 'penalty': 5},
            {'min_female_ratio': float(data.get('min_female_ratio', 0.3)), 'penalty': 15},
            {'min_regions': int(data.get('min_regions', 4)), 'penalty': 12}
        ]

        # Run GA
        selected_indices, fitness_history = genetic_algorithm(
            candidates=candidates,
            num_to_select=num_to_select,
            constraint_funcs=constraint_funcs,
            constraint_params=constraint_params,
            population_size=int(data.get('population_size', 100)),
            generations=int(data.get('generations', 100)),
            crossover_rate=float(data.get('crossover_rate', 0.85)),
            mutation_rate=float(data.get('mutation_rate', 0.15)),
            elitism_count=int(data.get('elitism_count', 2)))
        

        
        selected_candidates = [candidates[i] for i in selected_indices]
        
        response = OrderedDict([
                ('optimal_team', list(map(lambda c: c["id"], selected_candidates))),
                ('fitness_score', float(fitness_history[-1])),
                ('total_ahp', round(sum(c['ahp_score'] for c in selected_candidates), 3)),
                ('constraint_checks', OrderedDict([
                    ('total_education', sum(c['education'] for c in selected_candidates)),
                    ('total_skills', sum(c['skills'] for c in selected_candidates)),
                    ('total_experience', sum(c['experience'] for c in selected_candidates)),
                    ('total_cost', sum(c['salary'] for c in selected_candidates)),
                    ('regions', len(set(c['region'] for c in selected_candidates))),
                    ('gender_ratio', round(
                        sum(1 for c in selected_candidates if c['gender'] == 'female') / 
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
            'line_number': exc_traceback.tb_lineno,
            'file': exc_traceback.tb_frame.f_code.co_filename,
            'traceback': traceback.format_exc()
        }
        return jsonify(error_details), 500


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)