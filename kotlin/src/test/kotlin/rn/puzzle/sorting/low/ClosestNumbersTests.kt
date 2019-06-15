package rn.puzzle.sorting.low

import org.junit.Assert
import org.junit.Test

class ClosestNumbersTests {
    @Test
    fun sample1() {
        val res = closestNumbers(arrayOf(-20,-3916237,-357920,-3620601,7374819,-7330761,30))
        Assert.assertEquals(-20, res[0])
    }

    private fun closestNumbers(arr: Array<Int>): List<Int> {
        arr.sort()
        var diff = Int.MAX_VALUE
        val list = ArrayList<Int>()

        for (i in 0..arr.size-2) {
            val v1 = arr[i]
            val v2 = arr[i + 1]
            val d = v2 - v1
            if(d <= diff) {
                if(d < diff) {
                    diff = d
                    list.clear()
                }
                list.add(v1)
                list.add(v2)
            }
        }

        return list
    }
}