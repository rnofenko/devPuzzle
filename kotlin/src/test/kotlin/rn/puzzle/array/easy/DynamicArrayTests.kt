package rn.puzzle.array.easy

import org.junit.Assert
import org.junit.Test
import rn.puzzle.tool.StringToArrayConverter.stringTo2dArray

class DynamicArrayTests {
    private fun dynamicArray(n: Int, queries: Array<Array<Int>>): Array<Int> {
        var lastAnswer = 0
        val lists = generateSequence { ArrayList<Int>() }.take(n).toList()
        val res = ArrayList<Int>()
        for (query in queries) {
            val x = query[1]
            val y = query[2]

            val listNo = (lastAnswer xor x) % n
            val list = lists[listNo]

            if(query[0] == 1) {
                list.add(y)
            } else {
                val no = y % list.size
                lastAnswer = list[no]
                res.add(lastAnswer)
            }
        }
        return res.toTypedArray()
    }

    @Test
    fun test1() {
        val res = dynamicArray(2, stringTo2dArray("1 0 5,1 1 7,1 0 3,2 1 0,2 1 1"))
        Assert.assertArrayEquals(arrayOf(7,3), res)
    }
}