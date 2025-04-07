package com.recruitment_optimizer.candidateevaluation.repository.recruitmentcriterion;

import org.springframework.stereotype.Repository;

import com.recruitment_optimizer.candidateevaluation.model.CompositeId;
import com.recruitment_optimizer.candidateevaluation.model.RecruitmentCriterion;

@Repository
public interface RecruitmentCriterionRepository {
    
    public RecruitmentCriterion findById(CompositeId id);

    public RecruitmentCriterion save(RecruitmentCriterion recruitmentCriterion);


}
