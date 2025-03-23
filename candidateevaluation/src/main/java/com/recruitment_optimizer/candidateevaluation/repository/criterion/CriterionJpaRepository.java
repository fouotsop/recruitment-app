package com.recruitment_optimizer.candidateevaluation.repository.criterion;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruitment_optimizer.candidateevaluation.model.Criterion;

public interface CriterionJpaRepository extends JpaRepository<Criterion, String> {  
    
}
