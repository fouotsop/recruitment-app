package com.recruitment_optimizer.candidateevaluation.lib.ahp;

import java.util.List;

import com.recruitment_optimizer.candidateevaluation.model.Criterion;

public class PairwiseComparer {

    private final List<Criterion> criteria;

    private double[][] pairwiseComparismMatrix;

    public PairwiseComparer(List<Criterion> criteria) {
        this.criteria = criteria;
    }

    public double[][] getPairwiseComparismMatrix() {
        return pairwiseComparismMatrix;
    }

    public void generatePairwiseComparismMatrix() {
        int n = criteria.size();
        pairwiseComparismMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            pairwiseComparismMatrix[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                double comparism = criteria.get(i).getPreference() / criteria.get(j).getPreference();
                pairwiseComparismMatrix[i][j] = comparism;
                pairwiseComparismMatrix[j][i] = 1 / comparism;
            }
        }
    }

}
