package rn.puzzle.sorting.medium

import org.junit.Assert
import org.junit.Test

class TopKFrequentWordsTests {
    private fun topKFrequent(words: Array<String>, k: Int): List<String> {
        val map = words.groupingBy { it }.eachCount()
        return map.entries
                .sortedWith(compareByDescending<Map.Entry<String, Int>> { it.value }.thenBy { it.key })
                .map { it.key }
                .take(k)
    }

    @Test
    fun test1() {
        Assert.assertEquals("cc", topKFrequent(arrayOf("aa", "cc", "bb", "cc"), 1)[0])
        Assert.assertEquals("bb", topKFrequent(arrayOf("aa", "cc", "bb", "cc", "bb"), 1)[0])
        Assert.assertEquals("aa", topKFrequent(arrayOf("aa", "cc", "bb", "cc", "bb", "aa"), 1)[0])
    }
}