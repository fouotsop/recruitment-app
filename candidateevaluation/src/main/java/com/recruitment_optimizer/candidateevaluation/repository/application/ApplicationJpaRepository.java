package com.recruitment_optimizer.candidateevaluation.repository.application;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruitment_optimizer.candidateevaluation.model.Application;
import com.recruitment_optimizer.candidateevaluation.model.CompositeId;

public interface ApplicationJpaRepository extends JpaRepository<Application, CompositeId> {
}
