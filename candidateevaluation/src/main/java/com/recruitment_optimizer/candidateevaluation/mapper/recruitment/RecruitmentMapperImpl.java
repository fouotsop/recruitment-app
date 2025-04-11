package com.recruitment_optimizer.candidateevaluation.mapper.recruitment;

import org.springframework.stereotype.Service;

import com.recruitment_optimizer.candidateevaluation.dto.model.RecruitmentDto;
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
        recruitmentDto.setRecruitmentCriteria(recruitment.getRecruitmentCriteria());;

        return recruitmentDto;
    }
    
}
