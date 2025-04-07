package com.recruitment_optimizer.candidateevaluation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruitment_optimizer.candidateevaluation.dto.model.CriterionDto;
import com.recruitment_optimizer.candidateevaluation.dto.model.NumericCriterionDto;
import com.recruitment_optimizer.candidateevaluation.model.NumericCriterion;
import com.recruitment_optimizer.candidateevaluation.service.criterion.numeric.NumericCriterionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@Tag(name = "Numeric Criterion", description = "API for managing numeric criteria")
@RestController
@RequestMapping("/api/v1/criteria/numeric")
public class NumericCriterionController {
    
    private final NumericCriterionService service;

    public NumericCriterionController (NumericCriterionService service) { 
        this.service = service;
    }

    @PutMapping("/{id}")
    public ResponseEntity<NumericCriterion> upate (
        @RequestBody NumericCriterionDto criterion
        ) {

        
        NumericCriterion updatedCriterion = this.service.update(criterion);
        
        return ResponseEntity.ok(updatedCriterion);
    }

    @Operation(summary = "Fetch numeric criterion by ID", description = "Fetch numeric criterion details by ID")
    @GetMapping("/{id}")
    public ResponseEntity<CriterionDto> fetchById (@PathVariable String id) {
        
        CriterionDto criterion = this.service.fetchById(id);
        
        return ResponseEntity.ok(criterion);
    }   
}
