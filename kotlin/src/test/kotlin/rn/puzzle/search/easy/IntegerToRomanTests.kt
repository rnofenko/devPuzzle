package rn.puzzle.search.easy

import org.junit.Assert
import org.junit.Test
import java.lang.StringBuilder

class IntegerToRomanTests {
    private fun intToRoman(num: Int): String {
        val list = intArrayOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
        val map = mapOf(1000 to "M", 900 to "CM", 500 to "D", 400 to "CD", 100 to "C", 90 to "XC", 50 to "L",
                40 to "XL", 10 to "X", 9 to "IX", 5 to "V", 4 to "IV", 1 to "I")
        var rest = num
        val builder = StringBuilder()
        for (divisor in list) {
            val count = rest / divisor
            if(count == 0) {
                continue
            }
            val v = map[divisor] ?: ""
            builder.append(v.repeat(count))
            rest -= count * divisor
        }
        return builder.toString()
    }

    @Test
    fun test3() {
        Assert.assertEquals("MCMXCIV", intToRoman(1994))
    }
}