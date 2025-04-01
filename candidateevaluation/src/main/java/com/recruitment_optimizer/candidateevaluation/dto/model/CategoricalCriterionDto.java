package com.recruitment_optimizer.candidateevaluation.dto.model;

import java.util.List;

import com.recruitment_optimizer.candidateevaluation.model.LabeledValue;

public class CategoricalCriterionDto extends CriterionDto {

    private List<LabeledValue> values;

    public CategoricalCriterionDto() {}

    public List<LabeledValue> getValues() {
        return values;
    }

    public void setValues(List<LabeledValue> values) {
        this.values = values;
    }

}
