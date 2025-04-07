package com.recruitment_optimizer.candidateevaluation.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recruitment_optimizer.candidateevaluation.dto.model.RecruitmentDto;
import com.recruitment_optimizer.candidateevaluation.model.Recruitment;
import com.recruitment_optimizer.candidateevaluation.service.recruitment.RecruitmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;




@Tag(name = "Recruitment", description = "Recruitment management")
@RestController
@RequestMapping("/api/v1/recruitments")
public class RecruitmentController {

    private final RecruitmentService service;

    public RecruitmentController (RecruitmentService service) {
        this.service = service;
    }
    

    @Operation(summary = "Create a new recruitment", description = "Create a new recruitment with the provided details")
    @PostMapping("")
    public ResponseEntity<Recruitment> create(@RequestBody RecruitmentDto recruitment) {
        
        String id = UUID.randomUUID().toString();

        Recruitment created = new Recruitment();
        created.setId(id);
        created.setTitle(recruitment.getTitle());
        created.setDescription(recruitment.getDescription());
        created.setAvailable(false);
        created.setNumberOfPosts(recruitment.getNumberOfPosts());
        created.setLocation(recruitment.getLocation());
        created.setSalary(recruitment.getSalary());
        created.setRecruitmentCriteria(recruitment.getRecruitmentCriteria());

        created = service.create(created);


        return ResponseEntity.ok().body(created);
    }


    @Operation(summary = "Fetch recruitment by ID", description = "Fetch recruitment details by ID")
    @GetMapping("/{id}")
    public ResponseEntity<RecruitmentDto> fetchById(@PathVariable("id") String id) {



        return null;
    }
    
    @Operation(summary = "Update recruitment by ID", description = "Update recruitment details by ID")
    @PutMapping("/{id}")
    public ResponseEntity<?> update (@PathVariable String id, @RequestBody Recruitment recruitment) {

        recruitment = service.update(recruitment);
        
        return ResponseEntity.ok().body(recruitment);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Recruitment>> search (
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "3") int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Recruitment> results = service.findAll(pageable);
        
        return ResponseEntity.ok().body(results);
    }
    
}
