package com.recruitment_optimizer.candidateevaluation.repository.criterion.categorical;

import org.springframework.stereotype.Repository;

import com.recruitment_optimizer.candidateevaluation.model.CategoricalCriterion;

import jakarta.persistence.EntityNotFoundException;

@Repository
public class CategoricalCriterionRepositoryImpl implements CategoricalCriterionRepository {
    
    private final CategoricalCriterionJpaRepository repository;

    public CategoricalCriterionRepositoryImpl(CategoricalCriterionJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public CategoricalCriterion findById(String id) {
        return this.repository.findById(id).orElseThrow();
    }

    @Override
    public CategoricalCriterion fetchById(String id) {
    CategoricalCriterion criterion = this.repository.fetchById(id);
    if (criterion == null) throw new EntityNotFoundException();

    return criterion;
    }
    
}
