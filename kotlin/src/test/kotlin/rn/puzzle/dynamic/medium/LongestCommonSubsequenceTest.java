package rn.puzzle.dynamic.medium;

import org.junit.Assert;
import org.junit.Test;

//https://leetcode.com/problems/longest-common-subsequence/
public class LongestCommonSubsequenceTest {

    public int longestCommonSubsequence(String text1, String text2) {
        int[] current;
        int[] prev = null;
        for (int i = 0; i < text1.length(); i++) {
            current = new int[text2.length()];
            char c1 = text1.charAt(i);
            for (int j = 0; j < text2.length(); j++) {
                char c2 = text2.charAt(j);
                if (c1 == c2) {
                    current[j] = 1 + (i > 0 && j > 0 ? prev[j - 1] : 0);
                } else {
                    current[j] = Math.max((i > 0? prev[j] : 0), (j > 0 ? current[j - 1] : 0));
                }
            }

            prev = current;
        }
        return prev == null ? 0 : prev[text2.length() - 1];
    }

    @Test
    public void test1() {
        run("abcde", "ace", 3);
    }

    @Test
    public void test2() {
        run("ace", "ace", 3);
    }

    @Test
    public void test3() {
        run("acr", "def", 0);
    }

    private void run(String t1, String t2, int size) {
        int res = longestCommonSubsequence(t1, t2);
        Assert.assertEquals(size, res);
    }
}
