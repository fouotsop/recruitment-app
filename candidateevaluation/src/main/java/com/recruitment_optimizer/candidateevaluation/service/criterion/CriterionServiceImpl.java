package com.recruitment_optimizer.candidateevaluation.service.criterion;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.recruitment_optimizer.candidateevaluation.model.Criterion;
import com.recruitment_optimizer.candidateevaluation.repository.criterion.CriterionRepository;

@Service
public class CriterionServiceImpl implements CriterionService {

    private final CriterionRepository  criterionRepository;

    public CriterionServiceImpl(CriterionRepository criterionRepository) {
        this.criterionRepository = criterionRepository;
    }


    public Criterion create(Criterion criterion) {
        criterion.setId(UUID.randomUUID().toString());
        return criterionRepository.save(criterion);
    }

    public Criterion update(Criterion criterion) {
        return criterionRepository.save(criterion);
    }

    public Page<Criterion> findAll(Pageable pageable) {
        return criterionRepository.findAll(pageable);
    }


    
}
