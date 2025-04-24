package com.recruitment_optimizer.candidateevaluation.dto.model;

public class RecruitmentCriterionDto {
    
    private String recruitmentId;

    private Double preference;

    private Double threshold;

    private CriterionDto criterion;

    public RecruitmentCriterionDto () {}

    public String getRecruitmentId() {
        return recruitmentId;
    }

    public void setRecruitmentId(String recruitmentId) {
        this.recruitmentId = recruitmentId;
    }

    public CriterionDto getCriterion() {
        return criterion;
    }

    public void setCriterion(CriterionDto criterion) {
        this.criterion = criterion;
    }

    public Double getPreference() {
        return preference;
    }

    public void setPreference(Double preference) {
        this.preference = preference;
    }

    public Double getThreshold() {
        return threshold;
    }

    public void setThreshold(Double threshold) {
        this.threshold = threshold;
    }


}