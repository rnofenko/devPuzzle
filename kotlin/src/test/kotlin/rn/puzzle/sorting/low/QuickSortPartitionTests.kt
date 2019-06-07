package rn.puzzle.sorting.low

import org.junit.Assert.assertEquals
import org.junit.Test

class QuickSortPartitionTests {
    private val solver = QuickSortPartitionSolver()

    @Test
    fun sample1() {
        val res = solver.quickSort(arrayOf(4, 5, 3, 7, 2))
        assertEquals(3, res[0])
        assertEquals(2, res[1])
        assertEquals(4, res[2])
        assertEquals(5, res[3])
        assertEquals(7, res[4])
    }
}