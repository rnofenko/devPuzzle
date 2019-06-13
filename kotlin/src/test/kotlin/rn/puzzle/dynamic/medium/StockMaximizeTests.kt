package rn.puzzle.dynamic.medium

import org.junit.Assert
import org.junit.Test

class StockMaximizeTests {
    @Test
    fun sample1() {
        val res = stockmax(arrayOf(5,3,2))
        Assert.assertEquals(0, res)
    }

    @Test
    fun sample2() {
        val res = stockmax(arrayOf(1,2,100))
        Assert.assertEquals(197, res)
    }

    @Test
    fun sample3() {
        val res = stockmax(arrayOf(1,3,1,2))
        Assert.assertEquals(3, res)
    }

    @Test
    fun sample4() {
        val res = stockmax(arrayOf(1,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3))
        Assert.assertEquals(29, res)
    }

    fun stockmax(prices: Array<Int>): Long {
        val groups = splitByGroups(prices, 500)
        val forSell = ArrayList<Int>()
        var profit = 0L

        for ((index, group) in groups.withIndex()) {
            for (i in group.start..group.end) {
                val v = prices[i]
                if(v < group.futureMax) {
                    forSell.add(v)
                } else {
                    profit += sell(forSell, v)
                    group.updateMax(i + 1, prices)

                    if(index < groups.size - 1) {
                        val next = groups[index + 1]
                        group.futureMax = Math.max(group.max, next.futureMax)
                    } else {
                        group.futureMax = group.max
                    }
                }
            }
        }

        return profit
    }

    private fun sell(forSell: ArrayList<Int>, value: Int): Long {
        var profit = 0L
        for (i in forSell) {
            profit += value - i
        }
        forSell.clear()
        return profit
    }

    private fun splitByGroups(prices: Array<Int>, groupSize: Int): List<LocalGroup> {
        val groups = ArrayList<LocalGroup>()
        var startIndex = 0
        while (startIndex < prices.size) {
            var endIndex = startIndex + groupSize
            if(endIndex >= prices.size) {
                endIndex = prices.size - 1
            }
            val group = LocalGroup(endIndex)
            group.updateMax(startIndex, prices)
            groups.add(group)

            startIndex = endIndex + 1
        }

        updateFutureMax(groups)

        return groups
    }

    private fun updateFutureMax(prices: List<LocalGroup>) {
        var last = prices.last()
        last.futureMax = last.max
        for (i in prices.size - 2 downTo 0) {
            val current = prices[i]
            current.futureMax = Math.max(current.max, last.futureMax)

            last = current
        }
    }

    class LocalGroup(val end: Int, var start: Int = 0, var max: Int = 0, var futureMax: Int = 0) {
        fun updateMax(startIndex: Int, prices: Array<Int>) {
            start = startIndex
            var locMax = Int.MIN_VALUE
            for (i in start..end) {
                if(prices[i] > locMax) {
                    locMax = prices[i]
                }
            }

            max = locMax
        }
    }
}