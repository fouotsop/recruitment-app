package com.recruitment_optimizer.candidateevaluation.repository.candidate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.recruitment_optimizer.candidateevaluation.model.Candidate;

@Repository
public interface CandidateRepository {
    
    public Candidate save (Candidate candidate);

    public Candidate fetchById(String id);

    public Candidate fetch(String email, String password);

    public boolean existsByEmail(String email);

    public Candidate findByEmail(String email);

    public List<Candidate> fetchByRecruitmentId(String id);

    public Candidate findById(String id);
}
