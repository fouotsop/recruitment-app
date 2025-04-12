package com.recruitment_optimizer.candidateevaluation.service.application;

import org.springframework.stereotype.Service;

import com.recruitment_optimizer.candidateevaluation.dto.request.CreateApplication;
import com.recruitment_optimizer.candidateevaluation.model.Application;

@Service
public interface ApplicationService {
    
    public Application apply(CreateApplication application);
}
