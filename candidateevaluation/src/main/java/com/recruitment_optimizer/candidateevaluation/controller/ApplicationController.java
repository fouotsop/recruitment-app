package com.recruitment_optimizer.candidateevaluation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruitment_optimizer.candidateevaluation.dto.request.CreateApplication;
import com.recruitment_optimizer.candidateevaluation.model.Application;
import com.recruitment_optimizer.candidateevaluation.service.application.ApplicationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Application", description = "API for managing recruitment applications")
@RequestMapping("/api/v1/applications")
@RestController
public class ApplicationController {


    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }


    @Operation(summary = "Apply for a recruitment", description = "Submit an application for a specific recruitment")
    @PostMapping("")
    public ResponseEntity<Application> apply (@RequestBody CreateApplication create) {

        Application application = this.applicationService.apply(create);

        return ResponseEntity.ok(application);


    }

    
}
