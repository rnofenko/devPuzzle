package rn.puzzle.string.medium

import org.junit.Assert
import org.junit.Test
import rn.puzzle.FileHelper
import kotlin.math.pow

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

    @Test
    fun sample4() {
        val res = solve("aaaabbbaaaabbb")
        Assert.assertEquals(35, res)
    }

    @Test
    fun powTest() {
        val x = 11L
        val m = 5
        val res = solver.pow(x, m)
        val expected = x.toDouble().pow(m.toDouble()).toLong()
        Assert.assertEquals(expected, res)
    }

    @Test
    fun sample2_1() {
        val res = solve("wuhmbspjnfviogqzldrcxtaeyk", 16, 16)
        Assert.assertEquals(1, res)
    }

    @Test
    fun fileSample2() {
        fileRunner("input02.txt", "output02.txt")
    }

    @Test
    fun fileSample22() {
        fileRunner("input22.txt", "output22.txt")
    }

    @Test
    fun fileSample23() {
        fileRunner("input23.txt", "output23.txt")
    }

    private fun solve(s: String): Int {
        return solve(s, 1, s.length)
    }

    private fun solve(s: String, l: Int, r: Int): Int {
        solver.initialize(s)
        return solver.answerQuery(l,r)
    }

    private fun fileRunner(path: String, outputPath: String) {
        val prefix = "/Users/tkma0vh/Downloads/"
        val lines = FileHelper.load(prefix + path)
        val k = lines[1].trim().toInt()
        val outLines = FileHelper.load(prefix + outputPath)

        solver.initialize(lines[0])

        for(i in 0 until k) {
            val pair = lines[2 + i].split(" ").map { it.toInt() }.toTypedArray()
            val expectedRes = outLines[i].trim().toInt()
            try {
                val res = solver.answerQuery(pair[0], pair[1])
                Assert.assertEquals(expectedRes, res)
            }
            catch(ex: Exception) {
                println(lines[0])
                println(""+pair[0] + " " + pair[1])
                println(expectedRes)
                println()
            }
        }
    }

    private val solver = MaximumPalindromesFermatSolver()
//    private val solverSlow = MaximumPalindromesSolver()
}