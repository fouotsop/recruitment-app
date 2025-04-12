package com.recruitment_optimizer.candidateevaluation.repository.application;

import org.springframework.stereotype.Repository;

import com.recruitment_optimizer.candidateevaluation.model.Application;
import com.recruitment_optimizer.candidateevaluation.model.CompositeId;

@Repository
public interface ApplicationRepository {
    
    public Application save(Application application);

    public Application findById(CompositeId id);
}
