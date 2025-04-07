package com.recruitment_optimizer.candidateevaluation.repository.recruitmentcriterion;

import org.springframework.stereotype.Repository;

import com.recruitment_optimizer.candidateevaluation.model.CompositeId;
import com.recruitment_optimizer.candidateevaluation.model.RecruitmentCriterion;

@Repository
public class RecruitmentCriterionRepositoryImpl implements RecruitmentCriterionRepository {

    private final RecruitmentCriterionJpaRepository recruitmentCriterionJpaRepository;


    public RecruitmentCriterionRepositoryImpl(RecruitmentCriterionJpaRepository recruitmentCriterionJpaRepository) {
        this.recruitmentCriterionJpaRepository = recruitmentCriterionJpaRepository;
    }

    @Override
    public RecruitmentCriterion findById(CompositeId id) {
        return null;
    }

    @Override
    public RecruitmentCriterion save(RecruitmentCriterion recruitmentCriterion) {
        return this.recruitmentCriterionJpaRepository.save(recruitmentCriterion);
    }   
    
}
