package rn.puzzle.greedy.easy

import org.junit.Assert
import org.junit.Test

class SherlockAndTheBeastTests {
    @Test
    fun sample1() {
        val res = getDecentNumber(3)
        Assert.assertEquals("555", res)
    }

    @Test
    fun sample2() {
        val res = getDecentNumber(11)
        Assert.assertEquals("55555533333", res)
    }

    @Test
    fun sample3() {
        val res = getDecentNumber(15)
        Assert.assertEquals("555555555555555", res)
    }

    private fun getDecentNumber(n: Int): String {
        var n3 = n / 3 * 3
        while (n3 >= 0) {
            val n5 = n - n3
            if(n5 % 5 == 0) {
                return "5".repeat(n3)+"3".repeat(n5)
            }
            n3-=3
        }
        return "-1"
    }
}