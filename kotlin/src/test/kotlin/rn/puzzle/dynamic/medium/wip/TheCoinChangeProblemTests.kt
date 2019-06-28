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

    @Test
    fun sample4() {
        val res = getWays(4, stringToLongArray("1 2 3"))
        Assert.assertEquals(4, res)
    }

    @Test
    fun sample3() {
        val res = getWays(200, stringToLongArray("50 20 10 6 5 4 3 2 1"))
        Assert.assertEquals(47508405, res)
    }

    private fun getWays(n: Long, c: Array<Long>): Long {
        allCoins = c.sortedByDescending { it }.toLongArray()
        return calcCount(n, 0)
    }

    private fun calcCount(n: Long, index: Int): Long {
        if(n == 0L) {
            return 1
        }
        if(index >= allCoins.size) {
            return 0
        }
        val key = n * 1000 + index
        if(cache.containsKey(key)) {
            return cache[key] ?: 0
        }
        var count = 0L
        val coin = allCoins[index]
        var leftAmount = n / coin * coin
        while (leftAmount > 0) {
            val rightAmount = n - leftAmount
            count += calcCount(rightAmount, index + 1)
            leftAmount -= coin
        }
        count += calcCount(n, index + 1)
        cache[key] = count

        return count
    }

    private val cache = HashMap<Long, Long>()
    private var allCoins: LongArray = LongArray(0)
}