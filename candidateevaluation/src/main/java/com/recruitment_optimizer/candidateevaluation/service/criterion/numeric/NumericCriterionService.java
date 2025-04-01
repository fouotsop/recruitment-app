package com.recruitment_optimizer.candidateevaluation.service.criterion.numeric;

import org.springframework.stereotype.Service;

import com.recruitment_optimizer.candidateevaluation.dto.model.NumericCriterionDto;
import com.recruitment_optimizer.candidateevaluation.model.NumericCriterion;

@Service 
public interface NumericCriterionService {
    
    public NumericCriterion getById(String id);

    public NumericCriterion update(NumericCriterionDto criterion);

}
