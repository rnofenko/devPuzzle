package rn.puzzle.greedy.easy

import org.junit.Assert
import org.junit.Test

class MaximumPerimeterTriangleTests {
    @Test
    fun sample1() {
        Assert.assertArrayEquals(arrayOf(1,3,3), maximumPerimeterTriangle(arrayOf(1,1,1,3,3)))
    }

    @Test
    fun sample2() {
        Assert.assertArrayEquals(arrayOf(-1), maximumPerimeterTriangle(arrayOf(1,2,3)))
    }

    @Test
    fun sample3() {
        Assert.assertArrayEquals(arrayOf(1,1,1), maximumPerimeterTriangle(arrayOf(1,1,1,2,3,5)))
    }

    private fun maximumPerimeterTriangle(sticks: Array<Int>): Array<Int> {
        sticks.sortDescending()
        var i = 0
        while (i < sticks.size - 2) {
            val v0 = sticks[i]
            val v1 = sticks[i + 1]
            val v2 = sticks[i + 2]
            if(v0 < v1 + v2) {
                return arrayOf(v2,v1,v0)
            }

            i++
        }

        return arrayOf(-1)
    }
}