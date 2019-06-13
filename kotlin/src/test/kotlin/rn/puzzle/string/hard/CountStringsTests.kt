package rn.puzzle.string.hard

import org.junit.Assert
import org.junit.Test

class CountStringsTests {
    @Test
    fun sample1() {
        Assert.assertEquals(2, countStrings("((ab)|(ba))", 2))
    }

    @Test
    fun sample2() {
        Assert.assertEquals(32, countStrings("((a|b)*)", 5))
    }

    @Test
    fun sample3() {
        Assert.assertEquals(100, countStrings("((a*)(b(a*)))", 100))
    }

    fun countStrings(r: String, l: Int): Int {
        return 0
    }
}