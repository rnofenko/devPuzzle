package rn.puzzle.search.medium

import org.junit.Assert
import org.junit.Test

//https://www.hackerrank.com/challenges/connected-cell-in-a-grid/problem
class ConnectedCellsInGridTests {

    @Test
    fun sample1() {
        val res = connectedCell(arrayOf(arrayOf(1,1,0,0),arrayOf(0,1,1,0),arrayOf(0,0,1,0),arrayOf(1,0,0,0)))
        Assert.assertEquals(5, res)
    }

    @Test
    fun sample2() {
        val res = connectedCell(arrayOf(arrayOf(0,0,1,1),arrayOf(0,0,1,0),arrayOf(0,1,1,0),arrayOf(0,1,0,0),arrayOf(1,1,0,0)))
        Assert.assertEquals(8, res)
    }

    fun connectedCell(matrix: Array<Array<Int>>): Int {
        val width = matrix[0].size
        val height = matrix.size
        var max = 0
        for(rowNo in 0 until height) {
            val row = matrix[rowNo]
            for(colNo in 0 until width) {
                val cell = row[colNo]
                if(cell == 1) {
                    val infectedCount = infect(matrix, rowNo, colNo)
                    if(infectedCount > max) {
                        max = infectedCount
                    }
                }
            }
        }
        return max
    }

    private fun infect(matrix: Array<Array<Int>>, startRowNo: Int, startColNo: Int): Int {
        var count = 1
        matrix[startRowNo][startColNo] = 2

        val width = matrix[0].size
        val height = matrix.size
        val topRowNo = if(startRowNo > 0) 1 else 0
        val bottomRowNo = if(startRowNo < height - 1) 1 else 0
        val leftColNo = if(startColNo > 0) 1 else 0
        val rightColNo = if(startColNo < width - 1) 1 else 0

        for (rowNo in startRowNo - topRowNo..startRowNo + bottomRowNo) {
            for(colNo in startColNo - leftColNo..startColNo + rightColNo) {
                if(matrix[rowNo][colNo] == 1) {
                    count += infect(matrix, rowNo, colNo)
                }
            }
        }

        return count
    }
}