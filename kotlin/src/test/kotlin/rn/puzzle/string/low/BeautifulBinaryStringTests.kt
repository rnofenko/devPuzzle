package rn.puzzle.string.low

import org.junit.Assert
import org.junit.Test

class BeautifulBinaryStringTests {
    @Test
    fun sample1() {
        val res = beautifulBinaryString("0100101010")
        Assert.assertEquals(3, res)
    }

    @Test
    fun sample2() {
        val res = beautifulBinaryString("01100")
        Assert.assertEquals(0, res)
    }

    fun beautifulBinaryString(b: String): Int {
        val template = "010"
        var str = b
        var count = 0
        while (true) {
            val ind = str.indexOf(template)
            if(ind == -1) {
                break
            }
            count++
            str = str.substring(ind + 3)
        }
        return count
    }
}