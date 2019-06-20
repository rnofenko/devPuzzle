package rn.puzzle.search.easy.wip

import org.junit.Assert
import org.junit.Test

class SherlockAndArrayTests {
    @Test
    fun sample1() {
        val res = balancedSums(arrayOf(1, 4, 5, 3, 2))
        Assert.assertEquals("YES", res)
    }

    private fun balancedSums(arr: Array<Int>): String {
        val total = arr.sum()
        var left = 0
        var right = total

        for (i in arr) {
            right -=i
            if(left == right) {
                return "YES"
            }
            left += i
        }
        return "NO"
    }
}