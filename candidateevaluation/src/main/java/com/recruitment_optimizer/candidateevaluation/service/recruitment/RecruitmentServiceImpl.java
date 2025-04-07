package com.recruitment_optimizer.candidateevaluation.service.recruitment;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.recruitment_optimizer.candidateevaluation.model.Criterion;
import com.recruitment_optimizer.candidateevaluation.model.Recruitment;
import com.recruitment_optimizer.candidateevaluation.model.RecruitmentCriterion;
import com.recruitment_optimizer.candidateevaluation.repository.criterion.CriterionRepository;
import com.recruitment_optimizer.candidateevaluation.repository.recruitment.RecruitmentRepository;
import com.recruitment_optimizer.candidateevaluation.repository.recruitmentcriterion.RecruitmentCriterionRepository;

import jakarta.transaction.Transactional;

@Service
public class RecruitmentServiceImpl implements RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final RecruitmentCriterionRepository recruitmentCriterionRepository;
    private final CriterionRepository criterionRepository;

    public RecruitmentServiceImpl(RecruitmentRepository recruitmentRepository, RecruitmentCriterionRepository recruitmentCriterionRepository, CriterionRepository criterionRepository) {
        this.criterionRepository = criterionRepository;
        this.recruitmentCriterionRepository = recruitmentCriterionRepository;
        this.recruitmentRepository = recruitmentRepository;
    }

    @Override
    @Transactional
    public Recruitment create(Recruitment recruitment) {

        String id = UUID.randomUUID().toString();

        List<RecruitmentCriterion> recruitmentCriteria = recruitment.getRecruitmentCriteria();
        for (RecruitmentCriterion recruitmentCriterion : recruitmentCriteria) {
            recruitmentCriterion.getId().setChildId(id);
        }

        recruitment.setRecruitmentCriteria(null);
        recruitment = recruitmentRepository.save(recruitment);

        for (RecruitmentCriterion recruitmentCriterion : recruitmentCriteria) {
            Criterion criterion = criterionRepository.findById(recruitmentCriterion.getId().getParentId());
            recruitmentCriterion.setCriterion(criterion);
            recruitmentCriterion.setRecruitment(recruitment);
            recruitmentCriterionRepository.save(recruitmentCriterion);
        }


        return recruitment;
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