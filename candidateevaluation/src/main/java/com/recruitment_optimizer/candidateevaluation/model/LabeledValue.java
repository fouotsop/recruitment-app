package com.recruitment_optimizer.candidateevaluation.model;

public class LabeledValue {

    private String label;

    private double value;
    
    public LabeledValue(String label, double value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public double getValue() {
        return value;
    }

}
