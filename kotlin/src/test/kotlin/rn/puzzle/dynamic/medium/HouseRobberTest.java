package rn.puzzle.dynamic.medium;

import org.junit.Assert;
import org.junit.Test;

//https://leetcode.com/problems/house-robber/
public class HouseRobberTest {
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        int[] opt = new int[nums.length + 1];
        int idx = nums.length - 1;
        opt[idx] = nums[idx--];
        opt[idx] = nums[idx--];
        while (idx >= 0) {
            opt[idx] = nums[idx] + Math.max(opt[idx + 2], opt[idx + 3]);
            idx--;
        }

        return Math.max(opt[0], opt[1]);
    }

    @Test
    public void test1() {
        Assert.assertEquals(4, rob(new int[] {1,2,3,1}));
    }

    @Test
    public void test2() {
        Assert.assertEquals(12, rob(new int[] {2,7,9,3,1}));
    }

    @Test
    public void test3() {
        Assert.assertEquals(14, rob(new int[] {2,7,9,6,1,1}));
    }
}
