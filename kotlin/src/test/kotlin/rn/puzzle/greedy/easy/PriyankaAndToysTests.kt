package rn.puzzle.greedy.easy

import org.junit.Assert
import org.junit.Test

class PriyankaAndToysTests {
    @Test
    fun sample1() {
        Assert.assertEquals(4, toys(arrayOf(1,2,3,21,7,12,14,21)))
    }

    private fun toys(w: Array<Int>): Int {
        w.sort()
        var count = 0
        var sum = 0
        var start = w[0]
        for (i in w) {
            if(i - start > 4) {
                count++
                sum = 0
                start = i
            }
            sum += i
        }
        if(sum > 0) {
            count++
        }
        return count
    }
}