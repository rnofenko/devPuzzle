package rn.puzzle.wip

import org.junit.Assert
import org.junit.Test
import rn.puzzle.structure.array.hard.MinimumCostToMergeStonesSolution

class MinimumCostToMergeStonesTests {

    @Test
    fun test1() {
        solve(intArrayOf(3,2,4,1), 2, 20)
    }

    @Test
    fun test2() {
        solve(intArrayOf(3,2,4,1), 3, -1)
    }

    @Test
    fun test3() {
        solve(intArrayOf(3,5,1,2,6), 3, 25)
    }

    @Test
    fun test4() {
        solve(intArrayOf(1,3,2,7,4,2,2,8,3,4,5), 3, 88)
    }

    @Test
    fun test5() {
        solve(intArrayOf(1,2,3), 3, 6)
    }

    @Test
    fun test6() {
        solve(intArrayOf(6,4,4,6), 2, 40)
    }

    private fun solve(stones: IntArray, k: Int, expected: Int) {
        val res = solver.mergeStones(stones, k)
        Assert.assertEquals(expected, res)
    }

    private val solver = MinimumCostToMergeStonesSolution()
}