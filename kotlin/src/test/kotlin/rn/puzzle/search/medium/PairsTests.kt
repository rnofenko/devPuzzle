package rn.puzzle.search.medium

import org.junit.Assert
import org.junit.Test

class PairsTests {
    @Test
    fun sample1() {
        Assert.assertEquals(3, pairs(2, arrayOf(1,5,3,4,2)))
    }

    private fun pairs(k: Int, arr: Array<Int>): Int {
        arr.sort()
        var count = 0
        var f = 0
        var s = 1
        while (s < arr.size) {
            val diff = arr[s] - arr[f]
            if(diff == k) {
                count++
                f++
                s++
            } else if(diff > k) {
                f++
            } else {
                s++
            }
        }
        return count
    }
}