package rn.puzzle.graph.medium

import org.junit.Assert
import org.junit.Test

class SnakesAndLaddersTheQuickestWayUpTests {
    @Test
    fun sample1() {
        val ladders = arrayOf(arrayOf(32,62),arrayOf(42,68),arrayOf(12,98))
        val snakes = arrayOf(arrayOf(95,13),arrayOf(97,25),arrayOf(93,37),arrayOf(79,27),arrayOf(75,19),arrayOf(49,47),arrayOf(67,17))
        Assert.assertEquals(3, quickestWayUp(ladders, snakes))
    }

    @Test
    fun sample2() {
        val ladders = arrayOf(arrayOf(8,52),arrayOf(6,80),arrayOf(26,42), arrayOf(2,72))
        val snakes = arrayOf(arrayOf(51,19),arrayOf(39,11),arrayOf(37,29),arrayOf(81,3),arrayOf(59,5),arrayOf(79,23),arrayOf(53,7), arrayOf(43,33), arrayOf(77,21))
        Assert.assertEquals(5, quickestWayUp(ladders, snakes))
    }

    @Test
    fun sample3() {
        val ladders = arrayOf(arrayOf(3,54),arrayOf(37,100))
        val snakes = arrayOf(arrayOf(56,33))
        Assert.assertEquals(3, quickestWayUp(ladders, snakes))
    }

    @Test
    fun sample4() {
        val ladders = arrayOf(arrayOf(3,57),arrayOf(8,100))
        val snakes = arrayOf(arrayOf(88,44))
        Assert.assertEquals(2, quickestWayUp(ladders, snakes))
    }

    private fun quickestWayUp(ladders: Array<Array<Int>>, snakes: Array<Array<Int>>): Int {
        val b = createBoard(ladders, snakes)
        history.clear()
        history[1] = true
        return go(b, listOf(1), 0)
    }

    private fun go(board: IntArray, startPoses: Iterable<Int>, stepNo: Int): Int {
        while (true) {
            val nextPoses = ArrayList<Int>()

            for (startPos in startPoses) {
                val next = getNextMoves(board, startPos)
                for (i in next) {
                    if(i == 100) {
                        return stepNo + 1
                    }
                    if(history.containsKey(i)) {
                        continue
                    }
                    history[i] = true
                    nextPoses.add(i)
                }
            }

            if(nextPoses.isEmpty()) {
                return -1
            }

            return go(board, nextPoses.asIterable(), stepNo + 1)
        }
    }

    private fun getNextMoves(board: IntArray, startPos: Int): List<Int> {
        var simpleMove = startPos
        val ladders = ArrayList<Int>()
        val maxStep = Math.min(6, 100 - startPos)
        for (i in 1..maxStep) {
            val pos = startPos + i
            if(board[pos] == 0) {
                simpleMove = pos
            } else {
                ladders.add(board[pos])
            }
        }
        if(simpleMove > startPos) {
            ladders.add(simpleMove)
        }
        return ladders
    }

    private fun createBoard(ladders: Array<Array<Int>>, snakes: Array<Array<Int>>): IntArray {
        val b = IntArray(101)
        for (ladder in ladders) {
            b[ladder[0]] = ladder[1]
        }
        for (snake in snakes) {
            b[snake[0]] = snake[1]
        }
        return b
    }

    private val history = HashMap<Int, Boolean>()
}