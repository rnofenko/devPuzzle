package rn.puzzle.search.easy

import org.junit.Assert
import org.junit.Test
import java.lang.StringBuilder

class CountAndSayTests {
    private fun countAndSay(n: Int): String {
        var text = "1"
        var i = 1
        while (i < n) {
            val builder = StringBuilder()

            var prev = text[0]
            var count = 0
            for (c in text) {
                if(c == prev) {
                    count++
                } else {
                    builder.append(count.toString()).append(prev)
                    count = 1
                }
                prev = c
            }
            if(count > 0) {
                builder.append(count.toString()).append(prev)
            }

            text = builder.toString()
            i++
        }
        return text
    }

    @Test
    fun test1() {
        Assert.assertEquals("1211", countAndSay(4))
    }
}