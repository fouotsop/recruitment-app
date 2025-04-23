package com.recruitment_optimizer.candidateevaluation.service.candidate;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.recruitment_optimizer.candidateevaluation.model.Candidate;
import com.recruitment_optimizer.candidateevaluation.repository.candidate.CandidateRepository;


@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }


    @Override
    public Candidate create(Candidate candidate) {
        candidate.setId(UUID.randomUUID().toString());
        return candidateRepository.save(candidate);
    }

    @Override
    public Candidate findById(String id) {
        return this.candidateRepository.findById(id);
    }


    @Override
    public Candidate login(String email, String password) {

        Candidate candidate = candidateRepository.fetch(email, password);

        return candidate;
    }

    
}
