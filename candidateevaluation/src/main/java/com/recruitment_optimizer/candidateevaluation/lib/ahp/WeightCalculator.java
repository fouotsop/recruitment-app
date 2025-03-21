package com.recruitment_optimizer.candidateevaluation.lib.ahp;

public class WeightCalculator {

    private final double[][] normalizedMatrix;

    private double[] weights;

    public WeightCalculator(double[][] normalizedMatrix) {
        this.normalizedMatrix = normalizedMatrix;
    }

    public double[] getWeights() {
        return weights;
    }

    public void calculateWeights() {
        int n = normalizedMatrix.length;
        weights = new double[n];

        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum += normalizedMatrix[i][j];
            }
            weights[i] = sum / n;
        }
    }

}
