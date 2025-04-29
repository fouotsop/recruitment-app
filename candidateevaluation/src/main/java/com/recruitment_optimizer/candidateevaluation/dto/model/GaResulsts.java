package com.recruitment_optimizer.candidateevaluation.dto.model;

import java.util.List;

public class GaResulsts {
    
    private String recruitment_id;

    private double total_ahp;

    private List<String> optimal_team;

    private double fitness_score;

    public GaResulsts() {}


    public String getRecruitment_id() {
        return recruitment_id;
    }

    public void setRecruitment_id(String recruitment_id) {
        this.recruitment_id = recruitment_id;
    }

    public double getTotal_ahp() {
        return total_ahp;
    }

    public void setTotal_ahp(double total_ahp) {
        this.total_ahp = total_ahp;
    }

    public List<String> getOptimal_team() {
        return optimal_team;
    }

    public void setOptimal_team(List<String> optimal_team) {
        this.optimal_team = optimal_team;
    }

    public double getFitness_score() {
        return fitness_score;
    }

    public void setFitness_score(double fitness_score) {
        this.fitness_score = fitness_score;
    }

    


}
