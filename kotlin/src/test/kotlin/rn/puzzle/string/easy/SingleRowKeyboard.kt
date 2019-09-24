package rn.puzzle.string.easy

import org.junit.Assert
import org.junit.Test

class SingleRowKeyboard {
    private fun calculateTime(keyboard: String, word: String): Int {
        val map = HashMap<Char, Int>()
        var i = 0
        for (c in keyboard) {
            map[c] = i++
        }

        var current = 0
        var total = 0
        for (c in word) {
            val dest = map[c] ?: 0
            total += Math.abs(dest - current)
            current = dest
        }

        return total
    }

    @Test
    fun test1() {
        val res = calculateTime("abcdefghijklmnopqrstuvwxyz", "cba")
        Assert.assertEquals(4, res)
    }
}