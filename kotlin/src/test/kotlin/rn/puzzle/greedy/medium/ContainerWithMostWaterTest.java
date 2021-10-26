package rn.puzzle.greedy.medium;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.StrConverter;

//https://leetcode.com/problems/container-with-most-water/
public class ContainerWithMostWaterTest {
    public int maxArea(int[] height) {
        int leftIdx = 0;
        int rightIdx = height.length - 1;
        int volume = 0;

        while (leftIdx <= rightIdx) {
            int leftHeight = height[leftIdx];
            int rightHeight = height[rightIdx];
            volume = Math.max(volume, Math.min(height[leftIdx], height[rightIdx]) * (rightIdx - leftIdx));
            if (leftHeight <= rightHeight) {
                leftIdx++;
            } else  {
                rightIdx--;
            }
        }
        return volume;
    }

    @Test
    public void test1() {
        Assert.assertEquals(49, maxArea(StrConverter.toIntArray("[1,8,6,2,5,4,8,3,7]")));
    }
}
