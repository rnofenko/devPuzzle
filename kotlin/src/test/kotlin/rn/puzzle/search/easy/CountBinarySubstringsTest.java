package rn.puzzle.search.easy;

import org.junit.Assert;
import org.junit.Test;

//https://leetcode.com/problems/count-binary-substrings/
public class CountBinarySubstringsTest {
    public int countBinarySubstrings(String s) {
        int total = 0;
        int currentCount = 0;
        int prevCount = 0;
        char currentC  = s.charAt(0);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == currentC) {
                currentCount++;
                if (currentCount <= prevCount) {
                    total++;
                }
            } else {
                prevCount = currentCount;
                currentCount = 1;
                currentC = c;
                total++;
            }
        }
        return total;
    }

    @Test
    public void test1() {
        Assert.assertEquals(6, countBinarySubstrings("00110011"));
        Assert.assertEquals(4, countBinarySubstrings("10101"));
    }
}
