package rn.puzzle.search.easy

import org.junit.Assert
import org.junit.Test

class MissingNumbersTests {
    @Test
    fun sample1() {
        Assert.assertArrayEquals(arrayOf(204,205,206), missingNumbers(
                arrayOf(203,204,205,206,207,208,203,204,205,206),
                arrayOf(203,204,204,205,206,207,205,208,203,206,205,206,204)
        ))
    }

    private fun missingNumbers(arr: Array<Int>, brr: Array<Int>): Array<Int> {
        val am = arr.groupingBy { it }.eachCount()
        val bm = brr.groupingBy { it }.eachCount()

        val res = ArrayList<Int>()
        for (entry in bm) {
            val k = entry.key
            val newCount = am[k] ?: 0
            if (newCount < entry.value) {
                res.add(k)
            }
        }
        return res.sortedBy { it }.toTypedArray()
    }
}