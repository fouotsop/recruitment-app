package com.recruitment_optimizer.candidateevaluation.service.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.recruitment_optimizer.candidateevaluation.dto.request.CreateApplication;
import com.recruitment_optimizer.candidateevaluation.model.Application;
import com.recruitment_optimizer.candidateevaluation.model.Candidate;
import com.recruitment_optimizer.candidateevaluation.model.CompositeId;
import com.recruitment_optimizer.candidateevaluation.model.Criterion;
import com.recruitment_optimizer.candidateevaluation.model.CriterionValue;
import com.recruitment_optimizer.candidateevaluation.model.Recruitment;
import com.recruitment_optimizer.candidateevaluation.model.TenaryId;
import com.recruitment_optimizer.candidateevaluation.repository.application.ApplicationRepository;
import com.recruitment_optimizer.candidateevaluation.repository.candidate.CandidateRepository;
import com.recruitment_optimizer.candidateevaluation.repository.recruitment.RecruitmentRepository;

import jakarta.transaction.Transactional;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final RecruitmentRepository recruitmentRepository;

    private final CandidateRepository   candidateRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, RecruitmentRepository recruitmentRepository, CandidateRepository candidateRepository) {
        this.applicationRepository = applicationRepository;
        this.recruitmentRepository = recruitmentRepository;
        this.candidateRepository = candidateRepository;
    }

    @Override
    @Transactional
    public Application apply(CreateApplication create) {

        Application application = new Application();

        application.setRecruitment(new Recruitment());
        application.getRecruitment().setId(create.getRecruitmentId());

        application.setCandidate(new Candidate());
        application.getCandidate().setId(create.getCandidateId());

        List<Criterion> criteria = new ArrayList<>();    

        List<CriterionValue> criterionValues = new ArrayList<>();

        int i = 0;
        for (String  criterionId : create.getCriteriaIds()) {

            Criterion criterion = new Criterion() {
                @Override
                public String getId() {
                    return criterionId;
                }
            };
            criterion.setId(criterionId);
            criteria.add(criterion);

            CriterionValue criterionValue = new CriterionValue();
            criterionValue.setCriterion(criterion);
            criterionValue.setValue(create.getCriteriaValues().get(i));

            criterionValues.add(criterionValue);
            i = i + 1;
        }

        application.setCriteriaValues(criterionValues);


        Recruitment recruitment = recruitmentRepository.findById(create.getRecruitmentId());
        Candidate candidate = candidateRepository.findById(create.getCandidateId()); 

        application.setRecruitment(recruitment);
        application.setCandidate(candidate);
        application.setId(new CompositeId(recruitment.getId(), candidate.getId()));;

        for (CriterionValue value : application.getCriteriaValues()) {
            value.setId(new TenaryId(value.getCriterion().getId(), application.getId()));
            value.setApplication(application);
        }

        application = applicationRepository.save(application);

        return application;


    }
    
}
