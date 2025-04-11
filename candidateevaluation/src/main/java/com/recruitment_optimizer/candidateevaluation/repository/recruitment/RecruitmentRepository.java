package com.recruitment_optimizer.candidateevaluation.repository.recruitment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.recruitment_optimizer.candidateevaluation.model.Recruitment;

@Repository
public interface RecruitmentRepository {
    
    public Recruitment save(Recruitment recruitment);

    public Recruitment findById(String id);

    public Page<Recruitment> findAll(Pageable pageable);

    public Recruitment fetcchById(String id);


}
