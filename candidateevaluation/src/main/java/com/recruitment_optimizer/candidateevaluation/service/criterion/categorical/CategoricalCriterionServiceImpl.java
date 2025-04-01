package com.recruitment_optimizer.candidateevaluation.service.criterion.categorical;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.recruitment_optimizer.candidateevaluation.dto.model.CriterionDto;
import com.recruitment_optimizer.candidateevaluation.mapper.criterion.CriterionMapper;
import com.recruitment_optimizer.candidateevaluation.model.CategoricalCriterion;
import com.recruitment_optimizer.candidateevaluation.model.LabeledValue;
import com.recruitment_optimizer.candidateevaluation.repository.criterion.categorical.CategoricalCriterionRepository;
import com.recruitment_optimizer.candidateevaluation.repository.labeledvalue.LabeledValueRepository;

@Service
public class CategoricalCriterionServiceImpl implements CategoricalCriterionService {

    private final CategoricalCriterionRepository categoricalCriterionRepository;
    private final LabeledValueRepository labeledValueRepository;

    private final CriterionMapper mapper;

    public CategoricalCriterionServiceImpl(CriterionMapper mapper, CategoricalCriterionRepository categoricalCriterionRepository, LabeledValueRepository repository) {
        this.mapper = mapper;
        this.categoricalCriterionRepository = categoricalCriterionRepository;
        this.labeledValueRepository = repository;
    }

    @Override
    public CriterionDto fetchById(String id) {
        CategoricalCriterion criterion = categoricalCriterionRepository.fetchById(id);
        CriterionDto dto = mapper.toDto(criterion);
        return dto;

    }

    @Override
    public LabeledValue addLabeledValue(LabeledValue labeledValue) {

        CategoricalCriterion criterion = this.categoricalCriterionRepository.findById(labeledValue.getCategoricalCriterion().getId());

        labeledValue.setCategoricalCriterion(criterion);
        labeledValue.setId(UUID.randomUUID().toString());

        labeledValue = labeledValueRepository.save(labeledValue);

        return labeledValue;
    }
    
}
