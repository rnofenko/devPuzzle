package rn.puzzle.graph.medium

import org.junit.Assert
import org.junit.Test

class NumberOfIslandsTests {
    @Test
    fun test1() {
        val input = toCharArray(arrayOf("11110", "11010", "11000", "00000"))
        solve(input, 1)
    }

    @Test
    fun test2() {
        val input = toCharArray(arrayOf("11000", "11000", "00100", "00011"))
        solve(input, 3)
    }

    @Test
    fun test3() {
        val input = toCharArray(arrayOf("10111", "10101", "11101"))
        solve(input, 1)
    }

    @Test
    fun test4() {
        val input = toCharArray(arrayOf("1111111", "0000001", "1111101", "1000101", "1010101", "1011101", "1111111"))
        solve(input, 1)
    }

    private fun toCharArray(input: Array<String>): Array<CharArray> {
        return input.map { it.toCharArray() }.toTypedArray()
    }

    private fun solve(grid: Array<CharArray>, expectedResult: Int) {
        val res = solver.numIslands(grid)
        Assert.assertEquals(expectedResult, res)
    }

    private val solver = NumberOfIslandsSolver()
}