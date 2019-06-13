package rn.puzzle.greedy.medium

import org.junit.Assert
import org.junit.Test

class GreedyFloristTests {
    @Test
    fun sample1() {
        val res = getMinimumCost(3, arrayOf(2,5,6))
        Assert.assertEquals(13, res)
    }

    @Test
    fun sample2() {
        val res = getMinimumCost(2, arrayOf(2,5,6))
        Assert.assertEquals(15, res)
    }

    @Test
    fun sample3() {
        val res = getMinimumCost(3, arrayOf(1,3,5,7,9))
        Assert.assertEquals(29, res)
    }

    fun getMinimumCost(k: Int, prices: Array<Int>): Int {
        var factor = 1
        var i = 0
        prices.sortDescending()

        var total = 0
        for (price in prices) {
            total += price * factor

            i++
            if(i == k) {
                factor++
                i = 0
            }
        }

        return total
    }
}