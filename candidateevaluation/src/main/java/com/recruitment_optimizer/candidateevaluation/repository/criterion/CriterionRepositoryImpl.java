package com.recruitment_optimizer.candidateevaluation.repository.criterion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.recruitment_optimizer.candidateevaluation.model.Criterion;

@Repository
public class CriterionRepositoryImpl implements CriterionRepository {

    private final CriterionJpaRepository criterionJpaRepository;

    public CriterionRepositoryImpl(CriterionJpaRepository criterionJpaRepository) {
        this.criterionJpaRepository = criterionJpaRepository;
    }


    public Criterion save(Criterion criterion) {
        return criterionJpaRepository.save(criterion);
    }

    public Criterion findById(String id) {
        return criterionJpaRepository.findById(id).orElse(null);
    }

    public Page<Criterion> findAll(Pageable pageable) {
        return criterionJpaRepository.findAll(pageable);
    }

}
