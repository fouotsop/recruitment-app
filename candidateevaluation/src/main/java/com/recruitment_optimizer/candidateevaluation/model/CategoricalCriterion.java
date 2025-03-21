package com.recruitment_optimizer.candidateevaluation.model;

import java.util.List;

public class CategoricalCriterion extends Criterion {
    
    private List<LabeledValue> labeledValues;

    public CategoricalCriterion(String name, double preference, List<LabeledValue> labeledValues) {
        super(name, preference);
        this.labeledValues = labeledValues;
    }

    public List<LabeledValue> getLabeledValues() {
        return labeledValues;
    }

    public void setLabeledValues(List<LabeledValue> labeledValues) {
        this.labeledValues = labeledValues;
    }
    
    @Override
    public String getType() {
        return "CATEGORICAL";
    }
    
}   