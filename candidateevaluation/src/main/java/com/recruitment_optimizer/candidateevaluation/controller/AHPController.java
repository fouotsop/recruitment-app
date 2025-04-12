package com.recruitment_optimizer.candidateevaluation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruitment_optimizer.candidateevaluation.service.ahp.AHPService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@Tag(name = "AHP", description = "Analytic Hierarchy Process (AHP) management")
@RestController
@RequestMapping("/api/v1/ahp")
public class AHPController {


    private final AHPService ahpService;


    public AHPController(AHPService ahpService) {
        this.ahpService = ahpService;
    }


    @Operation(summary = "Calculate AHP weights", description = "Calculate AHP weights for the given criteria")
    @PostMapping("{recruitmentId}/calculate-weights")
    public ResponseEntity<?> calculateWeights(String recruitmentId) {

        ahpService.calculateAHPWeight(recruitmentId);

        return ResponseEntity.ok("AHP weights calculated successfully");

    }
    
}
