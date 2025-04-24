package com.recruitment_optimizer.candidateevaluation.mapper.recruitment;

import java.util.List;

import org.springframework.stereotype.Service;

import com.recruitment_optimizer.candidateevaluation.dto.model.CriterionDto;
import com.recruitment_optimizer.candidateevaluation.dto.model.RecruitmentCriterionDto;
import com.recruitment_optimizer.candidateevaluation.dto.model.RecruitmentDto;
import com.recruitment_optimizer.candidateevaluation.model.Criterion;
import com.recruitment_optimizer.candidateevaluation.model.Recruitment;

@Service
public class RecruitmentMapperImpl implements RecruitmentMapper {

    @Override
    public RecruitmentDto toDto(Recruitment recruitment) {
        if (recruitment == null) {
            return null;
        }

        RecruitmentDto recruitmentDto = new RecruitmentDto();
        recruitmentDto.setId(recruitment.getId());
        recruitmentDto.setTitle(recruitment.getTitle());
        recruitmentDto.setDescription(recruitment.getDescription());
        recruitmentDto.setAvailable(recruitment.isAvailable());
        recruitmentDto.setNumberOfPosts(recruitment.getNumberOfPosts());
        recruitmentDto.setLocation(recruitment.getLocation());
        recruitmentDto.setSalary(recruitment.getSalary());

        List<RecruitmentCriterionDto> recruitmentCriteria = recruitment.getRecruitmentCriteria().stream()
                .map(recruitmentCriterion -> {
                    RecruitmentCriterionDto recruitmentCriterionDto = new RecruitmentCriterionDto();

                    Criterion criterion = recruitmentCriterion.getCriterion();
                    CriterionDto criterionDto = new CriterionDto() {
                                                @Override
                                                public String getId() {
                                                    return criterion.getId();
                                                }

                                                @Override
                                                public String getName() {
                                                    return criterion.getName();
                                                }

                                                @Override
                                                public String getDescription() {
                                                    return criterion.getDescription();
                                                }

                                                @Override
                                                public boolean isMandatory() {
                                                    return criterion.isMandatory();
                                                }

                                                @Override
                                                public String getType(){
                                                    return criterion.getType();
                                                }
                                                
                                            };

                    recruitmentCriterionDto.setCriterion(criterionDto);
                    recruitmentCriterionDto.setRecruitmentId(recruitment.getId());
                    recruitmentCriterionDto.setPreference(recruitmentCriterion.getPreference());
                    recruitmentCriterionDto.setThreshold(recruitmentCriterion.getThreshold());
                    return recruitmentCriterionDto;
                })
                .toList();

                recruitmentDto.setRecruitmentCriteria(recruitmentCriteria);

        return recruitmentDto;
    }
    
}
