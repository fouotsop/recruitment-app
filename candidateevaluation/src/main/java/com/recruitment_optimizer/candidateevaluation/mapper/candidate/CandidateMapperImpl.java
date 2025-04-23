package com.recruitment_optimizer.candidateevaluation.mapper.candidate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.recruitment_optimizer.candidateevaluation.dto.model.ApplicationDto;
import com.recruitment_optimizer.candidateevaluation.dto.model.CandidateDto;
import com.recruitment_optimizer.candidateevaluation.dto.model.CriterionValueDto;
import com.recruitment_optimizer.candidateevaluation.model.Application;
import com.recruitment_optimizer.candidateevaluation.model.Candidate;
import com.recruitment_optimizer.candidateevaluation.model.CriterionValue;

@Component
public class CandidateMapperImpl implements CandidateMapper {

    @Override
    public CandidateDto toDto(Candidate candidate) {

        CandidateDto candidateDto = new CandidateDto();

        candidateDto.setId(candidate.getId());
        candidateDto.setDateOfBirth(candidate.getDateOfBirth());
        candidateDto.setEmail(candidate.getEmail());
        candidateDto.setGenre(candidate.getGenre());
        candidateDto.setLocation(candidate.getLocation());
        candidateDto.setName(candidate.getName());
        candidateDto.setPhone(candidate.getPhone());

        List<ApplicationDto> applicationDtos = new ArrayList<>();

        for (Application application : candidate.getApplications()) {
            ApplicationDto applicationDto = new ApplicationDto();
            applicationDto.setRecruitmentId(application.getId().getParentId());
            applicationDto.setApplicantId(application.getId().getChildId());
            applicationDto.setAhpScore(application.getAhpScore());

            List<CriterionValueDto> criterionValueDtos = new ArrayList<>();
            
            for (CriterionValue value: application.getCriteriaValues()) {
                CriterionValueDto criterionValueDto = new CriterionValueDto();
                criterionValueDto.setCriterionId(value.getId().getParentId());
                criterionValueDto.setApplicationId(value.getId().getChildId().getChildId());
                criterionValueDto.setRecruitmentId(value.getId().getChildId().getParentId());   

                criterionValueDtos.add(criterionValueDto);
                
                
            }
            applicationDto.setCriterionValues(criterionValueDtos);
            applicationDtos.add(applicationDto);    
        }
        
        candidateDto.setApplications(applicationDtos);
        return candidateDto;
    }
    
}
