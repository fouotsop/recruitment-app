package com.recruitment_optimizer.candidateevaluation.dto.model;

public class RecruitmentCriterionDtoC {
    
    private String recruitmentId;

    private Double preference;

    private Double threshold;

    private String criterionId;

    public RecruitmentCriterionDtoC () {}

    public String getRecruitmentId() {
        return recruitmentId;
    }

    public void setRecruitmentId(String recruitmentId) {
        this.recruitmentId = recruitmentId;
    }

    public String getCriterionId() {
        return criterionId;
    }

    public void setCriterionId(String criterion) {
        this.criterionId = criterion;
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