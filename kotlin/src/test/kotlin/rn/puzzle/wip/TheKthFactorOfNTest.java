package rn.puzzle.wip;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TheKthFactorOfNTest {
    public int kthFactor(int n, int k) {
        int max = (int)Math.sqrt(n);
        List<Integer> bigFactors = new ArrayList<>();
        int f = 0;
        for (int i = 1; i <= max; i++) {
            if (n % i == 0) {
                int bigFactor = n / i;
                if (bigFactor != i) {
                    bigFactors.add(bigFactor);
                }
                f++;
                if (f == k) {
                    return i;
                }
            }
        }

        int bigK = k - f;
        return  (bigK <= bigFactors.size()) ? bigFactors.get(bigFactors.size() - bigK) : -1;
    }

    @Test
    public void test2() {
        Assert.assertEquals(-1, kthFactor(4, 4));
    }

    @Test
    public void test1() {
        Assert.assertEquals(1, kthFactor(30, 1));
        Assert.assertEquals(2, kthFactor(30, 2));
        Assert.assertEquals(3, kthFactor(30, 3));
        Assert.assertEquals(5, kthFactor(30, 4));
        Assert.assertEquals(6, kthFactor(30, 5));
        Assert.assertEquals(10, kthFactor(30, 6));
        Assert.assertEquals(15, kthFactor(30, 7));
        Assert.assertEquals(30, kthFactor(30, 8));
        Assert.assertEquals(-1, kthFactor(30, 9));
    }
}
