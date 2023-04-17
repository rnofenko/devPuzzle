package rn.puzzle.string.medium;

import org.junit.Assert;
import org.junit.Test;

//https://leetcode.com/problems/flip-string-to-monotone-increasing/
public class FlipStringToMonotoneIncreasingTest {
    public int minFlipsMonoIncr(String s) {
        int count0 = 0;
        int count1 = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                count1 = Math.min(count1, count0);
                count0++;
            } else {
                count1++;
            }
        }
        return Math.min(count0, count1);
    }

    @Test
    public void test1() {
        Assert.assertEquals(1, minFlipsMonoIncr("00110"));
        Assert.assertEquals(2, minFlipsMonoIncr("010110"));
        Assert.assertEquals(2, minFlipsMonoIncr("00011000"));
    }
}
