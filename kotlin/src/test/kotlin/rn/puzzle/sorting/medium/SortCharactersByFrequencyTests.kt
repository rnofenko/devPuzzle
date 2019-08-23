package rn.puzzle.sorting.medium

import org.junit.Assert
import org.junit.Test
import java.lang.StringBuilder

class SortCharactersByFrequencyTests {
    private fun frequencySort(s: String): String {
        val stats = IntArray(256)
        for (c in s) {
            stats[c.toInt()]++
        }

        val builder = StringBuilder()
        for(p in stats.mapIndexed { index, v -> Pair(index, v) }.filter { it.second > 0 }.sortedByDescending { it.second }) {
            val s = p.first.toChar().toString().repeat(p.second)
            builder.append(s)
        }
        return builder.toString()
    }

    @Test
    fun test1() {
        Assert.assertEquals("bbAa", frequencySort("Aabb"))
    }
}