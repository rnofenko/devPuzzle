package rn.coursera.algtool.w2;

import org.junit.Assert;
import org.junit.Test;

public class FibonacciHugeTest {
    @Test
    public void test1() {
        long res = FibonacciHuge.getFibonacciHugeNaive(9999999999999L, 2);
        Assert.assertEquals(10, res);
    }
}
