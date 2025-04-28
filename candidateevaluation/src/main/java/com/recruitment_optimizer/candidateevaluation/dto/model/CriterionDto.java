package com.recruitment_optimizer.candidateevaluation.dto.model;

public class  CriterionDto {

    private String id;

    private String name;

    private String description;

    private boolean mandatory;
    
    private String type;

    public CriterionDto () {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    
}
