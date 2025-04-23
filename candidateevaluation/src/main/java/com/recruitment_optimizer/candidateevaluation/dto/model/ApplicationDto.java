package com.recruitment_optimizer.candidateevaluation.dto.model;

import java.util.List;

public class ApplicationDto {

    private String applicantId;

    private String recruitmentId;

    private Double ahpScore;

    private List<CriterionValueDto> criterionValues;

    public ApplicationDto() {}

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getRecruitmentId() {
        return recruitmentId;
    }

    public void setRecruitmentId(String recruitmentId) {
        this.recruitmentId = recruitmentId;
    }

    public Double getAhpScore() {
        return ahpScore;
    }

    public void setAhpScore(Double ahpScore) {
        this.ahpScore = ahpScore;
    }

    public List<CriterionValueDto> getCriterionValues() {
        return criterionValues;
    }

    public void setCriterionValues(List<CriterionValueDto> criterionValues) {
        this.criterionValues = criterionValues;
    }

    
}
