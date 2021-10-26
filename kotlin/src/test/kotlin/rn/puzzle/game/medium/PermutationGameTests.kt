package rn.puzzle.game.medium

import org.junit.Assert
import org.junit.Test
import rn.tool.FileHelper
import rn.tool.StrConverter.toIntArray

//https://www.hackerrank.com/challenges/permutation-game/problem
class PermutationGameTests {
    @Test
    fun sample1() {
        Assert.assertEquals("Alice", permutationGame("1 3 2"))
    }

    @Test
    fun sample2() {
        Assert.assertEquals("Bob", permutationGame("5 3 2 1 4"))
    }

    @Test
    fun sample3() {
        Assert.assertEquals("Alice", permutationGame("3 2 1 4"))
    }

    @Test
    fun sample4() {
        Assert.assertEquals("Alice", permutationGame("4 3 2 1"))
    }

    @Test
    fun sample1_1() {
        Assert.assertEquals("Bob", permutationGame("3 2 1 4 5"))
    }

    @Test
    fun sample1_2() {
        Assert.assertEquals("Alice", permutationGame("3 4 5 1 2"))
    }

    @Test
    fun sample1_3() {
        Assert.assertEquals("Alice", permutationGame("4 3 1 5 2"))
    }

    @Test
    fun sample1_4() {
        Assert.assertEquals("Alice", permutationGame("3 2 4 5 1 6"))
    }

    @Test
    fun sample1_5() {
        Assert.assertEquals("Alice", permutationGame("6 3 7 5 1 4 2"))
    }

    @Test
    fun fileSample1() {
        val res = fileRunner("/Users/tkma0vh/Downloads/input01.txt", "/Users/tkma0vh/Downloads/output01.txt")
        Assert.assertEquals(0, res)
    }

    private fun permutationGame(strArr: String): String {
        val solver = PermutationGameBruteSolver()
        return solver.permutationGame(toIntArray(strArr))
    }

    private fun fileRunner(path: String, pathRes: String): Int {
        val lines = FileHelper.load(path)
        val resLines = FileHelper.load(pathRes)
        var i = 0
        val t = lines[i++].trim().toInt()
        var failed = 0
        for (tItr in 1..t) {
            i++
            val arr = lines[i++]
            val result = permutationGame(arr)
            val expectedResult = resLines[tItr-1]
            if(result != expectedResult) {
                failed++
                println("#$tItr " + lines[i-1])
            }
        }
        return failed
    }
}