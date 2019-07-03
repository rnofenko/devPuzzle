package rn.puzzle.string.medium

import org.junit.Assert
import org.junit.Test

class HighestValuePalindromeTests {
    @Test
    fun sample1() {
        val res = highestValuePalindrome("3943", 1)
        Assert.assertEquals("3993", res)
    }

    @Test
    fun sample2() {
        val res = highestValuePalindrome("092282", 3)
        Assert.assertEquals("992299", res)
    }

    @Test
    fun sample3() {
        val res = highestValuePalindrome("0011", 1)
        Assert.assertEquals("-1", res)
    }

    @Test
    fun sample4() {
        val res = highestValuePalindrome("777", 0)
        Assert.assertEquals("777", res)
    }

    @Test
    fun sample5() {
        val res = highestValuePalindrome("5", 1)
        Assert.assertEquals("9", res)
    }

    private fun highestValuePalindrome(s: String, k: Int): String {
        val endIndex = s.lastIndex
        val left = CharArray(s.length / 2)
        var changesCount = 0
        for(i in 0 until left.size) {
            val c1 = s[i]
            val c2 = s[endIndex - i]
            if(c1 == c2) {
                left[i] = c1
                continue
            }

            changesCount++
            left[i] = if(c1>c2) c1 else c2
        }
        var i = 0
        while(i < left.size && changesCount < k) {
            if(left[i] != '9') {
                val wasChanged = s[i] != s[endIndex - i]
                if(wasChanged) {
                    left[i] = '9'
                    changesCount++
                } else if(k - changesCount > 1) {
                    left[i] = '9'
                    changesCount += 2
                }
            }

            i++
        }
        if(changesCount>k) {
            return "-1"
        }

        val str = left.joinToString("")
        var m = ""
        if(s.length % 2 == 1) {
            m = if(k - changesCount > 0) "9" else s[s.length / 2].toString()
        }
        return str + m + str.reversed()
    }
}