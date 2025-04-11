package com.recruitment_optimizer.candidateevaluation.repository.candidate;

import org.springframework.stereotype.Repository;

import com.recruitment_optimizer.candidateevaluation.model.Candidate;

@Repository
public interface CandidateRepository {
    
    public Candidate save (Candidate candidate);

    public Candidate findById(String id);

}
