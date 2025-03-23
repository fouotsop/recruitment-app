package com.recruitment_optimizer.candidateevaluation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class NumericCriterion extends Criterion {
    
    private Double minValue;

    private Double maxValue;

    public NumericCriterion() {}

    @Override
    @Id
    public String getId() {
        return super.getId();
    }

    public Double getMinValue() {
        return minValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }


    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }


    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    
}
