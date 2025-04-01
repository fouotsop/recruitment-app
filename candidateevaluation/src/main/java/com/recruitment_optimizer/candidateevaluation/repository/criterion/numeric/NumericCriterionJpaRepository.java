package com.recruitment_optimizer.candidateevaluation.repository.criterion.numeric;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruitment_optimizer.candidateevaluation.model.NumericCriterion;

public interface NumericCriterionJpaRepository extends JpaRepository<NumericCriterion, String> {
    
}
