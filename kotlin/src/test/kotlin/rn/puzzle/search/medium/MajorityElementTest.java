package rn.puzzle.search.medium;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.StrConverter;

//https://leetcode.com/problems/majority-element/
public class MajorityElementTest {
    public int majorityElement(int[] nums) {
        int x = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (x == nums[i]) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                count = 1;
                x = nums[i];
            }
        }

        return x;
    }

    @Test
    public void test1() {
        Assert.assertEquals(2, majorityElement(StrConverter.toIntArray("[2,2,1,1,1,2,2]")));
    }
}
