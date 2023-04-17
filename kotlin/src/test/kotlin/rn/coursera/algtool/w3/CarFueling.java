package rn.coursera.algtool.w3;

import java.util.*;
import java.io.*;

public class CarFueling {
    public static int computeMinRefills(int dist, int tank, int[] stops) {
        if (tank >= dist) {
            return 0;
        }
        if (stops.length == 0) {
            return -1;
        }
        int currentIdx = -1;
        int refills = 0;

        while (currentIdx == -1 || stops[currentIdx] + tank < dist) {
            currentIdx = getNextStop(currentIdx, tank, stops);
            if (currentIdx == -1) {
                return -1;
            }
            refills++;
        }

        return refills;
    }

    private static int getNextStop(int currentIdx, int tank, int[] stops) {
        int start = currentIdx == -1 ? 0 : stops[currentIdx];

        int result = stops.length - 1;
        for (int i = currentIdx + 1; i < stops.length; i++) {
            if (stops[i] - start > tank) {
                result = i - 1;
                break;
            }
        }

        return result == currentIdx ? -1 : result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dist = scanner.nextInt();
        int tank = scanner.nextInt();
        int n = scanner.nextInt();
        int[] stops = new int[n];
        for (int i = 0; i < n; i++) {
            stops[i] = scanner.nextInt();
        }

        System.out.println(computeMinRefills(dist, tank, stops));
    }
}
