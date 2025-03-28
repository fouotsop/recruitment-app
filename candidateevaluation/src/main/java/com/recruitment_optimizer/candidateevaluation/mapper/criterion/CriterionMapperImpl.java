package com.recruitment_optimizer.candidateevaluation.mapper.criterion;

import org.springframework.stereotype.Component;

import com.recruitment_optimizer.candidateevaluation.dto.CategoricalCriterionDto;
import com.recruitment_optimizer.candidateevaluation.dto.CriterionDto;
import com.recruitment_optimizer.candidateevaluation.dto.NumericCriterionDto;
import com.recruitment_optimizer.candidateevaluation.model.CategoricalCriterion;
import com.recruitment_optimizer.candidateevaluation.model.NumericCriterion;

@Component
public class CriterionMapperImpl implements CriterionMapper {

    public CriterionMapperImpl() {}

    public CriterionDto toDto(CategoricalCriterion criterion) {

        CategoricalCriterionDto dto = new CategoricalCriterionDto();
        dto.setId(criterion.getId());
        dto.setName(criterion.getName());
        dto.setDescription(criterion.getDescription());
        dto.setMandatory(criterion.isMandatory());
        dto.setValues(criterion.getLabeledValues());

        return dto;
    }

    public CriterionDto toDto (NumericCriterion criterion) {

        NumericCriterionDto dto = new NumericCriterionDto();
        dto.setId(criterion.getId());
        dto.setName(criterion.getName());
        dto.setDescription(criterion.getDescription());
        dto.setMin(dto.getMin());
        dto.setMax(dto.getMax());
        dto.setMandatory(criterion.isMandatory());

        return dto;
    }
    
}
