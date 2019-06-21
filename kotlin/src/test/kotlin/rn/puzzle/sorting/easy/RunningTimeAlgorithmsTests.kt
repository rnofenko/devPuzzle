package rn.puzzle.sorting.easy

import org.junit.Assert.assertEquals
import org.junit.Test
import rn.puzzle.sorting.low.RunningTimeAlgorithmsSolver

class RunningTimeAlgorithmsTests {
    private val solver = RunningTimeAlgorithmsSolver()

    @Test
    fun sample1() {
        val res = solver.runningTime(arrayOf(2,1,3,1,2))
        assertEquals(4, res)
    }
}