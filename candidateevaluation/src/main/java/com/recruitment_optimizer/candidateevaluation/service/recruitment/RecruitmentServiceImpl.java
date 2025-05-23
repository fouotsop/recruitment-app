package com.recruitment_optimizer.candidateevaluation.service.recruitment;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.recruitment_optimizer.candidateevaluation.dto.model.RecruitmentDto;
import com.recruitment_optimizer.candidateevaluation.mapper.recruitment.RecruitmentMapper;
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
    private final RecruitmentMapper recruitmentMapper;

    public RecruitmentServiceImpl(RecruitmentMapper mapper, RecruitmentRepository recruitmentRepository, RecruitmentCriterionRepository recruitmentCriterionRepository, CriterionRepository criterionRepository) {
        this.criterionRepository = criterionRepository;
        this.recruitmentCriterionRepository = recruitmentCriterionRepository;
        this.recruitmentRepository = recruitmentRepository;
        this.recruitmentMapper = mapper;
    }

    @Override
    @Transactional
    public Recruitment create(Recruitment recruitment) {

        String id = recruitment.getId();

        List<RecruitmentCriterion> recruitmentCriteria = recruitment.getRecruitmentCriteria();
        for (RecruitmentCriterion recruitmentCriterion : recruitmentCriteria) {
            recruitmentCriterion.getId().setChildId(id);
        }

        recruitment.setRecruitmentCriteria(null);
        recruitment.setId(id);
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

    @Override
    public RecruitmentDto fetchById(String id) {

        Recruitment recruitment = recruitmentRepository.fetcchById(id);

        RecruitmentDto recruitmentDto = recruitmentMapper.toDto(recruitment);

        return recruitmentDto;
    }

    @Override
    @Transactional
    public Recruitment addCriterion(String id, List<RecruitmentCriterion> criteria) {

        Recruitment recruitment = recruitmentRepository.findById(id);

        for (RecruitmentCriterion recruitmentCriterion : criteria) {
            Criterion criterion = criterionRepository.findById(recruitmentCriterion.getId().getParentId());
            recruitmentCriterion.setCriterion(criterion);
            recruitmentCriterion.setRecruitment(recruitment);
            recruitmentCriterionRepository.save(recruitmentCriterion);
        }

        recruitment.getRecruitmentCriteria().addAll(criteria);

        return recruitment;

    }

    @Override
    public List<Recruitment> findAll() {
        return recruitmentRepository.findAll();
    }

}