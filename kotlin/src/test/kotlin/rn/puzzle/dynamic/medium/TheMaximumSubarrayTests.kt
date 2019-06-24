package rn.puzzle.dynamic.medium

import org.junit.Assert
import org.junit.Test

class TheMaximumSubarrayTests {
    @Test
    fun sample1() {
        val res = maxSubarray(arrayOf(1,2,3,4))
        Assert.assertArrayEquals(arrayOf(10,10), res)
    }

    @Test
    fun sample2() {
        val res = maxSubarray(arrayOf(2,-1,2,3,4,-5))
        Assert.assertArrayEquals(arrayOf(10,11), res)
    }

    @Test
    fun sample3() {
        val res = maxSubarray(arrayOf(-2,-3,-1,-4,-6))
        Assert.assertArrayEquals(arrayOf(-1,-1), res)
    }

    private fun maxSubarray(arr: Array<Int>): Array<Int> {
        var max = Int.MIN_VALUE
        var sum = 0
        var posSum = 0
        for (i in arr) {
            sum += i
            if(i > sum) {
                sum = i
            }
            if(max < sum) {
                max = sum
            }
            if(i>0) {
                posSum += i
            }
        }
        if(posSum == 0) {
            posSum = max
        }
        return arrayOf(max, posSum)
    }
}