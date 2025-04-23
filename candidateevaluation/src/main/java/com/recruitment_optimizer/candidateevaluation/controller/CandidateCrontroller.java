package com.recruitment_optimizer.candidateevaluation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruitment_optimizer.candidateevaluation.dto.model.CandidateDto;
import com.recruitment_optimizer.candidateevaluation.dto.request.Login;
import com.recruitment_optimizer.candidateevaluation.model.Candidate;
import com.recruitment_optimizer.candidateevaluation.service.candidate.CandidateService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Candidate", description = "API for managing candidates")
@RestController
@RequestMapping("/api/v1/candidates")
public class CandidateCrontroller {
    
    private final CandidateService candidateService;

    public CandidateCrontroller(CandidateService candidateService) {
        this.candidateService = candidateService;
    }


    @Operation(summary = "Create a new candidate", description = "Create a new candidate with the provided details")
    @PostMapping("")
    public ResponseEntity<Candidate> create(@RequestBody Candidate candidate) {

        Candidate created = candidateService.create(candidate);

        return ResponseEntity.ok().body(created);
    }

    @Operation(summary = "Get a candidate by ID", description = "Retrieve a candidate's details using their ID")
    @GetMapping("/{id}")
    public ResponseEntity<CandidateDto> findById(@PathVariable String id) {

        CandidateDto candidate = candidateService.fetchById(id);

        return ResponseEntity.ok().body(candidate);

    }

    @Operation(summary = "Login with candidate email and password", description = "Retrieve a candidate's details using their email")
    @PostMapping("/login")
    public ResponseEntity<Candidate> login (@RequestBody Login login) {

        Candidate candidate = candidateService.login(login.getEmail(), login.getPassword());
        return ResponseEntity.ok(candidate);
    }
    

}
