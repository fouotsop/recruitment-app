package com.recruitment_optimizer.candidateevaluation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Entity
public class AppliclationCriterionValue {

    private CompositeId id;

    private double value;

    private Application application;

    private Criterion criterion;

    public AppliclationCriterionValue() {}

    @EmbeddedId
    public CompositeId getId() {
        return id;
    }

    public void setId(CompositeId id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    public Criterion getCriterion() {
        return criterion;
    }

    public void setCriterion(Criterion criterion) {
        this.criterion = criterion;
    }

    
}
