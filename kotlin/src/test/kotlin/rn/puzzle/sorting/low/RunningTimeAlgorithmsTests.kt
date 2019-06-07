package rn.puzzle.sorting.low

import org.junit.Assert.assertEquals
import org.junit.Test

class RunningTimeAlgorithmsTests {
    private val solver = RunningTimeAlgorithmsSolver()

    @Test
    fun sample1() {
        val res = solver.runningTime(arrayOf(2,1,3,1,2))
        assertEquals(4, res)
    }
}