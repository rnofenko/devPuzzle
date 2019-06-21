package rn.puzzle.sorting.easy

import org.junit.Assert
import org.junit.Test

class FindMedianTests {
    @Test
    fun sample1() {
        Assert.assertEquals(3, findMedian(arrayOf(0,1,2,4,6,5,3)))
    }

    private fun findMedian(arr: Array<Int>): Int {
        arr.sort()
        val index = arr.size / 2
        return arr[index]
    }
}