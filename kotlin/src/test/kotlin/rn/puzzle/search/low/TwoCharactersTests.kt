package rn.puzzle.search.low

import org.junit.Assert
import org.junit.Test
import java.lang.StringBuilder

class TwoCharactersTests {

        @Test
        fun sample1() {
            val res = alternate("beabeefeab")
            Assert.assertEquals(5, res)
        }

    @Test
    fun sample2() {
        val inp = "cwomzxmuelmangtosqkgfdqvkzdnxerhravxndvomhbokqmvsfcaddgxgwtpgpqrmeoxvkkjunkbjeyteccpugbkvhljxsshpoymkryydtmfhaogepvbwmypeiqumcibjskmsrpllgbvc"
        val res = alternate(inp)
        Assert.assertEquals(8, res)
    }

    fun alternate(s: String): Int {
        val str = removeWrong(s)
        return calc(str)
    }

    private fun calc(s: String): Int {
        var max = 0
        val allChars = getChars(s)
        for(i in 0 until allChars.size - 1) {
            for(j in i + 1 until allChars.size) {
                val cou = cuunt(s, allChars[i], allChars[j])
                if(cou > max) {
                    max = cou
                }
            }
        }

        return max
    }

    private fun getChars(s: String): Array<Char> {
        val set = HashSet<Char>()
        for (c in s) {
            set.add(c)
        }

        return set.sortedBy { it }.toTypedArray()
    }

    private fun cuunt(s: String, c1: Char, c2: Char): Int {
        var count = 0

        var prev = '0'
        for (c in s) {
            if(c != c1 && c != c2) {
                continue
            }
            if(prev == c) {
                return 0
            }
            prev = c
            count++
        }

        return count
    }

    private fun removeWrong(s: String): String {
        val wrong = HashSet<Char>()
        var prev = '0'
        for (c in s) {
            if(c == prev) {
                wrong.add(c)
            }
            prev = c
        }

        if(wrong.isEmpty()) {
            return s
        }

        val builder = StringBuilder()
        for (c in s) {
            if(wrong.contains(c)) {
                continue
            }
            builder.append(c)
        }

        return removeWrong(builder.toString())
    }
}