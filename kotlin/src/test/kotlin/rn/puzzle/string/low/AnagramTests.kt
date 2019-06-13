package rn.puzzle.string.low

import org.junit.Assert
import org.junit.Test

class AnagramTests {
    @Test
    fun sample1() {
        Assert.assertEquals(3, anagram("aaabbb"))
    }

    @Test
    fun sample2() {
        Assert.assertEquals(-1, anagram("abc"))
    }

    @Test
    fun sample3() {
        Assert.assertEquals(1, anagram("xaxbbbxx"))
    }

    private fun anagram(s: String): Int {
        if(s.length % 2 == 1) {
            return -1
        }

        val left = s.substring(0, s.length / 2).groupBy { it }
        val right = s.substring(s.length / 2).groupBy { it }

        var diff = 0
        for (entry in right) {
            var leftCount = 0
            if(left.containsKey(entry.key)) {
                leftCount = (left[entry.key] ?: throw Exception("")).size
            }
            val rightCount = entry.value.size
            if(rightCount > leftCount) {
                diff += rightCount - leftCount
            }
        }

        return diff
    }

    fun twoStrings(s1: String, s2: String): String {
        val e1 = s1.toSet()
        for (c in s2) {
            if(e1.contains(c)) {
                return "YES"
            }
        }
        return "NO"
    }
}