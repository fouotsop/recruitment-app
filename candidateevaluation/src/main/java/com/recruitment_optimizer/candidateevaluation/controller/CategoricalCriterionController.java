package com.recruitment_optimizer.candidateevaluation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruitment_optimizer.candidateevaluation.dto.model.CriterionDto;
import com.recruitment_optimizer.candidateevaluation.model.CategoricalCriterion;
import com.recruitment_optimizer.candidateevaluation.model.LabeledValue;
import com.recruitment_optimizer.candidateevaluation.service.criterion.categorical.CategoricalCriterionService;

import io.swagger.v3.oas.annotations.tags.Tag;



@Tag(name = "Categorical Criterion", description = "API for managing categorical criteria")
@RestController
@RequestMapping("/api/v1/criteria/categorical")
public class CategoricalCriterionController {
    
    private final CategoricalCriterionService service;

    public CategoricalCriterionController(CategoricalCriterionService service) {
        this.service = service;
    }

    @PostMapping("/{id}/labeled-values")
    public ResponseEntity<LabeledValue>  addLabeledValue (@PathVariable String id, @RequestBody LabeledValue  labeledValue) {
        
        labeledValue.setCategoricalCriterion(new CategoricalCriterion());
        labeledValue.getCategoricalCriterion().setId(id);
        this.service.addLabeledValue(labeledValue);

        return ResponseEntity.ok().body(labeledValue);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriterionDto> fetchById (@PathVariable String id) {

        CriterionDto criterion = this.service.fetchById(id);

        return ResponseEntity.ok(criterion);
    }
    
    
}
