package rn.coursera.algtool.w2;

import org.junit.Assert;
import org.junit.Test;

public class FibonacciTest {
    @Test
    public void test1() {
        Assert.assertEquals(0, Fibonacci.calc_fib(0));
        Assert.assertEquals(1, Fibonacci.calc_fib(1));
        Assert.assertEquals(1, Fibonacci.calc_fib(2));
        Assert.assertEquals(2, Fibonacci.calc_fib(3));
        Assert.assertEquals(3, Fibonacci.calc_fib(4));
        Assert.assertEquals(5, Fibonacci.calc_fib(5));
        Assert.assertEquals(8, Fibonacci.calc_fib(6));
        Assert.assertEquals(13, Fibonacci.calc_fib(7));
    }
}
