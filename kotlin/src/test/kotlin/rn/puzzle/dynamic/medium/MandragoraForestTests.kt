package rn.puzzle.dynamic.medium

import org.junit.Assert
import org.junit.Test
import rn.puzzle.FileHelper
import rn.puzzle.Stopwatch
import rn.puzzle.StringToArrayHelpers.stringToIntArray

class MandragoraForestTests {
    @Test
    fun sample1() {
        Assert.assertEquals(10, mandragora(stringToIntArray("3 2 2")))
    }

    @Test
    fun sample2() {
        Assert.assertEquals(16, mandragora(stringToIntArray("3 2 5")))
    }

    @Test
    fun fileSample1() {
        val w = Stopwatch.start()
        fileRunner("/Users/tkma0vh/Downloads/input01.txt")
        w.show()
    }

    private fun mandragora(h: Array<Int>): Long {
        h.sort()
        var health = h.size.toLong()
        var power = h.last().toLong()
        var maxPoints = health * power

        for (i in h.size - 2 downTo 0) {
            health--
            power += h[i]
            val points = health * power
            if(points > maxPoints) {
                maxPoints = points
            } else {
                break
            }
        }
        return maxPoints
    }

    private fun fileRunner(path: String) {
        val lines = FileHelper.load(path)

        val t = lines[0].toInt()

        var lineNo = 2
        for (tItr in 1..t) {
            val mandragoras = lines[lineNo].split(" ").map{ it.trim().toInt() }.toTypedArray()
            lineNo += 2
            mandragora(mandragoras)
        }
    }
}