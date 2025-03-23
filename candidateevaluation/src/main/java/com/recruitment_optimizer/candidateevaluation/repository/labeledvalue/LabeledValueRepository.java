package com.recruitment_optimizer.candidateevaluation.repository.labeledvalue;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.recruitment_optimizer.candidateevaluation.model.LabeledValue;

@Repository
public interface LabeledValueRepository {

    public LabeledValue save(LabeledValue labeledValue);

    public LabeledValue findById(String id);

    public Page<LabeledValue> findAll(Pageable pageable);
    
}
