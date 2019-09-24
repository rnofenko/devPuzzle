package rn.puzzle.search.hard

import org.junit.Assert
import org.junit.Test
import rn.puzzle.search.hard.CrackingTheSafeDeBruijnSolver

class CrackingTheSafeTests {

    @Test
    fun test12() {
        solve(1, 2, "01")
    }

    @Test
    fun test21() {
        solve(2, 1, "00")
    }

    @Test
    fun test11() {
        solve(1, 1, "0")
    }

    @Test
    fun test13() {
        solve(1, 3, "012")
    }

    @Test
    fun test22() {
        solve(2, 2, "00110")
    }

    @Test
    fun test32() {
        solve(3, 2, "0001011100")
    }

    @Test
    fun test42() {
        solve(4, 2, "0000100110101111000")
    }

    @Test
    fun test52() {
        solve(5, 2, "000001000110010100111010110111110000")
    }

    @Test
    fun test23() {
        solve(2, 3, "0010211220")
    }

    @Test
    fun test33() {
        solve(3, 3, "00010020110120210221112122200")
    }

    @Test
    fun test4() {
        solve(2, 5, "00102030411213142232433440")
    }

    private fun solve(n: Int, k: Int, expected: String) {
        val res = solver.solve(n, k)
        Assert.assertEquals(expected, res)
    }

    private val solver = CrackingTheSafeDeBruijnSolver()
}