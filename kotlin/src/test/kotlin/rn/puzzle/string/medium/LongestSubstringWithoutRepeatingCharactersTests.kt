package rn.puzzle.string.medium

import org.junit.Assert
import org.junit.Test

class LongestSubstringWithoutRepeatingCharactersTests {
    private fun lengthOfLongestSubstring(s: String): Int {
        if(s.isEmpty()) {
            return 0
        }
        var start = 0
        var end = start
        val buffer = IntArray(256)
        buffer[s[start].toInt()]++
        var max = 1

        while (end < s.lastIndex) {
            val nextChar = s[end + 1].toInt()
            if(buffer[nextChar] == 0) {
                end++
                buffer[nextChar] = 1
                max = Math.max(end - start + 1, max)
            } else {
                buffer[s[start].toInt()] = 0
                start++
            }
        }

        return max
    }

    @Test
    fun test1() {
        Assert.assertEquals(3, lengthOfLongestSubstring("abcabcbb"))
    }

    @Test
    fun test2() {
        Assert.assertEquals(1, lengthOfLongestSubstring("bbbbbb"))
    }

    @Test
    fun test3() {
        Assert.assertEquals(3, lengthOfLongestSubstring("pwwkew"))
    }

    @Test
    fun test4() {
        Assert.assertEquals(4, lengthOfLongestSubstring("abcd"))
    }
}