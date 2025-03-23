package com.recruitment_optimizer.candidateevaluation.repository.recruitment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.recruitment_optimizer.candidateevaluation.model.Recruitment;

@Repository
public class RecruitmentRepositoryImpl implements RecruitmentRepository {
    
    private final RecruitmentJpaRepository recruitmentJpaRepository;    

    public RecruitmentRepositoryImpl(RecruitmentJpaRepository recruitmentJpaRepository) {
        this.recruitmentJpaRepository = recruitmentJpaRepository;
    }


    public Recruitment save(Recruitment recruitment) {
        return recruitmentJpaRepository.save(recruitment);
    }

    public Recruitment findById(String id) {
        return recruitmentJpaRepository.findById(id).orElse(null);
    }


    @Override
    public Page<Recruitment> findAll(Pageable pageable) {
        return recruitmentJpaRepository.findAll(pageable);
    }



}
