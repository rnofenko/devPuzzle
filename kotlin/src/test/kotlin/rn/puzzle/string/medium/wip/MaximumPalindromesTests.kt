package rn.puzzle.string.medium.wip

import org.junit.Assert
import org.junit.Test

class MaximumPalindromesTests {
    @Test
    fun sample1() {
        val res = solve("week", 1, 4)
        Assert.assertEquals(2, res)
    }

    @Test
    fun sample2() {
        val res = solve("week", 2, 3)
        Assert.assertEquals(1, res)
    }

    @Test
    fun sample3() {
        val res = solve("abab", 1, 4)
        Assert.assertEquals(2, res)
    }

    private fun solve(s: String, l: Int, r: Int): Int {
        initialize(s)
        return answerQuery(l,r)
    }

    private fun initialize(s: String) {
    }

    private fun answerQuery(l: Int, r: Int): Int {
        return 0
    }
}