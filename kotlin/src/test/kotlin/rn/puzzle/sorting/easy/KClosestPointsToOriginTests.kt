package rn.puzzle.sorting.easy

import org.junit.Assert
import org.junit.Test

class KClosestPointsToOriginTests {
    private fun kClosest(points: Array<IntArray>, K: Int): Array<IntArray> {
        val selected = points
                .mapIndexed { index, a -> Pair(index, a[0]*a[0] + a[1]*a[1]) }
                .sortedBy { it.second }//.take(K)
        return selected.map { points[it.first] }.toTypedArray()
    }

    @Test
    fun test1() {
        val first = intArrayOf(1,3)
        val second = intArrayOf(-2,2)
        val res = kClosest(arrayOf(first, second), 1)
        Assert.assertArrayEquals(second,res[0])
    }
}