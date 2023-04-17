package rn.puzzle.dynamic.hard;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

//https://leetcode.com/problems/partition-equal-subset-sum/
public class PartitionEqualSubsetSumTest {
    public boolean canPartition(int[] nums) {
        if (nums.length == 0) {
            return false;
        }
        int totalSum = Arrays.stream(nums).sum();
        if (totalSum % 2 == 1) {
            return false;
        }

        int target = totalSum / 2;
        boolean[] dp = new boolean[target + 1];
        for (int v : nums) {
            if (v > target) {
                continue;
            }
            int endLoop = target - v;
            for (int i = endLoop; i >= 0; i--) {
                if (dp[i]) {
                    dp[i + v] = true;
                }
            }
            dp[v] = true;
        }

        return dp[target];
    }

    @Test
    public void test1() {
        Assert.assertTrue(canPartition(new int[] {1,5,11,5}));
    }

    @Test
    public void test2() {
        Assert.assertFalse(canPartition(new int[] {2,2,3,5}));
    }
}
