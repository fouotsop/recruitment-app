package com.recruitment_optimizer.candidateevaluation.lib.ahp;

public class MatrixNormalizer {

    private final double[][] pairWiseComparismMatrix;

    private double[][] normalizedMatrix;

    public MatrixNormalizer(double[][] matrix) {
        this.pairWiseComparismMatrix = matrix;
    }

    public double[][] getNormalizedMatrix() {
        return normalizedMatrix;
    }


    private double[] columnSums() {
        int n = pairWiseComparismMatrix.length;
        double[] sums = new double[n];

        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum += pairWiseComparismMatrix[j][i];
            }
            sums[i] = sum;
        }

        return sums;
    }

    public double[][] normalizeMatrix() {
        int n = pairWiseComparismMatrix.length;
        double[] sums = columnSums();
        normalizedMatrix = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                normalizedMatrix[j][i] = pairWiseComparismMatrix[j][i] / sums[i];
            }
        }

        return normalizedMatrix;
    }

    
}
