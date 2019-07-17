package rn.puzzle.game.easy

import org.junit.Assert
import org.junit.Test
import rn.puzzle.tool.StringToArrayConverter

class MisereNimTests {
    @Test
    fun sample11() {
        Assert.assertEquals("First", solve("1 1"))
    }

    @Test
    fun sample123() {
        Assert.assertEquals("Second", solve("2 1 3"))
    }

    @Test
    fun sample3() {
        Assert.assertEquals("First", solve("1 1 1 1"))
    }

    private fun solve(s: String): String {
        return misereNim(StringToArrayConverter.stringToArray(s))
    }

    private fun misereNim(pile: Array<Int>): String {
        var a = reduceArray(pile.toIntArray())
        if(a.isEmpty()) {
            return "Second"
        }

        var steps = 0
        while (a.isNotEmpty()) {
            if(a.all { it == 1 }) {
                if(a.size % 2 == 1) {
                    steps++
                }
                break
            }

            if(a.size == 1) {
                break
            }
            else if(a.size == 2) {
                if(a[0] == a[1]) {
                    steps++
                }
                break
            }

            var x = a[0]
            for(i in 1 until a.size) {
                x = x xor a[i]
            }

            steps++
            if(x == 0) {
                break
            }

            var minI = 0
            for (i in a.indices) {
                val y = a[i] xor x
                if(y < a[i]) {
                    minI = i
                    break
                }
            }

            val redVal = a[minI] xor x
            a[minI] = redVal
            if(a[minI] < 1) {
                a = reduceArray(a)
            }
        }
        return if(steps % 2 == 0) "First" else "Second"
    }

    private fun reduceArray(a: IntArray): IntArray {
        var i = 0
        var j = 0
        while (j < a.size) {
            if(a[j] == 0) {
                j++
                continue
            }
            a[i] = a[j]
            i++
            j++
        }

        return a.copyOf(i)
    }
}