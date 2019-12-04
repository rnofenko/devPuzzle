package rn.puzzle.wip;

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

    private void solve(int[] a, int d, int[] exp) {
        int[] r = rotLeft(a, d);
        Assert.assertArrayEquals(exp, r);
    }

    private int[] rotLeft(int[] a, int d) {
        int len = a.length;
        d = d % len;
        int prev = a[d - 1];
        for (int i = 0; i < len; i++) {
            a[i] = a[(i + d) % len];
        }
        a[len - 1] = last;
        return a;
    }

    private int[] rotLeft2(int[] a, int d) {
        int len = a.length;
        d = d % len;
        int last = a[d - 1];
        for (int i = 0; i < len; i++) {
            a[i] = a[(i + d) % len];
        }
        a[len - 1] = last;
        return a;
    }
}
