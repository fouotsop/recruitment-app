package com.recruitment_optimizer.candidateevaluation.service.candidate;

import java.util.List;

import org.springframework.stereotype.Service;

import com.recruitment_optimizer.candidateevaluation.dto.model.CandidateDto;
import com.recruitment_optimizer.candidateevaluation.model.Candidate;

@Service
public interface CandidateService {

    Candidate create(Candidate candidate);

    CandidateDto fetchById(String id);

    Candidate login(String email, String password);

    List<Candidate> fetchByRecruitmentId(String id);


}
