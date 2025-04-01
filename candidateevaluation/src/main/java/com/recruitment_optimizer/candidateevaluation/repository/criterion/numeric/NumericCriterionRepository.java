package com.recruitment_optimizer.candidateevaluation.repository.criterion.numeric;

import org.springframework.stereotype.Repository;

import com.recruitment_optimizer.candidateevaluation.model.NumericCriterion;

@Repository
public interface NumericCriterionRepository {
    
    public NumericCriterion findById(String id);

    public NumericCriterion save(NumericCriterion criterion);

    public NumericCriterion getByID(String id);
}
