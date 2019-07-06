package rn.puzzle.game.easy

import org.junit.Assert
import org.junit.Test

class GameOfStonesTests {

    @Test
    fun sample1() {
        Assert.assertEquals("Second", gameOfStones(1))
        Assert.assertEquals("First", gameOfStones(3))
        Assert.assertEquals("First", gameOfStones(5))
        Assert.assertEquals("First", gameOfStones(6))
    }

    @Test
    fun sample2() {
        Assert.assertEquals("First", gameOfStones(2))
    }

    @Test
    fun sample4() {
        Assert.assertEquals("First", gameOfStones(4))
    }

    @Test
    fun sample7() {
        Assert.assertEquals("Second", gameOfStones(7))
    }

    @Test
    fun sample10() {
        Assert.assertEquals("First", gameOfStones(10))
    }

    private fun gameOfStones(n: Int): String {
        val a = BooleanArray(n)
        solve(n - 1, a)
        return if(a[0]) "First" else "Second"
    }

    private fun solve(startPos: Int, map: BooleanArray) {
        map[startPos] = findWinner(startPos, map)
        if(startPos > 0) {
            solve(startPos - 1, map)
        }
    }

    private fun findWinner(startPos: Int, map: BooleanArray): Boolean {
        val allSteps = intArrayOf(5,3,2)
        for (step in allSteps) {
            val nextPos = startPos + step
            if(nextPos > map.size) {
                continue
            }
            if(nextPos == map.size) {
                return true
            }
            if(!map[nextPos]) {
                return true
            }
        }
        return false
    }
}