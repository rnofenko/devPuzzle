package rn.puzzle.array.medium

import org.junit.Assert
import org.junit.Test

class TwoSumLessThanKTests {

    @Test
    fun test1() {
        solve(intArrayOf(34,23,1,24,75,33,54,8), 60, 58)
    }

    @Test
    fun test2() {
        solve(intArrayOf(10,20,30), 15, -1)
    }

    @Test
    fun test3() {
        val a = intArrayOf(254,914,110,900,147,441,209,122,571,942,136,350,160,127,178,839,201,386,462,45,735,467,153,415,875,282,204,534,639,994,284,320,865,468,1,838,275,370,295,574,309,268,415,385,786,62,359,78,854,944)
        solve(a, 200, 198)
    }

    private fun solve(a: IntArray, k: Int, expected: Int) {
        val res = twoSumLessThanK(a, k)
        Assert.assertEquals(expected, res)
    }

    private fun twoSumLessThanK(a: IntArray, k: Int): Int {
        a.sort()
        if(a.size < 2 || a[0] + a[1] > k) {
            return -1
        }

        var beg = 0
        var begCount = 1
        var end = a.lastIndex
        var endCount = 1
        var max = a[0] + a[1]

        while (beg < end) {
            val begValue = a[beg]
            val endValue = a[end]

            while (beg < end && begValue == a[beg + 1]) {
                beg++
                begCount++
            }
            while (end > beg && endValue == a[end - 1]) {
                end--
                endCount++
            }

            val sum = begValue + endValue
            if(sum < k) {
                max = Math.max(max, sum)
                beg++
            } else {
                end--
            }
        }

        return max
    }
}