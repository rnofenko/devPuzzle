package rn.puzzle.structure.heap.median;

import org.junit.Test;
import rn.tool.Ass;
import rn.tool.StrConverter;

import java.util.Comparator;
import java.util.PriorityQueue;

@SuppressWarnings("ConstantConditions")
public class FindTheRunningMedianTest {
    static double[] runningMedian(int[] input) {
        PriorityQueue<Integer> left = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> right = new PriorityQueue<>(Integer::compareTo);
        double[] result = new double[input.length];
        result[0] = input[0];
        left.add(input[0]);
        for (int i = 1; i < input.length; i++) {
            int a = input[i];
            if (left.peek() < a) {
                right.add(a);
            } else {
                left.add(a);
            }

            while (left.size() - right.size() > 1) {
                right.add(left.poll());
            }
            while (right.size() - left.size() > 1) {
                left.add(right.poll());
            }
            result[i] = getMedian(left, right);
        }
        return result;
    }

    static double getMedian(PriorityQueue<Integer> left, PriorityQueue<Integer> right) {
        if (left.size() > right.size()) {
            return left.peek();
        }
        if (right.size() > left.size()) {
            return right.peek();
        }
        int sum = left.peek() + right.peek();
        return sum == 0 ? 0: Math.round(sum / 0.2) / 10.0;
    }

    @Test
    public void test1() {
        double[] result = runningMedian(StrConverter.toIntArray("12 4 5 3 8 7"));
        Ass.assertFloatArrays("12 8 5 4.5 5 6", result);
    }
}
