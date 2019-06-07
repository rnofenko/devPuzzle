package rn.puzzle.string.low

import junit.framework.Assert.assertEquals
import org.junit.Test

class WeightedUniformStringsTests {

    private val solver = WeightedUniformStringsSolver()

    @Test
    fun sampe1() {
        val res = solver.weightedUniformStrings("abccddde", arrayOf(1, 3, 12, 5, 9, 10))
        assertEquals("Yes", res[0])
        assertEquals("Yes", res[1])
        assertEquals("Yes", res[2])
        assertEquals("Yes", res[3])
        assertEquals("No", res[4])
        assertEquals("No", res[5])
    }
}