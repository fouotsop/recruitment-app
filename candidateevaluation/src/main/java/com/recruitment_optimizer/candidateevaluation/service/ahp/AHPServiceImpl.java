package com.recruitment_optimizer.candidateevaluation.service.ahp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.recruitment_optimizer.candidateevaluation.lib.ahp.MatrixNormalizer;
import com.recruitment_optimizer.candidateevaluation.lib.ahp.PairwiseComparer;
import com.recruitment_optimizer.candidateevaluation.lib.ahp.WeightCalculator;
import com.recruitment_optimizer.candidateevaluation.model.Recruitment;
import com.recruitment_optimizer.candidateevaluation.model.RecruitmentCriterion;
import com.recruitment_optimizer.candidateevaluation.service.recruitment.RecruitmentService;

import jakarta.transaction.Transactional;

@Service
public class AHPServiceImpl implements AHPService {

    private final RecruitmentService recruitmentService;

    public AHPServiceImpl(RecruitmentService recruitmentService) {
        this.recruitmentService = recruitmentService;
    }


    @Override
    @Transactional
    public double calculateAHPWeight(String recruitmentId) {


        Recruitment recruitment = recruitmentService.findById(recruitmentId);

        List<RecruitmentCriterion> criteria = recruitment.getRecruitmentCriteria();

        if (criteria.size() <= 2) {
            throw new IllegalArgumentException("At least 3 criteria are required for AHP calculation.");
        }

        PairwiseComparer comparer = new PairwiseComparer(criteria);
        comparer.generatePairwiseComparisonMatrix();

        double[][] pairwiseMatrix = comparer.getPairwiseComparismMatrix();

        MatrixNormalizer normalizer = new MatrixNormalizer(pairwiseMatrix);

        double[][] normalizedMatrix = normalizer.normalizeMatrix();

        WeightCalculator calculator = new WeightCalculator(normalizedMatrix);
        calculator.calculateWeights();

        double[] weights = calculator.getWeights();

        for (RecruitmentCriterion  recruitmentCriterion  : recruitment.getRecruitmentCriteria()) {
            int index = criteria.indexOf(recruitmentCriterion);
            recruitment.getRecruitmentCriteria().get(index).setWeight(weights[index]);
            
        }

        return 0;
    }

    @Override
    public double calculateAhpScores(String recruitmentId) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
