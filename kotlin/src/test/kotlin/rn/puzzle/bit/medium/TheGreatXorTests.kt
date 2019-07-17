package rn.puzzle.bit.medium

import org.junit.Assert
import org.junit.Test

class TheGreatXorTests {

    @Test
    fun sample2() {
        Assert.assertEquals(1, theGreatXor(2))
    }

    @Test
    fun sample5() {
        Assert.assertEquals(2, theGreatXor(5))
    }

    @Test
    fun sample100() {
        Assert.assertEquals(27, theGreatXor(100))
    }

    private fun theGreatXor(x: Long): Long {
        var w = 1L
        while (w < x) {
            w *= 2
        }
        w -= 1
        return (x and w) xor w
    }
}