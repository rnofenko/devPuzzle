package rn.puzzle.string.low

import org.junit.Test
import rn.puzzle.string.low.SuperReducedStringSolver

class SuperReducedStringSolverTests {

    @Test
    fun run() {
        val solver = SuperReducedStringSolver()

        val res = solver.superReducedString("aaabccddd")
        org.junit.Assert.assertEquals("abd", res)
    }

    @Test
    fun emptyString() {
        val solver = SuperReducedStringSolver()

        val res = solver.superReducedString("baab")
        org.junit.Assert.assertEquals("Empty String", res)
    }
}