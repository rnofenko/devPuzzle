package rn.puzzle.dynamic.medium

import org.junit.Assert
import org.junit.Test
import java.math.BigInteger

class FibonacciModifiedTests {
    @Test
    fun sample1() {
        val res = fibonacciModified(0, 1,5)
        Assert.assertEquals(5, res)
    }

    @Test
    fun sample2() {
        val res = fibonacciModified(0, 1,6)
        Assert.assertEquals(BigInteger.valueOf(27L), res)
    }

    fun fibonacciModified(t1: Int, t2: Int, n: Int): BigInteger {
        if(n == 1) {
            return BigInteger.valueOf(t1.toLong())
        }
        if(n == 2) {
            return BigInteger.valueOf(t2.toLong())
        }
        var p1 = BigInteger.valueOf(t1.toLong())
        var p2 = BigInteger.valueOf(t2.toLong())
        for (i in 3..n) {
            val p3 = p1 + p2 * p2
            p1 = p2
            p2 = p3
        }

        return p2
    }
}