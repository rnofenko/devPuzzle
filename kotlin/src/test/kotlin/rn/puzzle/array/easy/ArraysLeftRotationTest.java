package rn.puzzle.array.easy;

import org.junit.Assert;
import org.junit.Test;

public class ArraysLeftRotationTest {

    @Test
    public void test1() {
        solve(new int[] { 1, 2, 3 }, 1, new int[] { 2, 3, 1 });
    }

    @Test
    public void test2() {
        solve(new int[] { 1, 2, 3, 4, 5 }, 4, new int[] { 5, 1, 2, 3, 4 });
    }

    @Test
    public void test3() {
        solve(new int[] { 1, 2, 3 }, 0, new int[] { 1, 2, 3 });
        solve(new int[] { 1, 2, 3 }, 1, new int[] { 2, 3, 1 });
        solve(new int[] { 1, 2, 3 }, 2, new int[] { 3, 1, 2 });
    }

    private void solve(int[] a, int d, int[] exp) {
        int[] r = rotLeft(a, d);
        Assert.assertArrayEquals(exp, r);
    }

    private int[] rotLeft(int[] a, int d) {
        int len = a.length;
        int[] b = new int[len];
        d = d % len;
        for (int i = 0; i < len; i++) {
            int shift = (i + d) % len;
            b[i] = a[shift];
        }
        return b;
    }
}
