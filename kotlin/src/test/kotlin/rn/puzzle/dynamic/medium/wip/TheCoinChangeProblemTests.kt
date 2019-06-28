package rn.puzzle.dynamic.medium.wip

import org.junit.Assert
import org.junit.Test
import rn.puzzle.StringToArrayHelpers.stringToLongArray

class TheCoinChangeProblemTests {
    @Test
    fun sample1() {
        val res = getWays(4, stringToLongArray("1 2 3"))
        Assert.assertEquals(4, res)
    }

    @Test
    fun sample2() {
        val res = getWays(10, stringToLongArray("2 5 3 6"))
        Assert.assertEquals(5, res)
    }

    private fun getWays(n: Long, c: Array<Long>): Long {
        c.sort()
        val startAmount = c[0]
        var count = 0L
        val cache = HashMap<Long, Long>()
        for(i in startAmount..n) {
          count += calcCount(i, c)
        }
        return 0
    }

    private fun calcCount(n: Long, c: Array<Long>): Long {
        return 0
    }
}