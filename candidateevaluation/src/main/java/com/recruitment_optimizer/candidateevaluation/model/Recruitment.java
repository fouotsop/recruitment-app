package com.recruitment_optimizer.candidateevaluation.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Recruitment {

    private String id;

    private String title;

    private String description;

    private String location;

    private int numberOfPosts;

    private double salary;

    private boolean available;

    private List<RecruitmentCriterion> recruitmentCriteria;

    private List<Application> applications;

    public Recruitment () {}

    @Id
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

    @Column(columnDefinition = "TEXT", nullable = false)
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


    @JsonIgnore
    @OneToMany(mappedBy = "recruitment")
    public List<RecruitmentCriterion> getRecruitmentCriteria() {
        return recruitmentCriteria;
    }

    public void setRecruitmentCriteria(List<RecruitmentCriterion> recruitmentCriterions) {
        this.recruitmentCriteria = recruitmentCriterions;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "recruitment")
    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }


    


    
}
