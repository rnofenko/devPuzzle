package rn.puzzle.dynamic.medium

import org.junit.Assert
import org.junit.Test
import rn.puzzle.tool.StringToArrayConverter.stringToArray

class BricksGameTests {
    @Test
    fun sample1() {
        val res = bricksGame(stringToArray("999 1 1 1 0"))
        Assert.assertEquals(1001, res)
    }

    @Test
    fun sample2() {
        val res = bricksGame(stringToArray("0 1 1 1 999"))
        Assert.assertEquals(999, res)
    }

    @Test
    fun sample3() {
        val res = bricksGame(stringToArray("1 2 3 4 5"))
        Assert.assertEquals(6, res)
    }

    @Test
    fun sample4() {
        val res = bricksGame(stringToArray("1 2 3 4 5 6 7"))
        Assert.assertEquals(16, res)
    }

    private fun bricksGame(arr: Array<Int>): Long {
        val best = LongArray(arr.size + 3)
        var sum = 0L

        for(i in arr.size - 1 downTo 0) {
            var max = Long.MIN_VALUE
            var currentMove = 0
            val endIndex = Math.min(i+2, arr.size-1)
            sum += arr[i]
            var localSum = sum
            for (j in i..endIndex) {
                currentMove += arr[j]
                localSum -= arr[j]
                val total = currentMove + localSum - best[j + 1]
                max = Math.max(max, total)
            }
            best[i] = max
        }
        return best[0]
    }
}