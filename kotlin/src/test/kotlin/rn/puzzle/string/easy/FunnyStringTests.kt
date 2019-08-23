package rn.puzzle.string.easy

import org.junit.Assert
import org.junit.Test

class FunnyStringTests {
    @Test
    fun sample1() {
        val res = funnyString("acxz")
        Assert.assertEquals("Funny", res)
    }

    @Test
    fun sample2() {
        val res = funnyString("bcxz")
        Assert.assertEquals("Not Funny", res)
    }

    @Test
    fun sample3() {
        val res = funnyString("lmnop")
        Assert.assertEquals("Funny", res)
    }

    @Test
    fun sample4() {
        val res = funnyString("uvzxrumuztyqyvpnji")
        Assert.assertEquals("Funny", res)
    }

    fun funnyString(s: String): String {
        val len = s.length
        val rev = s.reversed()
        for (i in 1 until len) {
            val left = Math.abs(s[i] - s[i - 1])
            val right = Math.abs(rev[i] - rev[i - 1])
            if(left != right) {
                return "Not Funny"
            }
        }

        return "Funny"
    }
}