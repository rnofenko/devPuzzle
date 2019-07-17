package rn.puzzle.game.easy

import org.junit.Assert
import org.junit.Test

class ChessboardGameTests {
    @Test
    fun sample1() {
        Assert.assertEquals("Second", chessboardGame(5, 2))
    }

    @Test
    fun sample2() {
        Assert.assertEquals("First", chessboardGame(5, 3))
    }

    @Test
    fun sample3() {
        Assert.assertEquals("First", chessboardGame(8, 8))
    }

    private fun chessboardGame(x: Int, y: Int): String {
        val p = Pos(x - 1, y - 1, board)
        checkPoint(p)
        return if(p.get() == win) "First" else "Second"
    }

    private fun checkPoint(p: Pos) {
        val moves = possibleChanges.map { p.move(it) }.filter { it.isValid() }
        for (move in moves) {
            if(move.get() == tbd) {
                checkPoint(move)
            }
            if(move.get() == loss) {
                p.set(win)
                return
            }
        }
        p.set(loss)
    }

    private val tbd = 0
    private val win = 1
    private val loss = 2
    private val size = 15
    private var board: Array<IntArray> = Array(size) { IntArray(size) }
    private val possibleChanges = arrayOf(Pos(-2, 1, board), Pos(-2, -1, board), Pos(-1, -2, board), Pos(1, -2, board))

    class Pos(val x: Int, val y: Int, val board: Array<IntArray>) {
        fun move(p: Pos): Pos {
            return Pos(x + p.x, y + p.y, board)
        }

        fun isValid(): Boolean {
            return x >= 0 && y >= 0 && x < 15 && y < 15
        }

        fun get(): Int {
            return board[y][x]
        }

        fun set(v: Int) {
            board[y][x] = v
        }

        override fun toString(): String {
            return "$x $y"
        }
    }
}