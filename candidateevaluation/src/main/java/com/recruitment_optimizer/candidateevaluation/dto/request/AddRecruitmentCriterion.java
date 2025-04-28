package com.recruitment_optimizer.candidateevaluation.dto.request;

public class AddRecruitmentCriterion {
    
    private String criterionId;

    private double preference;

    private double threshold;

    public AddRecruitmentCriterion() {}

    public String getCriterionId() {
        return criterionId;
    }

    public void setCriterionId(String criterionId) {
        this.criterionId = criterionId;
    }

    public double getPreference() {
        return preference;
    }

    public void setPreference(double preference) {
        this.preference = preference;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    


}
