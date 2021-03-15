package rn.puzzle.greedy.easy

import org.junit.Assert
import org.junit.Test
import rn.tool.FileHelper
import rn.tool.StrConverter.toIntegerArray

class BeautifulPairsTests {
    @Test
    fun sample1() {
        val a = toIntegerArray("1 2 3 4")
        val b = toIntegerArray("1 2 3 4")
        val res = beautifulPairs(a,b)
        Assert.assertEquals(3, res)
    }

    @Test
    fun sample3() {
        val a = toIntegerArray("1 2 3 4 4")
        val b = toIntegerArray("1 2 3 4 4")
        val res = beautifulPairs(a,b)
        Assert.assertEquals(4, res)
    }

    @Test
    fun sample4() {
        val a = toIntegerArray("1 2 3 4 4")
        val b = toIntegerArray("1 2 3 4")
        val res = beautifulPairs(a,b)
        Assert.assertEquals(4, res)
    }

    @Test
    fun sample5() {
        val a = toIntegerArray("1 2 3 4")
        val b = toIntegerArray("1 2 3 4 4")
        val res = beautifulPairs(a,b)
        Assert.assertEquals(4, res)
    }

    @Test
    fun sample6() {
        val a = toIntegerArray("1 2 3 4 5")
        val b = toIntegerArray("1 2 3 4 4")
        val res = beautifulPairs(a,b)
        Assert.assertEquals(5, res)
    }

    @Test
    fun sample7() {
        val a = toIntegerArray("1 2 3 4 4")
        val b = toIntegerArray("1 2 3 4 5")
        val res = beautifulPairs(a,b)
        Assert.assertEquals(5, res)
    }

    @Test
    fun sample8() {
        val a = toIntegerArray("1 2 3 4 5")
        val b = toIntegerArray("1 2 3 4 6")
        val res = beautifulPairs(a,b)
        Assert.assertEquals(5, res)
    }

    @Test
    fun sample2() {
        val a = toIntegerArray("3 5 7 11 5 8")
        val b = toIntegerArray("5 7 11 10 5 8")
        val res = beautifulPairs(a,b)
        Assert.assertEquals(6, res)
    }

    @Test
    fun fileSample1() {
        val res = fileRunner("/Users/tkma0vh/Downloads/input04.txt")
        Assert.assertEquals(999, res)
    }

    private fun beautifulPairs(a: Array<Int>, b: Array<Int>): Int {
        val m1 = a.groupingBy { it }.eachCount().toMutableMap()
        val m2 = b.groupingBy { it }.eachCount().toMutableMap()
        var count = 0
        val keys = m1.keys.toList()
        for (key in keys) {
            val n1 = m1[key] ?: 0
            val n2 = m2[key] ?: 0
            when {
                n1 == n2 -> {
                    count += n1
                    m1.remove(key)
                    m2.remove(key)
                }
                n1 > n2 -> {
                    count += n2
                    m1[key] = n1 - n2
                    m2.remove(key)
                }
                else -> {
                    count += n1
                    m1.remove(key)
                    m2[key] = n2 - n1
                }
            }
        }
        if(m1.isNotEmpty() && m2.isNotEmpty()) {
            return count+1
        } else if(m1.isEmpty() && m2.isEmpty()) {
            return count-1
        }
        return count
    }

    private fun fileRunner(path: String): Int {
        val lines = FileHelper.load(path)
        val a = lines[1].split(" ").map{ it.trim().toInt() }.toTypedArray()
        val b = lines[2].split(" ").map{ it.trim().toInt() }.toTypedArray()
        return beautifulPairs(a, b)
    }
}