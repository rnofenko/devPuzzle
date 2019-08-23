package rn.puzzle.string.easy

import org.junit.Test

class StrongPasswordSolverTests {

    @Test
    fun test1() {
        val solver = StrongPasswordSolver()

        val res = solver.minimumNumber("Ab1")
        org.junit.Assert.assertEquals(3, res)
    }
}