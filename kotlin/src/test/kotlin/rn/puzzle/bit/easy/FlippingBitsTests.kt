package rn.puzzle.bit.easy

import org.junit.Assert
import org.junit.Test

class FlippingBitsTests {

    @Test
    fun sample1() {
        Assert.assertEquals(4294967295, flippingBits(0))
    }

    @Test
    fun sample2() {
        Assert.assertEquals(3492223820, flippingBits(802743475))
    }

    private fun flippingBits(n: Long): Long {
        val sample = 4294967295L
        return n xor sample
    }
}