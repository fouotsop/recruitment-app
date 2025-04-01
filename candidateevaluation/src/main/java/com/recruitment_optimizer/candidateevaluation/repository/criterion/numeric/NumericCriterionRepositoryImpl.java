package com.recruitment_optimizer.candidateevaluation.repository.criterion.numeric;

import org.springframework.stereotype.Repository;

import com.recruitment_optimizer.candidateevaluation.model.NumericCriterion;

@Repository
public class NumericCriterionRepositoryImpl implements NumericCriterionRepository {

    private final NumericCriterionJpaRepository dataSource;

    public NumericCriterionRepositoryImpl(NumericCriterionJpaRepository dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public NumericCriterion findById(String id) {
        return dataSource.findById(id).orElseThrow();
    }

    @Override
    public NumericCriterion save(NumericCriterion criterion) {
        return dataSource.save(criterion);
    }

    @Override
    public NumericCriterion getByID(String id) {
        return null; // Placeholder for actual implementation
    }
    
}
