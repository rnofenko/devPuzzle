package rn.puzzle.search.hard

import org.junit.Assert
import org.junit.Test
import rn.puzzle.search.hard.ParseLispExpressionSolver

class ParseLispExpressionTests {

    @Test
    fun add1() {
        solve("(add 1 2)", 3)
    }

    @Test
    fun mult1() {
        solve("(mult 3 (add 2 3))", 15)
    }

    @Test
    fun let1() {
        solve("(let x 2 (mult x 5))", 10)
    }

    @Test
    fun let2() {
        solve("(let x 3 x 2 x)", 2)
    }

    @Test
    fun let3() {
        solve("(let x 2 (mult x (let x 3 y 4 (add x y))))", 14)
    }

    @Test
    fun let4() {
        solve("(let x 1 y 2 x (add x y) (add x y))", 5)
    }

    @Test
    fun let5() {
        solve("(let x 2 (add (let x 3 (let x 4 x)) x))", 6)
    }

    @Test
    fun let6() {
        solve("(let a1 3 b2 (add a1 1) b2)", 4)
    }

    @Test
    fun let7() {
        solve("(let x 7 -12)", -12)
    }

    private fun solve(expression: String, res: Int) {
        val actual = solver.evaluate(expression)
        Assert.assertEquals(res, actual)
    }

    private val solver = ParseLispExpressionSolver()
}