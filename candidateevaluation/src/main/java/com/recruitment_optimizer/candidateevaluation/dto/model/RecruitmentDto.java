package com.recruitment_optimizer.candidateevaluation.dto.model;

import java.util.List;

import com.recruitment_optimizer.candidateevaluation.model.RecruitmentCriterion;

import jakarta.persistence.Column;

public class RecruitmentDto {
    
   private String id;

    private String title;

    private String description;

    private String location;

    private int numberOfPosts;

    private double salary;

    private boolean available;

    private List<RecruitmentCriterion> recruitmentCriteria;

    public RecruitmentDto () {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(nullable = false)
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


    public List<RecruitmentCriterion> getRecruitmentCriteria() {
        return recruitmentCriteria;
    }

    public void setRecruitmentCriteria(List<RecruitmentCriterion> recruitmentCriterions) {
        this.recruitmentCriteria = recruitmentCriterions;
    }

}
