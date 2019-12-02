package rn.puzzle.search.medium

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import rn.puzzle.search.medium.FindMedianFromDataStreamHeap

class FindMedianFromDataStreamTests {

    @Test
    fun test1() {
        check(0.0)
        add(1)
        add(3)
        add(2)
        check(2.0)
    }

    @Test
    fun test2() {
        check(0.0)
        add(1)
        check(1.0)
        add(2)
        check(1.5)
        add(3)
        check(2.0)
        add(3)
        check(2.5)
        add(3)
        check(3.0)
        add(1000)
        check(3.0)
    }

    private fun add(num: Int) {
        finder!!.addNum(num)
    }

    private fun check(expected: Double) {
        val res = finder!!.findMedian()
        Assert.assertEquals(expected, res, 0.0001)
    }

    @Before
    fun before() {
        finder = FindMedianFromDataStreamHeap()
    }

    private var finder: FindMedianFromDataStreamHeap? = null
}