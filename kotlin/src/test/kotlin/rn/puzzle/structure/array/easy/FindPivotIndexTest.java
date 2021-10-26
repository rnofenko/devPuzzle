package rn.puzzle.structure.array.easy;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.StrConverter;

import java.util.stream.IntStream;

//https://leetcode.com/problems/find-the-middle-index-in-array/
public class FindPivotIndexTest {
    public int pivotIndex(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }

        int rightSum = IntStream.of(nums).sum() - nums[0];
        int leftSum = 0;

        for (int j = 0; j < nums.length - 1; j++) {
            if (leftSum == rightSum) {
                return j;
            }
            leftSum += nums[j];
            rightSum -= nums[j + 1];
        }

        return leftSum == rightSum ? nums.length - 1 : -1;
    }

    @Test
    public void test1() {
        Assert.assertEquals(3, pivotIndex(StrConverter.toIntArray("[1,7,3,6,5,6]")));
    }

    @Test
    public void test2() {
        Assert.assertEquals(2, pivotIndex(StrConverter.toIntArray("[1,-1,4]")));
    }
}
