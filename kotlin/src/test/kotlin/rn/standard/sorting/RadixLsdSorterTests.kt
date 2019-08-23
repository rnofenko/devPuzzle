package rn.standard.sorting

import org.junit.Assert
import org.junit.Test
import rn.standard.sorting.RadixLsdSorter

class RadixLsdSorterTests {
    private val sorter = RadixLsdSorter()

    @Test
    fun test1() {
        val a = intArrayOf(10, 5, 20, 4, 17)
        val b = sorter.sort(a)
        Assert.assertArrayEquals(intArrayOf(4,5,10,17,20), b)
    }

    @Test
    fun test2() {
        val a = intArrayOf(1990000010, 198000000, 2120000000, 190000000, 2110000000)
        val b = sorter.sort(a)
        Assert.assertArrayEquals(intArrayOf(190000000,198000000,1990000010,2110000000,2120000000), b)
    }
}