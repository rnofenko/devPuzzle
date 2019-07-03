package rn.puzzle.bit.easy

import org.junit.Assert
import org.junit.Test

class SumVsXorTests {
    @Test
    fun sample1() {
        Assert.assertEquals(4, sumXor(10))
    }

    @Test
    fun sample2() {
        Assert.assertEquals(2, sumXor(5))
    }

    @Test
    fun sample3() {
        Assert.assertEquals(524288, sumXor(1234567890))
    }

    @Test
    fun sample4() {
        for(i in 1..100) {
            println("$i  " + sumXor(i.toLong()) + "  " + i.toString(2))
        }
    }

    private fun sumXor(n: Long): Long {
        if(n == 0L){
            return 1
        }
        val zerosCount = n.toString(2).filter { it == '0' }.count()
        val res = Math.pow(2.0, zerosCount.toDouble())
        return res.toLong()
    }
}