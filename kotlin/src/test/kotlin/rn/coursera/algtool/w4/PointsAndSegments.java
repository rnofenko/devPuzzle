package rn.coursera.algtool.w4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PointsAndSegments {
    public static int[] fastCountSegments(int[] starts, int[] ends, int[] inputPoints) {
        int[] points = inputPoints.clone();
        Arrays.sort(starts);
        Arrays.sort(ends);
        Arrays.sort(points);
        int i = 0;
        int j = 0;
        int count = 0;
        Map<Integer, Integer> counts = new HashMap<>();
        for (int p : points) {
            while (i < starts.length && starts[i] <= p) {
                i++;
                count++;
            }
            while (j < ends.length && ends[j] < p) {
                j++;
                count--;
            }
            counts.put(p, count);
        }

        int[] res = new int[points.length];
        for (int k = 0; k < inputPoints.length; k++) {
            res[k] = counts.get(inputPoints[k]);
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //use fastCountSegments
        int[] cnt = fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }
}

