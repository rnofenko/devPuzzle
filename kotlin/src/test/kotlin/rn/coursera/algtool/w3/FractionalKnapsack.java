package rn.coursera.algtool.w3;

import java.util.Arrays;
import java.util.Scanner;

public class FractionalKnapsack {
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double[] rates = new double[values.length];
        Integer[] indexes = new Integer[values.length];
        for (int i = 0; i < rates.length; i++) {
            rates[i] = values[i] * 1.0 / weights[i];
            indexes[i] = i;
        }
        Arrays.sort(indexes, (o1, o2) -> Double.compare(rates[o2], rates[o1]));

        int totalWeight = 0;
        double totalValue = 0;
        int i = 0;
        while (totalWeight < capacity && i < weights.length) {
            int idx = indexes[i];
            int available = capacity - totalWeight;
            int weight = Math.min(weights[idx], available);
            double rate = rates[idx];
            totalValue += rate * weight;
            totalWeight += weight;

            i++;
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }
} 
