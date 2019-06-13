package rn.puzzle.greedy.medium

import org.junit.Assert
import org.junit.Test

class MaxMinTests {

    @Test
    fun sample1() {
        val res = maxMin(2, arrayOf(1,2,1,2,1))
        Assert.assertEquals(0, res)
    }

    @Test
    fun sample2() {
        val res = maxMin(4, arrayOf(1,2,3,4,10,20,30,40,100,200))
        Assert.assertEquals(3, res)
    }

    fun maxMin(k: Int, arr: Array<Int>): Int {
        arr.sort()
        val shift = k - 1

        var min = Int.MAX_VALUE
        for (i in 0 until arr.size - shift) {
            val current = arr[i + shift] - arr[i]
            if(min > current) {
                min = current
            }
        }

        return min
    }
}
//https://www.hackerrank.com/challenges/angry-children/problem