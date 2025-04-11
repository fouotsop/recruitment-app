package com.recruitment_optimizer.candidateevaluation.dto.request;

import java.util.List;

public class CreateApplication {

    private String recruitmentId;

    private String candidateId;

    private List<String> criteriaIds;

    private List<Double> criteriaValues;
    

    public CreateApplication() {
    }


    public String getRecruitmentId() {
        return recruitmentId;
    }


    public void setRecruitmentId(String recruitmentId) {
        this.recruitmentId = recruitmentId;
    }


    public String getCandidateId() {
        return candidateId;
    }


    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }


    public List<String> getCriteriaIds() {
        return criteriaIds;
    }


    public void setCriteriaIds(List<String> criteriaIds) {
        this.criteriaIds = criteriaIds;
    }


    public List<Double> getCriteriaValues() {
        return criteriaValues;
    }


    public void setCriteriaValues(List<Double> criteriaValues) {
        this.criteriaValues = criteriaValues;
    }


}
