package com.recruitment_optimizer.candidateevaluation.model;


public abstract class Criterion {

    private String name;

    private double preference;

    private Double weight;

    private boolean mandatory;

    public Criterion(String name, double preference) {
        this.name = name;
        this.preference = preference;
    }

    public String getName() {
        return name;
    }

    public double getPreference() {
        return preference;
    }

    public abstract String getType();

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    
}
