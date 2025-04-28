package com.recruitment_optimizer.candidateevaluation.dto.model;

public class CriterionValueDto {

    private String recruitmentId;

    private String applicationId;

    private String criterionId;

    private double value;

    public CriterionValueDto() {}

    public String getRecruitmentId() {
        return recruitmentId;
    }

    public void setRecruitmentId(String recruitmentId) {
        this.recruitmentId = recruitmentId;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getCriterionId() {
        return criterionId;
    }

    public void setCriterionId(String criterionId) {
        this.criterionId = criterionId;
    }

    public double  getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    
    
}
