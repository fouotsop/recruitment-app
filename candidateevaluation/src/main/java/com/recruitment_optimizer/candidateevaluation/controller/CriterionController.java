package com.recruitment_optimizer.candidateevaluation.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.recruitment_optimizer.candidateevaluation.model.CategoricalCriterion;
import com.recruitment_optimizer.candidateevaluation.model.Criterion;
import com.recruitment_optimizer.candidateevaluation.model.LabeledValue;
import com.recruitment_optimizer.candidateevaluation.model.NumericCriterion;
import com.recruitment_optimizer.candidateevaluation.service.criterion.CriterionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;




@RestController
@Tag(name = "Criterion", description = "API for managing criteria")
@RequestMapping("/api/v1/criteria")
public class CriterionController {
    
    private final CriterionService criterionService;


    public CriterionController(CriterionService criterionService) {
        this.criterionService = criterionService;
    }

    @Operation(summary = "Create a categorical criterion", description = "Create a new categorical criterion")
    @PostMapping("/categorical")
    public ResponseEntity<Criterion> createCategorical (
        @RequestParam String name,
        @RequestParam(required = false) String description,
        @RequestParam boolean mandatory,
        @RequestParam String[] labels,
        @RequestParam double[] values
    ) {

        List<LabeledValue> labeledValues = new ArrayList<>();
        for (int i = 0; i < labels.length; i++) {
            labeledValues.add(new LabeledValue(labels[i], values[i]));
        }

        Criterion criterion = new CategoricalCriterion(UUID.randomUUID().toString(), name, description, mandatory, labeledValues);

        criterionService.create(criterion);
        
        final String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/criteria/{id}").buildAndExpand(criterion.getId()).toUriString();
        URI location = URI.create(url);
        return ResponseEntity.created(location).body(criterion);
    }
    

    @Operation(summary = "Create a numeric criterion", description = "Create a new numeric criterion")
    @PostMapping("/numeric")
    public ResponseEntity<Criterion> createNumeric (
        @RequestParam String name,
        @RequestParam(required = false) String description,
        @RequestParam boolean mandatory,
        @RequestParam(required = false) Double min,
        @RequestParam(required = false) Double max

    ) {

        Criterion criterion = new NumericCriterion(UUID.randomUUID().toString(), name, description, mandatory, min, max);

        final String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/criteria/{id}").buildAndExpand(criterion.getId()).toUriString();
        URI location = URI.create(url);
        return ResponseEntity.created(location).body(criterion);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Criterion>> search(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size
        ) {

            Pageable pageable = PageRequest.of(page, size);
            Page<Criterion> criteria = criterionService.findAll(pageable);

            return ResponseEntity.ok().body(criteria);
    }

}
