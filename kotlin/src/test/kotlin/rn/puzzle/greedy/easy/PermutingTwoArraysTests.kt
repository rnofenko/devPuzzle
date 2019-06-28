package rn.puzzle.greedy.easy

import org.junit.Assert
import org.junit.Test
import rn.puzzle.StringToArrayHelpers

class PermutingTwoArraysTests {

    @Test
    fun sample1() {
        val a = StringToArrayHelpers.stringToIntArray("2 1 3")
        val b = StringToArrayHelpers.stringToIntArray("7 8 9")
        val res = twoArrays(10, a,b)
        Assert.assertEquals("YES", res)
    }

    @Test
    fun sample2() {
        val a = StringToArrayHelpers.stringToIntArray("1 2 2 1")
        val b = StringToArrayHelpers.stringToIntArray("3 3 3 4")
        val res = twoArrays(5, a,b)
        Assert.assertEquals("NO", res)
    }

    private fun twoArrays(k: Int, A: Array<Int>, B: Array<Int>): String {
        A.sort()
        B.sortDescending()
        for (i in A.indices) {
            if(A[i] + B[i] < k) {
                return "NO"
            }
        }
        return "YES"
    }
}