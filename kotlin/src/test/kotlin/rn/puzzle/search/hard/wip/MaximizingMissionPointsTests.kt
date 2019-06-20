package rn.puzzle.search.hard.wip

import org.junit.Assert
import org.junit.Test
import rn.puzzle.search.hard.MaximizingMissionPointsSolver

class MaximizingMissionPointsTests {
    @Test
    fun sample1() {
        val solver = MaximizingMissionPointsSolver(1, 1)
        solver.solve(1,1,1,3)
        solver.solve(2,2,2,-1)
        solver.solve(3,3,3,3)
        Assert.assertEquals(5, solver.getResult())
    }
}