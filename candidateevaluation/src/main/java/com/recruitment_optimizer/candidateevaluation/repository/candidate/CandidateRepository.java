package com.recruitment_optimizer.candidateevaluation.repository.candidate;

import org.springframework.stereotype.Repository;

import com.recruitment_optimizer.candidateevaluation.model.Candidate;

@Repository
public interface CandidateRepository {
    
    public Candidate save (Candidate candidate);

    public Candidate fetchById(String id);

    public Candidate fetch(String email, String password);

}
