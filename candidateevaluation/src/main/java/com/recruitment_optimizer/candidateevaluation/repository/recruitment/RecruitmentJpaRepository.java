package com.recruitment_optimizer.candidateevaluation.repository.recruitment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruitment_optimizer.candidateevaluation.model.Recruitment;

public interface RecruitmentJpaRepository extends JpaRepository<Recruitment, String> {
    
}
