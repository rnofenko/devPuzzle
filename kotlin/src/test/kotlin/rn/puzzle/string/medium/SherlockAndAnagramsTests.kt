package rn.puzzle.string.medium

import org.junit.Assert
import org.junit.Test

class SherlockAndAnagramsTests {
    @Test
    fun sample1() {
        val res = sherlockAndAnagrams("ifailuhkqq")
        Assert.assertEquals(3, res)
    }

    @Test
    fun sample2() {
        val res = sherlockAndAnagrams("kkkk")
        Assert.assertEquals(10, res)
    }

    @Test
    fun sample3() {
        val res = sherlockAndAnagrams("abba")
        Assert.assertEquals(4, res)
    }

    fun sherlockAndAnagrams(s: String): Int {
        var total = 0
        val a = s.map { it.toInt() - 'a'.toInt() }.toTypedArray()
        for (i in s.length - 1 downTo 1) {
            total += countOccurrence(a, i)
        }
        return total
    }

    private fun countOccurrence(a: Array<Int>, len: Int): Int {
        val untilIndex1 = a.size - len - 1
        val untilIndex2 = a.size - len
        var count = 0
        for (i in 0..untilIndex1) {
            val map1 = createMap(a, len, i)
            for(j in (i+1)..untilIndex2) {
                val map2 = createMap(a, len, j)
                if(mapsEqual(map1, map2)) {
                    count++
                }
            }
        }
        return count
    }

    private fun mapsEqual(m1: IntArray, m2: IntArray): Boolean {
        for (i in m1.indices) {
            if(m1[i] != m2[i]) {
                return false
            }
        }
        return true
    }

    private fun createMap(a: Array<Int>, len: Int, startIndex: Int): IntArray {
        val count = IntArray(26)
        val endIndex = startIndex + len - 1
        for (i in startIndex..endIndex) {
            count[a[i]]++
        }
        return count
    }
}