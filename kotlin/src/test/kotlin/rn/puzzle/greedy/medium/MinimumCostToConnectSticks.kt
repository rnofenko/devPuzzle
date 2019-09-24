package rn.puzzle.greedy.medium

import org.junit.Assert
import org.junit.Test
import java.util.*

class MinimumCostToConnectSticks {
    private fun connectSticks(sticks: IntArray): Int {
        var total = 0
        val listOne = LinkedList<Int>()
        val listTwo = LinkedList<Int>()
        listTwo.addAll(sticks.sorted().toList())

        while (listOne.size + listTwo.size > 1) {
            val el1 = if(listTwo.isEmpty() || (listOne.isNotEmpty() && listOne.first <= listTwo.first))
                listOne.pollFirst()
            else listTwo.pollFirst()
            val el2 = if(listTwo.isEmpty() || (listOne.isNotEmpty() && listOne.first <= listTwo.first))
                listOne.pollFirst()
            else listTwo.pollFirst()
            val price = el1 + el2
            listOne.add(price)

            total += price
        }
        return total
    }

    @Test
    fun test1() {
        val res = connectSticks(intArrayOf(2,4,3))
        Assert.assertEquals(14, res)
    }

    @Test
    fun test2() {
        val res = connectSticks(intArrayOf(1,8,3,5))
        Assert.assertEquals(30, res)
    }
}