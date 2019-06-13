package rn.puzzle.search.medium

import org.junit.Assert
import org.junit.Test
import java.lang.Exception

class CountLuckTests {
    @Test
    fun sample1() {
        val res = countLuck(arrayOf("*.M",".X."), 1)
        Assert.assertEquals("Impressed", res)
    }

    @Test
    fun sample2() {
        val res = countLuck(arrayOf(".X.X......X",".X*.X.XXX.X",".XX.X.XM...","......XXXX."), 3)
        Assert.assertEquals("Impressed", res)
    }

    @Test
    fun sample3() {
        val res = countLuck(arrayOf(".X.X......X",".X*.X.XXX.X",".XX.X.XM...","......XXXX."), 4)
        Assert.assertEquals("Oops!", res)
    }

    private fun countLuck(source: Array<String>, k: Int): String {
        val matrix = source.map { it -> it.map { it }.toTypedArray() }.toTypedArray()
        val count = calcCount(matrix)
        return if(count==k) "Impressed" else "Oops!"
    }

    private fun calcCount(matrix: Array<Array<Char>>): Int {
        val start = findStart(matrix)
        return doMove(matrix, start, 0)
    }

    private fun doMove(matrix: Array<Array<Char>>, pos: Coord, initCount: Int): Int {
        if(matrix[pos.r][pos.c] == '*') {
            return initCount
        }
        matrix[pos.r][pos.c] = 'R'
        val moves = getMoves(matrix, pos)
        var count = initCount
        if(moves.size > 1) {
            count++
        }

        for (move in moves) {
            val res = doMove(matrix, move, count)
            if(res >= 0) {
                return res
            }
        }

        return -1
    }

    private fun getMoves(matrix: Array<Array<Char>>, start: Coord): List<Coord> {
        val res = ArrayList<Coord>()
        val r = start.r
        val c = start.c
        val available = arrayOf('.', '*')
        if(r > 0 && available.contains(matrix[r - 1][c])) {
            res.add(Coord(r - 1, c))
        }
        if(r < matrix.size - 1 && available.contains(matrix[r + 1][c])) {
            res.add(Coord(r + 1, c))
        }
        if(c > 0 && available.contains(matrix[r][c - 1])) {
            res.add(Coord(r, c - 1))
        }
        if(c < matrix[0].size - 1 && available.contains(matrix[r][c + 1])) {
            res.add(Coord(r, c + 1))
        }

        return res
    }

    private fun findStart(matrix: Array<Array<Char>>): Coord {
        for (r in 0 until matrix.size) {
            val row = matrix[r]
            for(c in 0 until row.size) {
                if(row[c] == 'M') {
                    return Coord(r, c)
                }
            }
        }
        throw Exception("not possible")
    }

    data class Coord(var r: Int, var c: Int)
}