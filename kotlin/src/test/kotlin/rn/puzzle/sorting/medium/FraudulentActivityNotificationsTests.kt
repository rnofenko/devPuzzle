package rn.puzzle.sorting.medium

import org.junit.Assert
import org.junit.Test
import rn.tool.FileHelper
import rn.tool.ArrayPrinter
import rn.tool.StrConverter.toIntegerArray
import java.util.*

class FraudulentActivityNotificationsTests {

    @Test
    fun sample5() {
        val res = activityNotifications(toIntegerArray("9 2 3 2 7"), 1)
        Assert.assertEquals(1, res)
    }

    @Test
    fun sample3() {
        for (i in 0..10) {
            val a = createArray(5, 10)
            ArrayPrinter.print(a)
            for (d in 1 until a.size - 1) {
                println("d=$d")
                val r1 = activityNotifications(a, d)
                val r2 = activityNotificationsSlow(a, d)
                Assert.assertEquals(r2, r1)
            }
        }
    }

    private fun activityNotifications(expenditure: Array<Int>, d: Int): Int {
        val buffer = IntArray(201)
        for (i in 0 until d) {
            buffer[expenditure[i]]++
        }
        var count = 0
        val middleIndex1 = (d + 1) / 2
        val middleIndex2 = (d + 2) / 2
        var median1 = findMedian(buffer, Median(0, 0), middleIndex1)
        var median2 = findMedian(buffer, median1, middleIndex2)
        for(i in d until expenditure.size) {
            val v = expenditure[i]
            if(v >= median1.value + median2.value) {
                count++
            }

            val vOld = expenditure[i-d]

            buffer[v]++
            buffer[vOld]--

            median1.plus(v)
            median1.minus(vOld)

            median1 = findMedian(buffer, median1, middleIndex1)
            median2 = findMedian(buffer, median1, middleIndex2)
        }

        return count
    }

    private fun findMedian(buffer: IntArray, initialMedian: Median, d: Int): Median {
        var median = initialMedian.value
        var countBelow = initialMedian.countBelow
        while (countBelow + buffer[median] < d) {
            countBelow += buffer[median]
            while(median < buffer.size && buffer[++median] == 0) { }
        }
        while (countBelow >= d) {
            while(median > 0 && buffer[--median] == 0) { }
            countBelow -= buffer[median]
        }

        return Median(median, countBelow)
    }

    class Median(val value:Int, var countBelow: Int) {
        fun plus(v: Int) {
            if(v < value) {
                countBelow++
            }
        }
        fun minus(v: Int) {
            if(v < value) {
                countBelow--
            }
        }
    }

    @Test
    fun fileSample1() {
        val res = fileRunner("/Users/tkma0vh/Downloads/input01.txt")
        Assert.assertEquals(633, res)
    }

    private fun fileRunner(path: String): Int {
        val lines = FileHelper.load(path)
        val d = lines[0].split(" ").map { it.toInt() }.toTypedArray()[1]
        val numbers = lines[1].split(" ").map { it.toInt() }.toTypedArray()
        return activityNotifications(numbers, d)
    }

    private val rand = Random()

    @Test
    fun sample1() {
        Assert.assertEquals(2, activityNotifications(toIntegerArray("2 3 4 2 3 6 8 4 5"), 5))
    }

    @Test
    fun sample2() {
        Assert.assertEquals(1, activityNotifications(toIntegerArray("2 2 2 2 4"), 3))
    }

    @Test
    fun sample4() {
        val res = activityNotifications(toIntegerArray("0 0 5 4 0"), 2)
        Assert.assertEquals(1, res)
    }

    private fun activityNotificationsSlow(expenditure: Array<Int>, d: Int): Int {
        var count = 0
        val middleIndex1 = (d + 1) / 2
        val middleIndex2 = (d + 2) / 2
        val buffer = IntArray(d)
        for(i in d until expenditure.size) {
            for(j in 1..d) {
                buffer[j-1] = expenditure[i-j]
            }
            buffer.sort()
            if(expenditure[i] >= buffer[middleIndex1 - 1] + buffer[middleIndex2 - 1]) {
                count++
            }
        }

        return count
    }

    private fun createArray(size: Int, maxNumber: Int): Array<Int> {
        return Array(size) { Math.abs(rand.nextInt() % maxNumber) }
    }
}