package com.recruitment_optimizer.candidateevaluation.mapper.recruitment;

import org.springframework.stereotype.Service;

import com.recruitment_optimizer.candidateevaluation.dto.model.RecruitmentDto;
import com.recruitment_optimizer.candidateevaluation.model.Recruitment;

@Service
public interface RecruitmentMapper {
    
    public RecruitmentDto toDto(Recruitment recruitment);

}
