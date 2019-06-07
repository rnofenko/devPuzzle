package rn.puzzle.greedy.low

import org.junit.Assert
import org.junit.Test

class GridChallengeTests {

    @Test
    fun sample1() {
        val res = gridChallenge(arrayOf("abcde","fghij", "klmno", "pqrst", "uvwxy"))
        Assert.assertEquals("YES", res)
    }

    @Test
    fun sample2() {
        val res = gridChallenge(arrayOf("abc","hjk", "mpq", "rtv"))
        Assert.assertEquals("YES", res)
    }

    fun gridChallenge(grid: Array<String>): String {
        val list = ArrayList<CharArray>()
        for (line in grid) {
            val a = line.map { it }.sortedBy { it }.toCharArray()
            list.add(a)
        }

        val height = list.size
        val width = list[0].size
        for (rowNo in 0 until height - 1) {
            for (colNo in 0 until width) {
                val r0 = list[rowNo]
                val r1 = list[rowNo + 1]
                if(r0[colNo] > r1[colNo]) {
                    return "NO"
                }
            }
        }

        return "YES"
    }
}