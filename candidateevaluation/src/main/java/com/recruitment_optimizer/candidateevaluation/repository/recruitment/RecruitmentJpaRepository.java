package com.recruitment_optimizer.candidateevaluation.repository.recruitment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.recruitment_optimizer.candidateevaluation.model.Recruitment;

public interface RecruitmentJpaRepository extends JpaRepository<Recruitment, String> {


    @Query(value = "SELECT r FROM Recruitment r LEFT JOIN FETCH r.recruitmentCriteria WHERE r.id = ?1")
    Recruitment fetchById(String id);
    
}
