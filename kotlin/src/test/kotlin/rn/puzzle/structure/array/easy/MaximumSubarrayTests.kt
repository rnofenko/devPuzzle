package rn.puzzle.structure.array.easy

import org.junit.Assert
import org.junit.Test

class MaximumSubarrayTests {
    @Test
    fun test1() {
        val a = intArrayOf(-2,1,-3,4,-1,2,1,-5,4)
        solve(a,6)
    }

    @Test
    fun test2() {
        val a = intArrayOf(1)
        solve(a,1)
    }

    @Test
    fun test3() {
        val a = intArrayOf(-1)
        solve(a,-1)
    }

    private fun solve(a: IntArray, expected: Int) {
        val res = maxSubArray(a)
        Assert.assertEquals(expected, res)
    }

    private fun maxSubArray(nums: IntArray): Int {
        if(nums.isEmpty()) {
            return 0
        }
        var sum = 0
        var max = nums[0]
        for (i in nums) {
            if(sum < 0) {
                sum = 0
            }
            sum += i
            max = Math.max(sum, max)
        }
        return max
    }
}