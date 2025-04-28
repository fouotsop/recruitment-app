package com.recruitment_optimizer.candidateevaluation.repository.candidate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.recruitment_optimizer.candidateevaluation.model.Candidate;

import jakarta.transaction.Transactional;


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
    public Candidate fetchById(String id) {
        return this.candidateJpaRepository.findById(id).orElseThrow();
    }

    @Override
    public Candidate fetch(String email, String password) {

        Candidate candidate = this.candidateJpaRepository.findByEmailAndPassword(email, password);

        if (candidate == null) {
            throw new RuntimeException("Candidate not found");
        }

        return candidate;

    }

    @Override
    public boolean existsByEmail(String email) {
        return this.candidateJpaRepository.existsByEmail(email);
    }

    @Override
    public Candidate findByEmail(String email) {
        return this.candidateJpaRepository.findByEmail(email);
    }

    @Override
    @Transactional  
    public List<Candidate> fetchByRecruitmentId(String id) {
        return this.candidateJpaRepository.findCandidatesByRecruitment(id);
    }
    
}
