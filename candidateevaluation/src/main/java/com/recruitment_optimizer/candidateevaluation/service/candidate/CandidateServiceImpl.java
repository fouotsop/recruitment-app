package com.recruitment_optimizer.candidateevaluation.service.candidate;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.recruitment_optimizer.candidateevaluation.dto.model.CandidateDto;
import com.recruitment_optimizer.candidateevaluation.mapper.candidate.CandidateMapper;
import com.recruitment_optimizer.candidateevaluation.model.Candidate;
import com.recruitment_optimizer.candidateevaluation.repository.candidate.CandidateRepository;

import jakarta.transaction.Transactional;


@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final CandidateMapper candidateMapper;

    public CandidateServiceImpl(CandidateMapper candidateMapper, CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
        this.candidateMapper = candidateMapper;
    }


    @Override
    public Candidate create(Candidate candidate) {

        boolean exists = candidateRepository.existsByEmail(candidate.getEmail());
        if (exists) {
            Candidate existingCandidate = candidateRepository.findByEmail(candidate.getEmail());

            return existingCandidate;
        }

        candidate.setId(UUID.randomUUID().toString());

        return candidateRepository.save(candidate);
    }

    @Override
    @Transactional
    public CandidateDto fetchById(String id) {
        Candidate candidate = this.candidateRepository.fetchById(id);

        CandidateDto candidateDto = candidateMapper.toDto(candidate);

        return candidateDto;
    }


    @Override
    public Candidate login(String email, String password) {

        Candidate candidate = candidateRepository.fetch(email, password);

        return candidate;
    }


    @Override
    @Transactional
    public List<Candidate> fetchByRecruitmentId(String id) {

        List<Candidate> candidates = candidateRepository.fetchByRecruitmentId(id);

        return candidates;   

    }

    
}
