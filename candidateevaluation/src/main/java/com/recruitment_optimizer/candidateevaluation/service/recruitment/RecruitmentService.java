package com.recruitment_optimizer.candidateevaluation.service.recruitment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.recruitment_optimizer.candidateevaluation.dto.model.RecruitmentDto;
import com.recruitment_optimizer.candidateevaluation.model.Recruitment;

@Service
public interface RecruitmentService {

    public Recruitment  create(Recruitment recruitment);    

    public Recruitment update(Recruitment recruitment);

    public Page<Recruitment> findAll(Pageable pageable);

    public Recruitment findById(String id);

    public RecruitmentDto fetchById(String id);
    

}
