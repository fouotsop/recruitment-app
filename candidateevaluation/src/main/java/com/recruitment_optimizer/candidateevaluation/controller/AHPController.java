package com.recruitment_optimizer.candidateevaluation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruitment_optimizer.candidateevaluation.service.ahp.AHPService;
import com.recruitment_optimizer.candidateevaluation.service.candidate.CandidateService;
import com.recruitment_optimizer.candidateevaluation.service.ga.GaClient;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@Tag(name = "AHP", description = "Analytic Hierarchy Process (AHP) management")
@RestController
@RequestMapping("/api/v1/ahp")
public class AHPController {


    private final AHPService ahpService;
    private final GaClient gaClient;
    private final CandidateService candidateService;


    public AHPController(AHPService ahpService, GaClient gaClient, CandidateService candidateService) {
        this.ahpService = ahpService;
        this.gaClient = gaClient;
        this.candidateService = candidateService;
    }


    @Operation(summary = "Calculate AHP weights", description = "Calculate AHP weights for the given criteria")
    @PostMapping("{recruitmentId}/calculate-weights")
    public ResponseEntity<?> calculateWeights(String recruitmentId) {

        return ResponseEntity.ok("AHP weights calculated successfully");


    }

    @Operation(summary = "Recruit the best team for a job offer", description = "Returns the best team (candidate ids) for a job offer with GA related metrics")
    @PostMapping("{recruitmentId}/optimize")
    public ResponseEntity<?> getBestTeam(@PathVariable String recruitmentId) {

        ahpService.calculateAHPWeight(recruitmentId);

        ahpService.calculateAhpScores(recruitmentId);

        ResponseEntity<?> response = gaClient.recruit(recruitmentId);

        return response;
    }
    
    /*@Operation(summary = "Recruit the best team for a job offer", description = "Returns the best team for a job offer")
    @PostMapping("{recruitmentId}/recruit")
    public ResponseEntity<?> calculateScoress(String recruitmentId) {

        ahpService.calculateAHPWeight(recruitmentId);

        ahpService.calculateAhpScores(recruitmentId);

        ResponseEntity<?> response = gaClient.recruit(recruitmentId);
        
        List<String> teamIds = new ArrayList<>();
        if (response.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            ObjectMapper mapper = new ObjectMapper();
            try {

                JsonNode node = mapper.readTree(response.getBody().toString());
                if(node != null) {
                    JsonNode team = node.get("optimal_team");
                    if (team != null && team.isArray()) {

                        for (JsonNode t  : team) {
                            teamIds.add(t.asText());
                        }
                    }

                List<Candidate> candidates = new ArrayList<>();
                    for (String  id  : teamIds) {
                    
                        Candidate candidate = this.candidateService.findById(id);
            
                        candidates.add(candidate);
                    }
                }

                Map<String, List<String>> res = new HashMap<>() {{
                    put("optimal_team", teamIds);
                }};
            
            
                return ResponseEntity.ok().body(res);
            
            }
        
            catch (Exception e) {
                return ResponseEntity.internalServerError().build();
            }
        }
        else {
            return ResponseEntity.internalServerError().build();
        }
    }
*/

}
