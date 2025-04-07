package com.recruitment_optimizer.candidateevaluation.repository.recruitmentcriterion;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruitment_optimizer.candidateevaluation.model.CompositeId;
import com.recruitment_optimizer.candidateevaluation.model.RecruitmentCriterion;

public interface RecruitmentCriterionJpaRepository extends JpaRepository <RecruitmentCriterion, CompositeId> {
}
