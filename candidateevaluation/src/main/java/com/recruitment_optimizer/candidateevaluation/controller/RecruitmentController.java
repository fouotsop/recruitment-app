package com.recruitment_optimizer.candidateevaluation.controller;

import java.util.ArrayList;
import java.util.List;
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

import com.recruitment_optimizer.candidateevaluation.dto.model.RecruitmentCriterionDtoC;
import com.recruitment_optimizer.candidateevaluation.dto.model.RecruitmentDto;
import com.recruitment_optimizer.candidateevaluation.dto.request.AddRecruitmentCriterion;
import com.recruitment_optimizer.candidateevaluation.dto.request.CreateRecruitment;
import com.recruitment_optimizer.candidateevaluation.model.CompositeId;
import com.recruitment_optimizer.candidateevaluation.model.Recruitment;
import com.recruitment_optimizer.candidateevaluation.model.RecruitmentCriterion;
import com.recruitment_optimizer.candidateevaluation.service.recruitment.RecruitmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;




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
    public ResponseEntity<Recruitment> create(@RequestBody CreateRecruitment recruitment) {
        
        String id = UUID.randomUUID().toString();

        Recruitment created = new Recruitment();
        created.setId(id);
        created.setTitle(recruitment.getTitle());
        created.setDescription(recruitment.getDescription());
        created.setAvailable(false);
        created.setNumberOfPosts(recruitment.getNumberOfPosts());
        created.setLocation(recruitment.getLocation());
        created.setSalary(recruitment.getSalary());

        List<RecruitmentCriterion> recruitmentCriteria = new ArrayList<>();  

        for (RecruitmentCriterionDtoC dto : recruitment.getRecruitmentCriteria()) {
            RecruitmentCriterion recruitmentCriterion = new RecruitmentCriterion();
            recruitmentCriterion.setId(new CompositeId());
            recruitmentCriterion.getId().setChildId(dto.getRecruitmentId());
            recruitmentCriterion.getId().setParentId(dto.getCriterionId());
            recruitmentCriterion.setPreference(dto.getPreference());
            recruitmentCriterion.setThreshold(dto.getThreshold());
            recruitmentCriteria.add(recruitmentCriterion);
            
        }
        created.setRecruitmentCriteria(recruitmentCriteria);

        created = service.create(created);


        return ResponseEntity.ok().body(created);
    }


    @Operation(summary = "Fetch recruitment by ID", description = "Fetch recruitment details by ID")
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<RecruitmentDto> fetchById(@PathVariable("id") String id) {


        RecruitmentDto recruitment = service.fetchById(id);

        return ResponseEntity.ok().body(recruitment);
    }
    
    @Operation(summary = "Update recruitment by ID", description = "Update recruitment details by ID")
    @PutMapping("/{id}")
    public ResponseEntity<?> update (@PathVariable String id, @RequestBody Recruitment recruitment) {

        recruitment = service.update(recruitment);
        
        return ResponseEntity.ok().body(recruitment);
    }

    @Operation(summary = "Get all recruitments", description = "return all the recruitments")
    @GetMapping("")
    public ResponseEntity<List<Recruitment>> findAll() {
        List<Recruitment> recruitments = service.findAll();
        
        return ResponseEntity.ok().body(recruitments);
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
    

    @Operation(summary = "Add a criterion to a recruitment", description = "Add a criterion to a recruitment")  
    @PostMapping("/{id}/criteria")
    public ResponseEntity<?> addCriterion(@PathVariable String id, @RequestBody List<AddRecruitmentCriterion> criteria) {

        List<RecruitmentCriterion> recruitmentCriteria = new ArrayList<>();
        for (AddRecruitmentCriterion  add  : criteria) {
            RecruitmentCriterion recruitmentCriterion = new RecruitmentCriterion();
            recruitmentCriterion.setId(new CompositeId());
            recruitmentCriterion.getId().setChildId(id);
            recruitmentCriterion.getId().setParentId(add.getCriterionId());
            recruitmentCriterion.setPreference(add.getPreference());
            recruitmentCriterion.setThreshold(add.getThreshold());
            recruitmentCriteria.add(recruitmentCriterion);
            
        }

        Recruitment recruitment = service.addCriterion(id, recruitmentCriteria);
        return ResponseEntity.ok().body(recruitment);
    }

}
