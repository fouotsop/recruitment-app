package com.recruitment_optimizer.candidateevaluation.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class CategoricalCriterion extends Criterion {
    
    
    private List<LabeledValue> labeledValues;

    public CategoricalCriterion() {}    

    public CategoricalCriterion(String id, String name, String description, boolean mandatory,List<LabeledValue> labeledValues) {
        super(id, name, description, mandatory);
        this.labeledValues = labeledValues;
    }

    @Override
    @Id
    public String getId() {
        return super.getId();
    }

    @JsonIgnore
    @OneToMany(mappedBy = "categoricalCriterion")
    public List<LabeledValue> getLabeledValues() {
        return labeledValues;
    }

    public void setLabeledValues(List<LabeledValue> labeledValues) {
        this.labeledValues = labeledValues;
    }
    
}   