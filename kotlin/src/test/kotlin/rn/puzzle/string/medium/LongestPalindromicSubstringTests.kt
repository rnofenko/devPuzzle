package rn.puzzle.string.medium

import org.junit.Assert
import org.junit.Test

class LongestPalindromicSubstringTests {
    @Test
    fun test1() {
        solve("cbbd", "bb")
    }

    @Test
    fun test3() {
        solve("babad", "bab")
    }

    @Test
    fun test4() {
        solve("", "")
    }

    @Test
    fun test5() {
        solve("a", "a")
    }

    @Test
    fun test6() {
        solve("aa", "aa")
    }

    @Test
    fun test7() {
        solve("aaa", "aaa")
    }

    @Test
    fun test8() {
        solve("ac", "a")
    }

    @Test
    fun test9() {
        solve("cbcdcbedcbc","bcdcb")
    }

    private fun solve(s: String, expected: String) {
        val res = longestPalindrome(s)
        Assert.assertEquals(expected, res)
    }

    private fun longestPalindrome(s: String): String {
        val a = s.map { it.toInt() - 'a'.toInt() }.toIntArray()
        if(a.size < 2) {
            return s
        }
        var sumSize = Math.max(s.length / 3, 1)
        while (sumSize > 0) {
            var i1 = 0
            var sum1 = calcSum(a, i1, i1 + sumSize - 1)
            var i2 = i1 + sumSize
            var sum2 = calcSum(a, i2, i2 + sumSize - 1)
            var lastIndex = i2 + sumSize
            var sum3 = if(lastIndex > a.lastIndex) Long.MIN_VALUE else sum2 - a[i2] + a[lastIndex]
            val centers = ArrayList<Center>()

            while(true) {
                if(sum1 == sum2 && a[i2] == a[i2 - 1]) {
                    centers.add(Center(i2 - 1, i2))
                }
                if(sum1 == sum3) {
                    centers.add(Center(i2, i2))
                }

                if(lastIndex > a.lastIndex) {
                    break
                }

                sum1 -= a[i1]
                sum1 += a[i2]

                sum2 -= a[i2]
                sum2 += a[lastIndex]

                if(lastIndex + 1 > a.lastIndex) {
                    sum3 = Long.MIN_VALUE
                } else {
                    sum3 -= a[i2 + 1]
                    sum3 += a[lastIndex + 1]
                }

                i1++
                i2++
                lastIndex++
            }

            if(centers.any()) {
                val pal = maxPalindrome(centers, s)
                if(pal.length >= sumSize * 2) {
                    return pal
                }
            }
            if(sumSize == 1) {
                return s[0].toString()
            }
            sumSize = Math.max(sumSize / 2, 1)
        }

        return ""
    }

    private fun maxPalindrome(centers: List<Center>, s: String): String {
        var longest = ""
        for (center in centers) {
            val found = findPalindrome(center, s)
            if(found.length > longest.length) {
                longest = found
            }
        }
        return longest
    }

    private fun findPalindrome(center: Center, s: String): String {
        var i1 = center.i1
        var i2 = center.i2
        while (i1 > 0 && i2 < s.lastIndex && s[i1 - 1] == s[i2 + 1]) {
            i1--
            i2++
        }
        return s.substring(i1, i2 + 1)
    }

    private fun calcSum(s: IntArray, start: Int, end: Int): Long {
        var sum = 0L
        for(i in start..end) {
            sum += s[i]
        }
        return sum
    }

    private class Center(val i1: Int, val i2: Int)
}