package rn.puzzle.string.medium

import org.junit.Assert
import org.junit.Test

class ImplementStrStrTests {

    @Test
    fun test1() {
        solve("hello", "ll", 2)
    }

    @Test
    fun test2() {
        solve("abababacaa", "ababaca", 2)
    }

    @Test
    fun test3() {
        solve("abababc", "ababc", 2)
    }

    @Test
    fun test4() {
        solve("ababbabbb", "bbb", 6)
    }

    fun solve(haystack: String, needle: String, index: Int) {
        val res = strStr(haystack, needle)
        Assert.assertEquals(index, res)
    }

    private fun strStr(haystack: String, needle: String): Int {
        if(needle.isEmpty()) {
            return 0
        }
        val t = buildKmpTable(needle)
        var i = 0
        var j = 0
        while (i < haystack.length) {
            if(haystack[i] == needle[j]) {
                i++
                j++
                if(j == needle.length) {
                    return i - j
                }
            } else {
                if(j > 0) {
                    j = t[j - 1]
                } else {
                    i++
                }
            }
        }
        return -1
    }

    private fun buildKmpTable(pattern: String): IntArray {
        val t = IntArray(pattern.length)
        var i = 1
        var j = 0
        while (i < t.size) {
            while (j > 0 && pattern[i] != pattern[j]) {
                j = t[j - 1]
            }
            if (pattern[i] == pattern[j]) {
                j++
            }
            t[i] = j
            i++
        }
        return t
    }
}