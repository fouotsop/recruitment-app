package com.recruitment_optimizer.candidateevaluation.lib.ahp;

public class ConsistencyVerifier {

    private final double[][] pairWiseComparismMatrix;

    private final double[] weights;

    private double consistencyIndex;

    private final double[] randomlyGenerateIndices = {0, 0, 0.58, 0.90, 1.12, 1.24, 1.32, 1.41, 1.45, 1.49};
    private double consistencyRatio;

    public ConsistencyVerifier(double[][] pairWiseComparismMatrix, double[] weights) {
        this.pairWiseComparismMatrix = pairWiseComparismMatrix;
        this.weights = weights;
    }

    public double getConsistencyIndex() {
        return consistencyIndex;
    }

    public double getConsistencyRatio() {
        return consistencyRatio;
    }

    public void verifyConsistency() {
        int n = pairWiseComparismMatrix.length;
        double[] sums = new double[n];

        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum += pairWiseComparismMatrix[i][j] * weights[j];
            }
            sums[i] = sum;
        }

        double[] consistencyVector = new double[n];
        for (int i = 0; i < n; i++) {
            consistencyVector[i] = sums[i] / weights[i];
        }

        double lambdaMax = 0;
        for (int i = 0; i < n; i++) {
            lambdaMax += consistencyVector[i];
        }

        lambdaMax /= n;

        consistencyIndex = (lambdaMax - n) / (n - 1);

        consistencyRatio = consistencyIndex / randomlyGenerateIndices[n - 1];
    }


    
}
