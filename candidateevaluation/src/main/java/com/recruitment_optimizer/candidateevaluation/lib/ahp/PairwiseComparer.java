package com.recruitment_optimizer.candidateevaluation.lib.ahp;

import java.util.List;

import com.recruitment_optimizer.candidateevaluation.model.RecruitmentCriterion;

public class PairwiseComparer {

    private final List<RecruitmentCriterion> recruitmentCriteria;

    private double[][] pairwiseComparismMatrix;

    public PairwiseComparer(List<RecruitmentCriterion> criteria) {
        this.recruitmentCriteria = criteria;
    }

    public double[][] getPairwiseComparismMatrix() {
        return pairwiseComparismMatrix;
    }

    public void generatePairwiseComparismMatrix() {
        int n = recruitmentCriteria.size();
        pairwiseComparismMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            pairwiseComparismMatrix[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                double comparism = recruitmentCriteria.get(i).getPreference() / recruitmentCriteria.get(j).getPreference();
                pairwiseComparismMatrix[i][j] = comparism;
                pairwiseComparismMatrix[j][i] = 1 / comparism;
            }
        }
    }

}
