package com.recruitment_optimizer.candidateevaluation.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class Criterion {

    private String id;

    private String name;

    private String description;

    private boolean mandatory;

    private List<RecruitmentCriterion> recruitmentCriteria;

    public Criterion() {}

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public boolean isMandatory() {
        return mandatory;
    }


    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "criterion")
    public List<RecruitmentCriterion> getRecruitmentCriteria() {
        return recruitmentCriteria;
    }

    public void setRecruitmentCriteria(List<RecruitmentCriterion> recruitmentCriteria) {
        this.recruitmentCriteria = recruitmentCriteria;
    }
    

}
