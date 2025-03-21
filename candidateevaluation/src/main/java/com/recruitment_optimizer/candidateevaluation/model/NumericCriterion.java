package com.recruitment_optimizer.candidateevaluation.model;

public class NumericCriterion extends Criterion {
    
    private Double minValue;

    private Double maxValue;

    public NumericCriterion(String name, double preference, Double minValue, Double maxValue) {
        super(name, preference);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public Double getMinValue() {
        return minValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    @Override
    public String getType() {
        return "CONTINOUOS";
    }
    
}
