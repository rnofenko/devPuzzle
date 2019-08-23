package rn.puzzle.string.easy

import org.junit.Test

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