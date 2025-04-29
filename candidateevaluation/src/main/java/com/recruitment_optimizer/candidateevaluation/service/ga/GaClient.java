package com.recruitment_optimizer.candidateevaluation.service.ga;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface GaClient {
    ResponseEntity<?> recruit(String recruitmentId);
}
