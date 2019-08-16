package rn.puzzle.search.easy

import org.junit.Assert
import org.junit.Test

class RomanToIntegerTests {
    private fun romanToInt(s: String): Int {
        var t = 0
        var prev = 10000
        for (c in s) {
            val v = when(c) {
                'I' -> 1
                'V' -> 5
                'X' -> 10
                'L' -> 50
                'C' -> 100
                'D' -> 500
                else -> 1000
            }
            t += v
            if(prev < v) {
                t -= prev * 2
            }
            prev = v
        }
        return t
    }

    @Test
    fun test1() {
        Assert.assertEquals(3, romanToInt("III"))
    }

    @Test
    fun test2() {
        Assert.assertEquals(4, romanToInt("IV"))
    }

    @Test
    fun test3() {
        Assert.assertEquals(1994, romanToInt("MCMXCIV"))
    }
}