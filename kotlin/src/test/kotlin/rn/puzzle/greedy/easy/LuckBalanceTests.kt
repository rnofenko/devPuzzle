package rn.puzzle.greedy.easy

import org.junit.Assert
import org.junit.Test

class LuckBalanceTests {
    @Test
    fun sample1() {
        val pairs = arrayOf(arrayOf(5,1),arrayOf(2,1),arrayOf(1,1),arrayOf(8,1),arrayOf(10,0),arrayOf(5,0))
        Assert.assertEquals(29, luckBalance(3, pairs))
    }

    private fun luckBalance(k: Int, contests: Array<Array<Int>>): Int {
        val important = ArrayList<Int>()
        var luck = 0
        for (contest in contests) {
            if(contest[1] == 0) {
                luck += contest[0]
            } else {
                important.add(contest[0])
            }
        }

        important.sortDescending()
        for (i in important.indices) {
            if(i<k) {
                luck += important[i]
            } else {
                luck -= important[i]
            }
        }

        return luck
    }
}