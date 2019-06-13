package rn.puzzle.string.low

import org.junit.Assert
import org.junit.Test
import java.lang.StringBuilder

class CaesarCipherTests {

    @Test
    fun sample1() {
        val res = caesarCipher("middle-Outz", 2)
        Assert.assertEquals("okffng-Qwvb", res)
    }

    @Test
    fun sample2() {
        val res = caesarCipher("xyz", 2)
        Assert.assertEquals("zab", res)
    }

    @Test
    fun sample3() {
        val res = caesarCipher("www.abc.xy", 87)
        Assert.assertEquals("fff.jkl.gh", res)
    }

    fun caesarCipher(s: String, k: Int): String {
        val small = Pair('a'.toByte(), 'z'.toByte())
        val big = Pair('A'.toByte(), 'Z'.toByte())
        val ranges = arrayOf(small, big)
        val shift = k % (small.second - small.first + 1)

        val builder = StringBuilder()
        for (c in s) {
            val code = c.toByte()
            var isHandled = false
            for (range in ranges) {
                if(code < range.first || code > range.second) {
                    continue
                }

                var newCode = code + shift
                if(newCode > range.second) {
                    newCode = range.first + (newCode - range.second) - 1
                }
                builder.append(newCode.toChar())
                isHandled = true
                break
            }

            if(!isHandled) {
                builder.append(c)
            }
        }

        return builder.toString()
    }
}