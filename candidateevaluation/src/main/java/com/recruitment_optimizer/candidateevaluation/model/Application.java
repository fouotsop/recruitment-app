package com.recruitment_optimizer.candidateevaluation.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;

@Entity
public class Application {
    
    private CompositeId id;

    private Double ahpScore;

    private Candidate candidate;

    private Recruitment recruitment;

    private List<CriterionValue> criteriaValues;

    public Application () {}

    @EmbeddedId
    public CompositeId getId() {
        return id;
    }

    public void setId(CompositeId id) {
        this.id = id;
    }

    public Double getAhpScore() {
        return ahpScore;
    }

    public void setAhpScore(Double score) {
        this.ahpScore = score;
    }

    @JsonIgnore
    @MapsId("childId")
    @ManyToOne(fetch = FetchType.LAZY)
    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    @JsonIgnore
    @MapsId("parentId")
    @ManyToOne(fetch = FetchType.LAZY)
    public Recruitment getRecruitment() {
        return recruitment;
    }

    public void setRecruitment(Recruitment recruitment) {
        this.recruitment = recruitment;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<CriterionValue> getCriteriaValues() {
        return criteriaValues;
    }

    public void setCriteriaValues(List<CriterionValue> applicationCriterionValue) {
        this.criteriaValues = applicationCriterionValue;
    }


}
