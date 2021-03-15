package rn.puzzle.dynamic.hard

import org.junit.Assert
import org.junit.Test
import rn.tool.FileHelper
import rn.tool.StrConverter.toIntegerArray

class TurnOffTheLightsTests {
    @Test
    fun sample1() {
        val a = toIntegerArray("1 1 1")
        Assert.assertEquals(1, turnOffTheLights(1, a))
    }

    @Test
    fun sample2() {
        val a = toIntegerArray("1 2 3 4 5")
        Assert.assertEquals(5, turnOffTheLights(1, a))
    }

    @Test
    fun sample07() {
        val a = toIntegerArray("10021 5844 1645")
        Assert.assertEquals(1645, turnOffTheLights(9, a))
    }

    @Test
    fun fileSample1() {
        val res = fileRunner("/Users/tkma0vh/Downloads/input01.txt")
        Assert.assertEquals(354556, res)
    }

    private fun turnOffTheLights(k: Int, c: Array<Int>): Long {
        val step = k * 2 + 1
        var min = Long.MAX_VALUE
        val points = LongArray(Math.min(k + 1, c.size))
        val finalIndex = c.size - k - 1
        for (i in 0 until c.size) {
            val v = i % step
            if(v > k) {
                continue
            }
            points[v] = points[v] + c[i]
            if(i >= finalIndex) {
                min = Math.min(min, points[v])
            }
        }
        return min
    }

    private fun fileRunner(path: String): Long {
        val lines = FileHelper.load(path)
        val k = lines[0].split(" ")[1].toInt()
        val c = lines[1].split(" ").map { it.toInt() }.toTypedArray()
        return turnOffTheLights(k, c)
    }
}