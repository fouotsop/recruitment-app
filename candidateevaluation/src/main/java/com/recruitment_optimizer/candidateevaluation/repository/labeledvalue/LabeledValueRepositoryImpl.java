package com.recruitment_optimizer.candidateevaluation.repository.labeledvalue;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.recruitment_optimizer.candidateevaluation.model.LabeledValue;

@Repository
public class LabeledValueRepositoryImpl implements LabeledValueRepository {

    private final LabeledValueJpaRepository labeledValueJpaRepository;

    public LabeledValueRepositoryImpl(LabeledValueJpaRepository labeledValueJpaRepository) {
        this.labeledValueJpaRepository = labeledValueJpaRepository;
    }

    @Override
    public LabeledValue save(LabeledValue labeledValue) {
        return labeledValueJpaRepository.save(labeledValue);
    }

    @Override
    public LabeledValue findById(String id) {
        return labeledValueJpaRepository.findById(id).orElse(null);
    }

    @Override
    public Page<LabeledValue> findAll(Pageable pageable) {
        return labeledValueJpaRepository.findAll(pageable);
    }   
    
}
