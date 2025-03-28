package com.recruitment_optimizer.candidateevaluation.dto;

public class NumericCriterionDto extends CriterionDto {

    private double min;

    private double max;

    public NumericCriterionDto() {
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }
    
}
