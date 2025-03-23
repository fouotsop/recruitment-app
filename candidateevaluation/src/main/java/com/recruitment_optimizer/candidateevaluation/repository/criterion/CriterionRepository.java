package com.recruitment_optimizer.candidateevaluation.repository.criterion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.recruitment_optimizer.candidateevaluation.model.Criterion;

@Repository
public interface CriterionRepository {
    
    public Criterion save(Criterion criterion);

    public Criterion findById(String id);

    public Page<Criterion> findAll(Pageable pageable);  
}
