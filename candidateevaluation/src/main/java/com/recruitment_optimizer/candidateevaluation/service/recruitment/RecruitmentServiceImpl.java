package com.recruitment_optimizer.candidateevaluation.service.recruitment;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.recruitment_optimizer.candidateevaluation.model.Recruitment;
import com.recruitment_optimizer.candidateevaluation.repository.recruitment.RecruitmentRepository;

@Service
public class RecruitmentServiceImpl implements RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

    public RecruitmentServiceImpl(RecruitmentRepository recruitmentRepository) {
        this.recruitmentRepository = recruitmentRepository;
    }

    @Override
    public Recruitment create(Recruitment recruitment) {
        recruitment.setId(UUID.randomUUID().toString());    
        return recruitmentRepository.save(recruitment);
    }

    @Override
    public Recruitment update(Recruitment recruitment) {
        return recruitmentRepository.save(recruitment);
    }

    @Override
    public Page<Recruitment> findAll(Pageable pageable) {
        return recruitmentRepository.findAll(pageable);
    }

    @Override
    public Recruitment findById(String id) {
        return recruitmentRepository.findById(id);
    }


}