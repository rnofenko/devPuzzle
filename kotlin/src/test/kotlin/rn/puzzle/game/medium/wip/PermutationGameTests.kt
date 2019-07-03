package rn.puzzle.game.medium.wip

import org.junit.Assert
import org.junit.Test
import rn.puzzle.FileHelper
import rn.puzzle.tool.StringToArrayConverter.stringToArray
import rn.puzzle.game.medium.PermutationGameMaxMinSolver

//https://www.hackerrank.com/challenges/permutation-game/problem
class PermutationGameTests {
    @Test
    fun sample1() {
        Assert.assertEquals("Alice", permutationGame(stringToArray("1 3 2")))
    }

    @Test
    fun sample2() {
        Assert.assertEquals("Bob", permutationGame(stringToArray("5 3 2 1 4")))
    }

    @Test
    fun sample3() {
        Assert.assertEquals("Alice", permutationGame(stringToArray("3 2 1 4")))
    }

    @Test
    fun sample4() {
        Assert.assertEquals("Alice", permutationGame(stringToArray("4 3 2 1")))
    }

    @Test
    fun sample1_1() {
        Assert.assertEquals("Bob", permutationGame(stringToArray("3 2 1 4 5")))
    }

    @Test
    fun sample1_2() {
        Assert.assertEquals("Alice", permutationGame(stringToArray("3 4 5 1 2")))
    }

    @Test
    fun sample1_3() {
        Assert.assertEquals("Alice", permutationGame(stringToArray("4 3 1 5 2")))
    }

    @Test
    fun sample1_4() {
        Assert.assertEquals("Alice", permutationGame(stringToArray("3 2 4 5 1 6")))
    }

    @Test
    fun sample1_5() {
        Assert.assertEquals("Alice", permutationGame(stringToArray("6 3 7 5 1 4 2")))
    }

    @Test
    fun fileSample1() {
        val res = fileRunner("/Users/tkma0vh/Downloads/input01.txt", "/Users/tkma0vh/Downloads/output01.txt")
        Assert.assertEquals(0, res)
    }

    private fun permutationGame(arr: Array<Int>): String {
        val solver = PermutationGameMaxMinSolver()
        return solver.permutationGame(arr)
    }

    private fun fileRunner(path: String, pathRes: String): Int {
        val lines = FileHelper.load(path)
        val resLines = FileHelper.load(pathRes)
        var i = 0
        val t = lines[i++].trim().toInt()
        var failed = 0
        for (tItr in 1..t) {
            i++
            val arr = lines[i++].split(" ").map{ it.trim().toInt() }.toTypedArray()
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