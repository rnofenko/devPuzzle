package rn.puzzle.greedy.low

import org.junit.Assert.assertEquals
import org.junit.Test

class MarcsCakewalkTests {

    @Test
    fun sample1() {
        val res = marcsCakewalk(arrayOf(7,4,9,6))
        assertEquals(79, res)
    }

    fun marcsCakewalk(calorie: Array<Int>): Long {
        calorie.sortByDescending { it }
        var res = 0L
        var factor = 1L
        for (cal in calorie) {
            res += cal * factor
            factor *= 2
        }

        return res
    }
}