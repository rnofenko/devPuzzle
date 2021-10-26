package rn.puzzle.structure.array.easy

import org.junit.Assert
import org.junit.Test

class TwoSumTests {
    private fun twoSum(nums: IntArray, target: Int): IntArray {
        val set = nums.toSet()
        for (i in set) {
            val s = target - i
            if(s == i) {
                val res = ArrayList<Int>()
                for (j in nums.indices) {
                    if(nums[j] == i) {
                        res.add(j)
                        if(res.size == 2) {
                            return res.toIntArray()
                        }
                    }
                }
                continue
            }
            if(set.contains(s)) {
                val i1 = nums.indexOf(i)
                val i2 = nums.indexOf(s)
                val res = intArrayOf(i1, i2)
                res.sort()
                return res
            }
        }
        return intArrayOf(0)
    }

    @Test
    fun test1() {
        val r = twoSum(intArrayOf(3,3), 6)
        Assert.assertArrayEquals(intArrayOf(0,1), r)
    }

    @Test
    fun test2() {
        val r = twoSum(intArrayOf(-3,4,3,90), 0)
        Assert.assertArrayEquals(intArrayOf(0,2), r)
    }
}