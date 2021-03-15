package rn.puzzle.array.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MinimumSumArrayTests {

    // performance for worst case scenario. N - number of elements in nums.
    // worst case for heap is O(log N), average is O(1)
    // insert n into heap - O(N * log N)
    // main loop - O(k * 2 * log N)
    // sum - O(N)
    // total - O(N + k * log N)
    private int minSum(int[] nums, int k) {
        if (nums.length == 0 || k < 1) {
            return 0;
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int num : nums) {
            heap.add(num);
        }

        for (int i = 0; i < k; i++) {
            int val = heap.poll();
            int ceiled = Double.valueOf(Math.round(Math.ceil(val / 2.0))).intValue();
            heap.add(ceiled);
            if (val == 1 || val == 0) {
                break;
            }
        }

        return heap.stream().mapToInt(i -> i).sum();
    }

    @Test
    public void test1() {
        int res = minSum(new int[] {10,20,7}, 4);
        Assert.assertEquals(14, res);
    }

    @Test
    public void test2() {
        int res = minSum(new int[] {-20, 10,20,7}, 4);
        Assert.assertEquals(-6, res);
    }

    @Test
    public void test3() {
        int res = minSum(new int[] {-20, 10, 20, 7}, 1000);
        Assert.assertEquals(-17, res);
    }

    @Test
    public void test4() {
        int res = minSum(new int[] {-20, -10, -20}, 1000);
        Assert.assertEquals(-40, res);
    }
}
