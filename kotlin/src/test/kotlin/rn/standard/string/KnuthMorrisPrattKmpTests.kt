package rn.standard.string

import org.junit.Assert
import org.junit.Test

class KnuthMorrisPrattKmpTests {
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

    fun solve(text: String, pattern: String, index: Int) {
        val res = findIndex(text, pattern)
        Assert.assertEquals(index, res)
    }

    private fun findIndex(text: String, pattern: String): Int {
        if(pattern.isEmpty()) {
            return 0
        }
        val t = buildKmpTable(pattern)
        var i = 0
        var j = 0
        while (i < text.length) {
            if(text[i] == pattern[j]) {
                i++
                j++
                if(j == pattern.length) {
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