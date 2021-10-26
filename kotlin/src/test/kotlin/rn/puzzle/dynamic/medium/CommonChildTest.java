package rn.puzzle.dynamic.medium;

import org.junit.Assert;
import org.junit.Test;

public class CommonChildTest {
    @Test
    public void test1() {
        test("ABCDEF", "FBDAMN", 2);
    }

    @Test
    public void test2() {
        test("SHINCHAN", "NOHARAAA", 3);
    }

    @Test
    public void test3() {
        test("AA", "BB", 0);
    }

    @Test
    public void test4() {
        test("HARRY", "SALLY", 2);
    }

    static int commonChild(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int[] memoCurr = new int[len2];
        int[] memoPrev;
        for (int i1 = 0; i1 < len1; i1++) {
            memoPrev = memoCurr;
            memoCurr = new int[len2];
            char c1 = s1.charAt(i1);
            for (int i2 = 0; i2 < len2; i2++) {
                if (s2.charAt(i2) == c1) {
                    int diag = i2 > 0 && i1 > 0 ? memoPrev[i2 - 1] : 0;
                    memoCurr[i2] = diag + 1;
                } else {
                    int left = i2 > 0 ? memoCurr[i2 - 1] : 0;
                    memoCurr[i2] = Math.max(left, memoPrev[i2]);
                }
            }
        }
        return memoCurr[len2 - 1];
    }

    private void test(String s1, String s2, int expected) {
        Assert.assertEquals(expected, commonChild(s1, s2));
    }
}
