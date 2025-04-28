package com.recruitment_optimizer.candidateevaluation.dto.request;

import java.util.List;

import com.recruitment_optimizer.candidateevaluation.dto.model.RecruitmentCriterionDtoC;

public class CreateRecruitment {
    

   private String id;

    private String title;

    private String description;

    private String location;

    private int numberOfPosts;

    private double salary;

    private boolean available;

    private List<RecruitmentCriterionDtoC> recruitmentCriteria;

    public CreateRecruitment () {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumberOfPosts() {
        return numberOfPosts;
    }

    public void setNumberOfPosts(int numberOfPosts) {
        this.numberOfPosts = numberOfPosts;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }


    public List<RecruitmentCriterionDtoC> getRecruitmentCriteria() {
        return recruitmentCriteria;
    }

    public void setRecruitmentCriteria(List<RecruitmentCriterionDtoC> recruitmentCriterions) {
        this.recruitmentCriteria = recruitmentCriterions;
    }



}
