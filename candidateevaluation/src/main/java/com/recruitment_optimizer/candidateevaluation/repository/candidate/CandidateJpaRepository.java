package com.recruitment_optimizer.candidateevaluation.repository.candidate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruitment_optimizer.candidateevaluation.model.Candidate;

public interface CandidateJpaRepository extends JpaRepository<Candidate, String> {
    
}
