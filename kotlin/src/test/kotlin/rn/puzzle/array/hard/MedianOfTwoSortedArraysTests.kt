package rn.puzzle.array.hard

import org.junit.Assert
import org.junit.Test

class MedianOfTwoSortedArraysTests {
    private fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        if(nums2.size > nums1.size) {
            return findMedianSortedArrays(nums2, nums1)
        } else if(nums2.isEmpty()) {
            return simpleMedian(nums1)
        }
        return find(nums1, nums2)
    }

    private fun find(nums1: IntArray, nums2: IntArray): Double {
        var step = Math.max(1, nums1.size / 3)
        var i1 = 0
        var i2 = -1
        val l1 = nums1.lastIndex
        val l2 = nums2.lastIndex
        val targetIndex = (nums1.size + nums2.size) / 2
        while (i1 + i2 + 1 < targetIndex) {
            if(step > 1 && i1 + i2 + 1 + step * 2 > targetIndex) {
                step /= 2
                continue
            }

            if(i2 == l2) {
                i1 += step
            } else {
                val newI1 = Math.min(l1, i1 + step)
                val newI2 = Math.min(l2, i2 + step)
                if(newI1 == i1 || (newI2 != i2 && nums1[newI1] > nums2[newI2])) {
                    i2 = newI2
                } else {
                    i1 = newI1
                }
            }
        }

        val values = ArrayList<Int>()
        values.add(nums1[i1])
        if(i2 >= 0) {
            values.add(nums2[i2])
        }
        if(i1 > 0) {
            values.add(nums1[i1 - 1])
        }
        if(i2 > 0) {
            values.add(nums2[i2 - 1])
        }
        values.sortDescending()
        var v = values[0] * 1.0
        if((nums1.size + nums2.size) % 2 == 0) {
            v += values[1]
            return v / 2
        }
        return v
    }

    private fun simpleMedian(nums1: IntArray): Double {
        val medianIndex = nums1.size / 2
        val value = nums1[medianIndex].toDouble()
        if(nums1.size % 2 == 1) {
            return value
        }
        return (nums1[medianIndex - 1] + value) / 2.0
    }

    @Test
    fun test11() {
        solve(intArrayOf(1,4,5), intArrayOf(2,3))
    }

    @Test
    fun test10() {
        Assert.assertEquals(2.5, findMedianSortedArrays(intArrayOf(2,4), intArrayOf(1,3)), 0.01)
    }

    @Test
    fun test9() {
        Assert.assertEquals(5.0, findMedianSortedArrays(intArrayOf(4,5,7,8,9), intArrayOf(1,2,3,6)), 0.01)
    }

    @Test
    fun test8() {
        Assert.assertEquals(3.0, findMedianSortedArrays(intArrayOf(4), intArrayOf(1,2,3,5)), 0.01)
    }

    @Test
    fun test7() {
        Assert.assertEquals(100000.5, findMedianSortedArrays(intArrayOf(100001), intArrayOf(100000)), 0.01)
    }

    @Test
    fun test6() {
        Assert.assertEquals(1.0, findMedianSortedArrays(intArrayOf(1, 2), intArrayOf(1, 1)), 0.01)
    }

    @Test
    fun test5() {
        Assert.assertEquals(1.5, findMedianSortedArrays(intArrayOf(1, 2), intArrayOf(1, 2)), 0.01)
    }

    @Test
    fun test4() {
        Assert.assertEquals(1.5, findMedianSortedArrays(intArrayOf(1, 2), intArrayOf(-1, 3)), 0.01)
    }

    @Test
    fun test_1233334_33() {
        Assert.assertEquals(3.0, findMedianSortedArrays(intArrayOf(1, 2, 3, 3, 3, 3, 4), intArrayOf(3, 3)), 0.01)
    }

    @Test
    fun test_1123666_45() {
        Assert.assertEquals(4.0, findMedianSortedArrays(intArrayOf(1, 1, 2, 3, 6, 6, 6), intArrayOf(4, 5)), 0.01)
    }

    @Test
    fun test_1123677_45() {
        Assert.assertEquals(4.0, findMedianSortedArrays(intArrayOf(1, 1, 2, 3, 6, 7, 7), intArrayOf(4, 5)), 0.01)
    }

    @Test
    fun test_1234678_59() {
        solve(intArrayOf(1, 2, 3, 4, 6, 7, 8), intArrayOf(5, 9))
    }

    @Test
    fun test_1356789_24() {
        Assert.assertEquals(5.0, findMedianSortedArrays(intArrayOf(1, 3, 5, 6, 7, 8, 9), intArrayOf(2, 4)), 0.01)
    }

    @Test
    fun test_13579_2468() {
        Assert.assertEquals(5.0, findMedianSortedArrays(intArrayOf(1, 3, 5, 7, 9), intArrayOf(2, 4, 6, 8)), 0.01)
    }

    @Test
    fun test3() {
        Assert.assertEquals(5.0, findMedianSortedArrays(intArrayOf(1, 2, 3, 4, 5), intArrayOf(6, 7, 8, 9)), 0.01)
    }

    @Test
    fun test1() {
        Assert.assertEquals(2.5, findMedianSortedArrays(intArrayOf(1, 2), intArrayOf(3, 4)), 0.01)
    }

    @Test
    fun test2() {
        Assert.assertEquals(2.5, findMedianSortedArrays(intArrayOf(), intArrayOf(1, 2, 3, 4)), 0.01)
        Assert.assertEquals(2.0, findMedianSortedArrays(intArrayOf(), intArrayOf(1, 2, 3)), 0.01)
    }

    private fun solve(nums1: IntArray, nums2: IntArray) {
        val all = ArrayList<Int>()
        all.addAll(nums1.toList())
        all.addAll(nums2.toList())
        all.sort()
        val expected = simpleMedian(all.toIntArray())
        val actual = findMedianSortedArrays(nums1, nums2)
        Assert.assertEquals(expected, actual, 0.01)
    }
}