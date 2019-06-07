package rn.puzzle.string.low

import org.junit.Test
import rn.puzzle.string.low.StrongPasswordSolver

class StrongPasswordSolverTests {

    @Test
    fun test1() {
        val solver = StrongPasswordSolver()

        val res = solver.minimumNumber("Ab1")
        org.junit.Assert.assertEquals(3, res)
    }
}