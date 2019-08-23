package rn.puzzle.search.medium

import org.junit.Test

class UglyNumber2Tests {
    private fun nthUglyNumber(n: Int): Int {
        if(n == 1) {
            return 1
        }
        var i = 1
        var m2 = 1
        var m3 = 1
        var m5 = 1
        val a = IntArray(1700)
        a[1] = 1
        var number = 1
        while (i < n) {
            val v2 = a[m2] * 2
            val v3 = a[m3] * 3
            val v5 = a[m5] * 5

            i++
            number = Math.min(Math.min(v2, v3), v5)
            a[i] = number
            if(number >= v2) {
                m2++
            }
            if(number >= v3) {
                m3++
            }
            if(number >= v5) {
                m5++
            }
        }
        return number
    }

    @Test
    fun test1() {
        nthUglyNumber(11)
    }
}