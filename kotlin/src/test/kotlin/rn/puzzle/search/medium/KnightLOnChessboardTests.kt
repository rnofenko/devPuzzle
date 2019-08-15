package rn.puzzle.search.medium

import org.junit.Assert
import org.junit.Test
import rn.tool.Stopwatch

class KnightLOnChessboardTests {
    @Test
    fun sample5() {
        val res = knightLOnAChessboard(5)
        Assert.assertArrayEquals(arrayOf(4, 4, 2, 8), res[0])
        Assert.assertArrayEquals(arrayOf(4, 2, 4, 4), res[1])
        Assert.assertArrayEquals(arrayOf(2, 4, -1, -1), res[2])
        Assert.assertArrayEquals(arrayOf(8, 4, -1, 1), res[3])
    }

    @Test
    fun sample6() {
        val res = knightLOnAChessboard(6)
        Assert.assertArrayEquals(arrayOf(5, 4, 3, 2, 5), res[0])
        Assert.assertArrayEquals(arrayOf(4, -1, 2, -1, -1), res[1])
        Assert.assertArrayEquals(arrayOf(3, 2, -1, -1, -1), res[2])
        Assert.assertArrayEquals(arrayOf(2, -1, -1, -1, -1), res[3])
        Assert.assertArrayEquals(arrayOf(5, -1, -1, -1, 1), res[4])
    }

    @Test
    fun sample11() {
        val res = knightLOnAChessboard(11)
        Assert.assertArrayEquals(arrayOf(10,8,6,4,4,8,6,16,2,20), res[0])
        Assert.assertArrayEquals(arrayOf(8,5,4,4,4,3,8,2,28,5), res[1])
    }

    @Test
    fun test_12_when_n6() {
        val res = solve(1, 2, 6)
        Assert.assertEquals(4, res)
    }

    @Test
    fun performance() {
        val w = Stopwatch.start()
        knightLOnAChessboard(25)
        w.show()
    }

    private fun knightLOnAChessboard(n: Int): Array<Array<Int>> {
        val res = Array(n - 1) {Array(n - 1) {0}}

        for (x in 1 until n) {
            for(y in x until n) {
                val count = solve(x, y, n)
                res[x - 1][y - 1] = count
                res[y - 1][x - 1] = count
            }
        }
        return res
    }

    private fun solve(stepX: Int, stepY: Int, n: Int): Int {
        if(Math.max(stepX, stepY) >= n) {
            return -1
        }

        val matrix = Array(n) {Array(n) {0}}
        return bruteForce(matrix, stepX, stepY)
    }

    private fun bruteForce(matrix: Array<Array<Int>>, stepX: Int, stepY: Int): Int {
        val stepOptions = arrayOf(
                Pair(stepX, stepY), Pair(stepY, stepX),
                Pair(-stepX, stepY), Pair(stepY, -stepX),
                Pair(stepX, -stepY), Pair(-stepY, stepX),
                Pair(-stepX, -stepY), Pair(-stepY, -stepX))

        val bestMovesMap = HashMap<Int, Int>()
        val res = makeStep(matrix, stepOptions, 0, 0, 0, Int.MAX_VALUE, bestMovesMap)
        return if(res == Int.MAX_VALUE) -1 else res
    }

    private fun makeStep(oldMatrix: Array<Array<Int>>, stepOptions: Array<Pair<Int, Int>>, x: Int, y: Int, no: Int, limit: Int, bestMovesMap: HashMap<Int, Int>): Int {
        val n = oldMatrix.size
        if(no >= limit || x < 0 || y < 0 || x >= n || y >= n) {
            return Int.MAX_VALUE
        }
        if(x == n - 1 && y == n - 1) {
            return no
        }
        if(oldMatrix[x][y] != 0) {
            return Int.MAX_VALUE
        }
        val coord = x * 100 + y
        val oldNo = bestMovesMap[coord]
        if(oldNo != null && oldNo <= no) {
            return Int.MAX_VALUE
        }
        bestMovesMap[coord] = no

        val matrix = oldMatrix.map { it.clone() }.toTypedArray()
        matrix[x][y] = 1
        var min = Int.MAX_VALUE
        for (stepPair in stepOptions) {
            val stepNo = makeStep(matrix, stepOptions, x + stepPair.first, y + stepPair.second, no + 1, min, bestMovesMap)
            if(stepNo < min) {
                min = stepNo
            }
        }

        return min
    }
}