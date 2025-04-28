package com.recruitment_optimizer.candidateevaluation.repository.candidate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.recruitment_optimizer.candidateevaluation.model.Candidate;

public interface CandidateJpaRepository extends JpaRepository<Candidate, String> {

    Candidate findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    Candidate findByEmail(String email);

    @Query("SELECT a.candidate FROM Application a WHERE a.recruitment.id = :recruitmentId")
    List<Candidate> findCandidatesByRecruitment(@Param("recruitmentId") String recruitmentId);

}
