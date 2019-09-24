package rn.puzzle.string.medium

import org.junit.Assert
import org.junit.Test

class MinimumWindowSubstringTests {

    @Test
    fun test1() {
        val res = minWindow("ADOBECODEBANC", "ABC")
        Assert.assertEquals("BANC", res)
    }

    @Test
    fun test2() {
        val res = minWindow("DOBEODEBAAANC", "ABC")
        Assert.assertEquals("BAAANC", res)
    }

    @Test
    fun test3() {
        val res = minWindow("ADOBECODEBAAANC", "AABC")
        Assert.assertEquals("BAAANC", res)
    }

    @Test
    fun test4() {
        val res = minWindow("ADOBECODEBANC", "AAABC")
        Assert.assertEquals("", res)
    }

    @Test
    fun test5() {
        val res = minWindow("A", "A")
        Assert.assertEquals("A", res)
    }

    private fun minWindow(s: String, t: String): String {
        val buffer = Buffer(s, t)
        var foundStart = 0
        var foundEnd = 0
        var foundSize = s.length + 1

        while (buffer.canContinue()) {
            if(buffer.leftCount > 0) {
                buffer.incEndIndex()
            } else {
                val newSize = buffer.end - buffer.start + 1
                if(foundSize > newSize) {
                    foundStart = buffer.start
                    foundEnd = buffer.end
                    foundSize = newSize
                }
                buffer.incStartIndex()
            }
        }

        if(foundSize > s.length) {
            return ""
        }
        return s.substring(foundStart, foundEnd + 1)
    }

    private class Buffer(val s: String, t: String) {
        var start = 0
        var end = -1
        private val map = HashMap<Char, Int>()
        var leftCount = 0

        init {
            for (c in t) {
                val count = map[c] ?: 0
                map[c] = count + 1
                leftCount++
            }
        }

        fun incEndIndex() {
            end++
            val c = s[end]

            val count = map[c] ?: return
            map[c] = count - 1
            if(count > 0) {
                leftCount--
            }
        }

        fun incStartIndex() {
            val c = s[start]
            start++

            val count = map[c] ?: return
            if(count >= 0) {
                leftCount++
            }
            map[c] = count + 1
        }

        fun canContinue(): Boolean {
            return end < s.lastIndex || leftCount == 0
        }
    }
}