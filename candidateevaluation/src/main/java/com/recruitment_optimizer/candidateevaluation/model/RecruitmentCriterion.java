package com.recruitment_optimizer.candidateevaluation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class RecruitmentCriterion {

    private CompositeId id;

    private Double preference;

    private Double weight;

    private Recruitment recruitment;

    private Criterion criterion;

    public RecruitmentCriterion () {}

    @EmbeddedId
    public CompositeId getId() {
        return id;
    }

    public void setId(CompositeId id) {
        this.id = id;
    }

    public Double getPreference() {
        return preference;
    }

    public void setPreference(Double preference) {
        this.preference = preference;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @JsonIgnore
    @MapsId("childId")
    @ManyToOne(fetch = FetchType.LAZY)
    public Recruitment getRecruitment() {
        return recruitment;
    }

    public void setRecruitment(Recruitment recruitment) {
        this.recruitment = recruitment;
    }

    @JsonIgnore
    @MapsId("parentId")
    @ManyToOne(fetch = FetchType.EAGER)
    public Criterion getCriterion() {
        return criterion;
    }

    public void setCriterion(Criterion criterion) {
        this.criterion = criterion;
    }

    
}
