package com.recruitment_optimizer.candidateevaluation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class Application {
    
    private CompositeId id;

    private Double ahpScore;

    private Candidate candidate;

    private Recruitment recruitment;


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


}
