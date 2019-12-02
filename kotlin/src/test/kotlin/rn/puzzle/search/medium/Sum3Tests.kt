package rn.puzzle.search.medium

import org.junit.Assert
import org.junit.Test

class Sum3Tests {
    @Test
    fun test1() {
        val a = intArrayOf(-1, 0, 1, 2, -1, -4)
        val expected = listOf(listOf(-1, -1, 2), listOf(-1, 0, 1))
        solve(a, expected)
    }

    @Test
    fun test2() {
        val a = intArrayOf(1, -1, 0)
        val expected = listOf(listOf(-1, 0, 1))
        solve(a, expected)
    }

    @Test
    fun test4() {
        val a = intArrayOf(-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6)
        val expected = listOf(listOf(-4, -2, 6), listOf(-4, 0, 4), listOf(-4, 1, 3), listOf(-4, 2, 2), listOf(-2, -2, 4), listOf(-2, 0, 2))
        solve(a, expected)
    }

    @Test
    fun test3() {
        val expected = ArrayList<List<Int>>()
        solve(intArrayOf(1, -1), expected)
        solve(intArrayOf(1), expected)
        solve(intArrayOf(), expected)
    }

    private fun solve(a: IntArray, expected: List<List<Int>>) {
        val res = threeSum(a).sortedBy { it[0] }
        Assert.assertEquals(expected, res)
    }

    private fun threeSum(nums: IntArray): List<List<Int>> {
        var zerosCount = 0
        val posList = ArrayList<Int>()
        val negList = ArrayList<Int>()
        for (num in nums) {
            if(num == 0) {
                zerosCount++
            } else if(num > 0) {
                posList.add(num)
            } else {
                negList.add(-num)
            }
        }
        posList.sort()
        negList.sort()

        val result = ArrayList<List<Int>>()
        if(zerosCount >= 3) {
            result.add(listOf(0,0,0))
        }
        if(posList.isNotEmpty() && negList.isNotEmpty()) {
            result.addAll(findEqual(posList, negList, zerosCount > 0, true))
            result.addAll(findEqual(negList, posList, anyZero = false, kPos = false))
        }

        return result
    }

    private fun findEqual(single: List<Int>, double: List<Int>, anyZero: Boolean, kPos: Boolean): List<List<Int>> {
        val result = ArrayList<List<Int>>()

        var si = single.lastIndex
        var di = double.lastIndex
        while (si >= 0) {
            while (si > 0 && single[si] == single[si - 1]) {
                si--
            }
            val k = single[si]

            if(anyZero) {
                di = findLowerOrEqual(double, k, di)
                if (di == -1) {
                    return result
                }
                if (double[di] == k) {
                    result.add(listOf(-k, 0, k))
                }
            }

            di = findLower(double, k, di)
            if(di == -1) {
                return result
            }

            val found = findMatch(double, k, di, kPos)
            result.addAll(found)

            si--
        }

        return result
    }

    private fun findLower(list: List<Int>, k: Int, endIndex: Int): Int {
        var i = endIndex
        while (list[i] >= k && i > 0) {
            i--
        }
        return if(list[i] >= k) -1 else i
    }

    private fun findLowerOrEqual(list: List<Int>, k: Int, endIndex: Int): Int {
        var i = endIndex
        while (list[i] > k && i > 0) {
            i--
        }
        return if(list[i] > k) -1 else i
    }

    private fun findMatch(list: List<Int>, k: Int, endIndex: Int, kPos: Boolean): List<List<Int>> {
        val result = ArrayList<List<Int>>()

        var b = 0
        var e = endIndex
        while (b < e) {
            val sum = list[b] + list[e]
            if(sum == k) {
                if(kPos) {
                    result.add(listOf(-list[e], -list[b], k))
                } else {
                    result.add(listOf(-k, list[b], list[e]))
                }
            }
            if(sum >= k) {
                e--
                while (e > b && list[e] == list[e + 1]) {
                    e--
                }
            } else {
                b++
            }
        }

        return result
    }
}