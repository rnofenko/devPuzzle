package rn.puzzle.array.hard

import org.junit.Assert
import org.junit.Test
import java.util.*

class TrappingRainWaterTests {

    @Test
    fun test1() {
        Assert.assertEquals(6, trap(intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1)))
    }

    private fun trap(height: IntArray): Int {
        val peaks = getPeaks(height)
        if(peaks.isEmpty()) {
            return 0
        }
        var leftMax = 0
        var minPeak = leftMax
        var sum = 0
        var nextPeak = peaks.pop()

        for (i in height.indices) {
            val value = height[i]
            if(value > leftMax) {
                leftMax = value
                minPeak = Math.min(leftMax, nextPeak[1])
                continue
            }
            while(i >= nextPeak[0]) {
                if(peaks.isEmpty()) {
                    return sum
                }
                nextPeak = peaks.pop()
                minPeak = Math.min(leftMax, nextPeak[1])
            }
            if(value < minPeak) {
                sum += minPeak - value
            }
        }
        return sum
    }

    private fun getPeaks(height: IntArray): Stack<IntArray> {
        val peaks = Stack<IntArray>()
        var max = 0
        for (i in height.lastIndex downTo 0) {
            val value = height[i]
            if(value > max) {
                max = value
                peaks.push(intArrayOf(i, value))
            }
        }
        return peaks
    }
}