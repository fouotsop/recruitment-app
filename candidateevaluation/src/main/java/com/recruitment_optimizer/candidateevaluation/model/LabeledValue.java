package com.recruitment_optimizer.candidateevaluation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class LabeledValue {

    private String id;

    private String label;

    private double value;

    private CategoricalCriterion categoricalCriterion;
    
    public LabeledValue () {}

    public LabeledValue(String label, double value) {
        this.label = label;
        this.value = value;
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }   

    @Column(nullable = false)
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    public CategoricalCriterion getCategoricalCriterion() {
        return categoricalCriterion;
    }
    
    public void setCategoricalCriterion(CategoricalCriterion categoricalCriterion) {
        this.categoricalCriterion = categoricalCriterion;
    }


}
