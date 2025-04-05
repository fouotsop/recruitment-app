package com.recruitment_optimizer.candidateevaluation.repository.criterion.categorical;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.recruitment_optimizer.candidateevaluation.model.CategoricalCriterion;

public interface CategoricalCriterionJpaRepository extends JpaRepository<CategoricalCriterion, String> {

    @EntityGraph(attributePaths = {"labeledValues"})
    @Query(value = "SELECT c FROM CategoricalCriterion c WHERE c.id = ?1")
    CategoricalCriterion fetchById(String id);
    
}
