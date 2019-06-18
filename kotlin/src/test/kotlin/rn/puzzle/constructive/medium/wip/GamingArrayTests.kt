package rn.puzzle.constructive.medium.wip

import org.junit.Assert
import org.junit.Test

class GamingArrayTests {
    @Test
    fun sample1() {
        Assert.assertEquals("BOB", gamingArray(arrayOf(1,3,5,7,9)))
    }

    @Test
    fun sample2() {
        Assert.assertEquals("ANDY", gamingArray(arrayOf(7,4,6,5,9)))
    }

    private fun gamingArray(arr: Array<Int>): String {
        var parts = cut(arr, 1000)

        var no = 0
        while (parts.isNotEmpty()) {

            var max = parts[0]
            var partIndex = 0
            for ((index, part) in parts.withIndex()) {
                if(part.maxValue > max.maxValue) {
                    max = part
                    partIndex = index
                }
            }

            if(!max.deleteAndUpdateMax()) {
                partIndex--
            }
            parts = parts.take(partIndex+1)

            no++
        }
        return if(no % 2 == 0) "ANDY" else "BOB"
    }

    private fun cut(arr: Array<Int>, partSize: Int): List<Part> {
        val list = ArrayList<Part>()
        var i = 0
        while (i < arr.size) {
            val endIndex = Math.min(arr.size - 1, i + partSize)
            val part = Part(i, endIndex, arr)
            part.updateMax()
            list.add(part)
            i = endIndex + 1
        }

        return list
    }

    class Part(private val start: Int, private var end: Int, private val arr: Array<Int>) {
        var maxValue: Int = 0
        private var maxIndex: Int = 0

        fun updateMax() {
            maxValue = Int.MIN_VALUE
            for (i in start..end) {
                val v = arr[i]
                if(v > maxValue) {
                    maxValue = v
                    maxIndex = i
                }
            }
        }

        fun deleteAndUpdateMax(): Boolean {
            end = maxIndex - 1
            updateMax()
            return end - start >=0
        }
    }
}