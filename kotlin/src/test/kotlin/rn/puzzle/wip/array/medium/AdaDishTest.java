package rn.puzzle.wip.array.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class AdaDishTest {
    @Test
    public void test1() {
        int res = solve(new int[] {9,2,5,4});
        Assert.assertEquals(11, res);
    }

    public int solve(int[] a) {
        Arrays.sort(a);
        int left = 0;
        int right = 0;
        for (int i = a.length - 1; i >= 0; i--) {
            if (left < right) {
                left += a[i];
            } else {
                right += a[i];
            }
        }
        return Math.max(left, right);
    }
}
