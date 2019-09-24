package rn.puzzle.array.medium

import org.junit.Assert
import org.junit.Test
import rn.tool.StringToArrayConverter.stringTo2dIntArray

class MergeIntervalsTests {
    @Test
    fun test1() {
        solve("[[1,3],[2,6],[8,10],[15,18]]", "[[1,6],[8,10],[15,18]]")
    }

    @Test
    fun test2() {
        solve("[[1,3],[1,2],[3,6],[1,5],[2,3]]", "[[1,6]]")
    }

    private fun solve(aStr: String, expectedStr: String) {
        val a = stringTo2dIntArray(aStr)
        val expected = stringTo2dIntArray(expectedStr)
        val res = merge(a)
        Assert.assertArrayEquals(expected, res)
    }

    private fun merge(intervals: Array<IntArray>): Array<IntArray> {
        if(intervals.isEmpty()) {
            return intervals
        }

        intervals.sortBy { it[0] }
        val result = ArrayList<IntArray>()

        var current = intervals.first()
        result.add(current)
        for (next in intervals) {
            if(current[0] == next[0] || current[1] >= next[0]) {
                current[1] = Math.max(current[1], next[1])
            } else {
                current = next
                result.add(current)
            }
        }

        return result.toTypedArray()
    }
}