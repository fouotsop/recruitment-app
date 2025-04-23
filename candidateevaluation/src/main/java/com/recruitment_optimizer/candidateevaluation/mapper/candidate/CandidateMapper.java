package com.recruitment_optimizer.candidateevaluation.mapper.candidate;

import org.springframework.stereotype.Component;

import com.recruitment_optimizer.candidateevaluation.dto.model.CandidateDto;
import com.recruitment_optimizer.candidateevaluation.model.Candidate;

@Component
public interface CandidateMapper {
    
    public CandidateDto toDto(Candidate candidate);
}
