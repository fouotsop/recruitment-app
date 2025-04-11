package com.recruitment_optimizer.candidateevaluation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class CriterionValue {

    private TenaryId id;

    private double value;

    private Application application;

    private Criterion criterion;

    public CriterionValue() {}

    @EmbeddedId
    public TenaryId getId() {
        return id;
    }

    public void setId(TenaryId id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @JsonIgnore
    @MapsId("childId")
    @ManyToOne(fetch = FetchType.LAZY)
    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    @JsonIgnore
    @MapsId("parentId")
    @ManyToOne(fetch = FetchType.LAZY)
    public Criterion getCriterion() {
        return criterion;
    }

    public void setCriterion(Criterion criterion) {
        this.criterion = criterion;
    }

    
}
