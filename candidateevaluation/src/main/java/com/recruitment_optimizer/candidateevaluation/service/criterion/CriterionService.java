package com.recruitment_optimizer.candidateevaluation.service.criterion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.recruitment_optimizer.candidateevaluation.model.Criterion;

@Service
public interface CriterionService {
    
    public Criterion create(Criterion criterion);

    public Criterion update(Criterion criterion);

    public Page<Criterion> findAll(Pageable pageable);

}
