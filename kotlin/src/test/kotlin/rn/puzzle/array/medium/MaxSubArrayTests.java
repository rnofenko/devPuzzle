package rn.puzzle.array.medium;

import org.junit.Assert;
import org.junit.Test;

public class MaxSubArrayTests {
    public long getMaxSubarraySum(int[] array){
        long currentMax = Long.MIN_VALUE;
        long totalMax =  Long.MIN_VALUE;

        for (int value : array) {
            currentMax = Math.max(currentMax, 0) + value;
            totalMax = Math.max(totalMax, currentMax);
        }
        return totalMax;
    }

    @Test
    public void test1() {
//        Assert.assertEquals(15, getMaxSubarraySum(new int[] { 1,2,3,4,5 }));
//        Assert.assertEquals(6, getMaxSubarraySum(new int[] { 1,-2,3,-2,5 }));
//        Assert.assertEquals(5, getMaxSubarraySum(new int[] { 5 }));
        Assert.assertEquals(-7, getMaxSubarraySum(new int[] { -7 }));
    }
}
