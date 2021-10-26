package rn.puzzle.structure.array.easy;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.StrConverter;

public class LargestNumberAtLeastTwiceOthersTest {
    public int dominantIndex(int[] nums) {
        if (nums.length < 1) {
            return -1;
        }
        if (nums.length == 1) {
            return 0;
        }
        int max1 = nums[0] > nums[1] ? 0 : 1;
        int max2 = max1 == 0 ? 1 : 0;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] > nums[max1]) {
                max2 = max1;
                max1 = i;
            } else if (nums[i] > nums[max2]) {
                max2 = i;
            }
        }
        return nums[max1] >= nums[max2] * 2 ? max1 : -1;
    }

    @Test
    public void test1() {
        Assert.assertEquals(0, dominantIndex(StrConverter.toIntArray("[1]")));
    }
}
