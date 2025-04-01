package com.recruitment_optimizer.candidateevaluation.repository.criterion.categorical;

import org.springframework.stereotype.Repository;

import com.recruitment_optimizer.candidateevaluation.model.CategoricalCriterion;

@Repository
public interface CategoricalCriterionRepository {
    
    public CategoricalCriterion findById (String id);

    public CategoricalCriterion fetchById(String id);

}
