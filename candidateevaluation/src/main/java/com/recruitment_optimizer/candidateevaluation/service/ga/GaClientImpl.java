package com.recruitment_optimizer.candidateevaluation.service.ga;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GaClientImpl implements GaClient {

    private final RestTemplate restTemplate;

    GaClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<?> recruit(String recruitmentId) {

        final String uriString = "https://genetic-algorithm-api.onrender.com/optimize/{id_recruitment}";

        final String uri = uriString.replace("{id_recruitment}", recruitmentId);

        ResponseEntity<?> response = restTemplate.getForEntity(uri, Object.class);

        return response;
    }
    
}
