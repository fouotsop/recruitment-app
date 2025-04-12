package com.recruitment_optimizer.candidateevaluation.service.ahp;

import org.springframework.stereotype.Service;

@Service
public interface AHPService {

    public double calculateAHPWeight(String recruitmentId);

    public double calculateAhpScores(String recruitmentId);
}
