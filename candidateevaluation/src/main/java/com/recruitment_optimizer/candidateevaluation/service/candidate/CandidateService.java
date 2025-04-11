package com.recruitment_optimizer.candidateevaluation.service.candidate;

import org.springframework.stereotype.Service;

import com.recruitment_optimizer.candidateevaluation.model.Candidate;

@Service
public interface CandidateService {

    Candidate create(Candidate candidate);

    Candidate findById(String id);

}
