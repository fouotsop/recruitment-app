package com.recruitment_optimizer.candidateevaluation.repository.application;

import org.springframework.stereotype.Repository;

import com.recruitment_optimizer.candidateevaluation.model.Application;
import com.recruitment_optimizer.candidateevaluation.model.CompositeId;

@Repository
public class ApplicationRepositoryImpl implements ApplicationRepository {
    
    private final ApplicationJpaRepository applicationJpaRepository;

    public ApplicationRepositoryImpl(ApplicationJpaRepository applicationJpaRepository) {
        this.applicationJpaRepository = applicationJpaRepository;
    }

    @Override
    public Application save(Application application) {
        return applicationJpaRepository.save(application);
    }

    @Override
    public Application findById(CompositeId id) {
        return applicationJpaRepository.findById(id).orElseThrow();
    }
    
}
