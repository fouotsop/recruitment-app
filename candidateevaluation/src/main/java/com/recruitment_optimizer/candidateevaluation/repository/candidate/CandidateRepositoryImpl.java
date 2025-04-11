package com.recruitment_optimizer.candidateevaluation.repository.candidate;

import org.springframework.stereotype.Repository;

import com.recruitment_optimizer.candidateevaluation.model.Candidate;


@Repository
public class CandidateRepositoryImpl implements CandidateRepository {

    private final CandidateJpaRepository candidateJpaRepository;

    public CandidateRepositoryImpl(CandidateJpaRepository candidateJpaRepository) {
        this.candidateJpaRepository = candidateJpaRepository;
    }

    @Override
    public Candidate save(Candidate candidate) {
        candidate = this.candidateJpaRepository.save(candidate);

        return candidate;
    }

    @Override
    public Candidate findById(String id) {
        return this.candidateJpaRepository.findById(id).orElseThrow();
    }
    
}
