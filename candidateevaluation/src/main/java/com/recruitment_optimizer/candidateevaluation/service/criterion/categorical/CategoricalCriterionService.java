package com.recruitment_optimizer.candidateevaluation.service.criterion.categorical;

import org.springframework.stereotype.Service;

import com.recruitment_optimizer.candidateevaluation.dto.model.CriterionDto;
import com.recruitment_optimizer.candidateevaluation.model.LabeledValue;

@Service
public interface CategoricalCriterionService {
    
    public CriterionDto fetchById(String id);
    
    public LabeledValue addLabeledValue(LabeledValue labeledValue);
}
