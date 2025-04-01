package com.recruitment_optimizer.candidateevaluation.service.criterion.numeric;

import org.springframework.stereotype.Service;

import com.recruitment_optimizer.candidateevaluation.dto.model.NumericCriterionDto;
import com.recruitment_optimizer.candidateevaluation.mapper.criterion.CriterionMapper;
import com.recruitment_optimizer.candidateevaluation.model.NumericCriterion;
import com.recruitment_optimizer.candidateevaluation.repository.criterion.numeric.NumericCriterionRepository;

@Service
public class NumericCriterionServiceImpl implements NumericCriterionService {

    private final NumericCriterionRepository numericCriterionRepository;

    private final CriterionMapper mapper;

    public NumericCriterionServiceImpl(CriterionMapper mapper, NumericCriterionRepository numericCriterionRepository) {
        this.numericCriterionRepository = numericCriterionRepository;
        this.mapper = mapper;
    }

    @Override
    public NumericCriterion getById(String id) {
        return numericCriterionRepository.findById(id);
    }

    @Override
    public NumericCriterion update(NumericCriterionDto criterion) {
        NumericCriterion existing = this.numericCriterionRepository.findById(criterion.getId());

        if (criterion.getMax() != null) {
            existing.setMaxValue(criterion.getMax());
        }

        if (criterion.getMin() != null) {
            existing.setMinValue(criterion.getMin());
        }

        existing = this.numericCriterionRepository.save(existing); 

        return existing;
    }
    
}
