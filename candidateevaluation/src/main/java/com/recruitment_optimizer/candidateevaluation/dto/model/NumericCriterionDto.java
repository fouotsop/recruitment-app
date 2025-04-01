package com.recruitment_optimizer.candidateevaluation.dto.model;

public class NumericCriterionDto extends CriterionDto {

    private Double min;

    private Double max;

    public NumericCriterionDto() {
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }
    
}
