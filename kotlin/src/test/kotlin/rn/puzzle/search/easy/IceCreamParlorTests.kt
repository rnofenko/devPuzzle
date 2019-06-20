package rn.puzzle.search.easy

import org.junit.Assert.assertEquals
import org.junit.Test

class IceCreamParlorTests {
    private val solver = IceCreamParlorSolver()

    @Test
    fun sample1() {
        val res = solver.iceCreamParlor(4, arrayOf(1, 4, 5, 3, 2))
        assertEquals(1, res[0])
        assertEquals(4, res[1])
    }

    @Test
    fun sample2() {
        val res = solver.iceCreamParlor(4, arrayOf(2, 2, 4, 3))
        assertEquals(1, res[0])
        assertEquals(2, res[1])
    }

    @Test
    fun sample3() {
        val res = solver.iceCreamParlor(9, arrayOf(1, 3, 4, 6,7,9))
        assertEquals(2, res[0])
        assertEquals(4, res[1])
    }
}