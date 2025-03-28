package com.recruitment_optimizer.candidateevaluation.mapper.criterion;

import org.springframework.stereotype.Component;

import com.recruitment_optimizer.candidateevaluation.dto.CriterionDto;
import com.recruitment_optimizer.candidateevaluation.model.CategoricalCriterion;
import com.recruitment_optimizer.candidateevaluation.model.NumericCriterion;

@Component
public interface CriterionMapper {

    public CriterionDto toDto(CategoricalCriterion criterion);

    public CriterionDto toDto(NumericCriterion criterion);
    
}