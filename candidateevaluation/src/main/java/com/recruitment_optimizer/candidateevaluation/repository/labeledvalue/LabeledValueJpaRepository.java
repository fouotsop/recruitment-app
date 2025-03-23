package com.recruitment_optimizer.candidateevaluation.repository.labeledvalue;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruitment_optimizer.candidateevaluation.model.LabeledValue;

public interface LabeledValueJpaRepository extends JpaRepository<LabeledValue, String> {
    
}
