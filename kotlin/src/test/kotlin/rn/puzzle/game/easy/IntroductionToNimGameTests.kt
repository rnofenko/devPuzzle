package rn.puzzle.game.easy

import org.junit.Assert
import org.junit.Test
import rn.tool.StringToArrayConverter.stringToArray

class IntroductionToNimGameTests {
    @Test
    fun sample11() {
        Assert.assertEquals("Second", solve("1 1"))
    }

    @Test
    fun sample112() {
        Assert.assertEquals("First", solve("1 1 2"))
    }

    @Test
    fun sample12() {
        Assert.assertEquals("First", solve("1 2"))
    }

    @Test
    fun sample123() {
        Assert.assertEquals("Second", solve("1 2 3"))
    }

    @Test
    fun sample124() {
        Assert.assertEquals("First", solve("2 1 4"))
    }

    @Test
    fun sampleMy1() {
        Assert.assertEquals("First", solve("1"))
        Assert.assertEquals("First", solve("2"))
        Assert.assertEquals("First", solve("3"))
        Assert.assertEquals("First", solve("1 2"))
        Assert.assertEquals("First", solve("1 3"))
        Assert.assertEquals("First", solve("1 4"))
        Assert.assertEquals("First", solve("2 3"))
        Assert.assertEquals("First", solve("2 4"))
        Assert.assertEquals("First", solve("2 5"))
        Assert.assertEquals("First", solve("3 4"))
        Assert.assertEquals("First", solve("1 1 1"))
        Assert.assertEquals("First", solve("1 1 2"))
        Assert.assertEquals("First", solve("1 1 3"))
        Assert.assertEquals("First", solve("1 1 4"))
        Assert.assertEquals("First", solve("1 1 5"))// -> 1 1
        Assert.assertEquals("First", solve("1 2 2"))
        Assert.assertEquals("First", solve("1 2 4"))// -> 1 2 3
        Assert.assertEquals("First", solve("1 3 4"))
        Assert.assertEquals("First", solve("1 4 4"))// -> 4 4
//        Assert.assertEquals("First", solve("1 4 5"))// -> 1 1 5
        Assert.assertEquals("First", solve("2 2 2"))
        Assert.assertEquals("First", solve("2 2 3"))
        Assert.assertEquals("First", solve("2 2 4"))// -> 2 2
        Assert.assertEquals("First", solve("2 2 5"))// -> 2 2
        Assert.assertEquals("First", solve("2 3 3"))
        Assert.assertEquals("First", solve("2 3 4"))// -> 1 2 3
        Assert.assertEquals("First", solve("2 3 5"))// -> 1 2 3
        Assert.assertEquals("First", solve("2 4 4"))// -> 4 4
        Assert.assertEquals("First", solve("3 3 3"))// -> 3 3
        Assert.assertEquals("First", solve("3 3 4"))// -> 3 3
        Assert.assertEquals("First", solve("3 4 4"))// -> 4 4
        Assert.assertEquals("First", solve("3 4 5"))// -> 2 4 5
        Assert.assertEquals("First", solve("1 1 1 2"))
        Assert.assertEquals("First", solve("1 2 2 2"))
        Assert.assertEquals("First", solve("1 2 3 4"))
        Assert.assertEquals("First", solve("1 2 3 5"))

        Assert.assertEquals("Second", solve("1 1"))
        Assert.assertEquals("Second", solve("2 2"))
        Assert.assertEquals("Second", solve("3 3"))
        Assert.assertEquals("Second", solve("4 4"))
        Assert.assertEquals("Second", solve("1 2 3"))
//        Assert.assertEquals("Second", solve("2 4 5"))
        Assert.assertEquals("Second", solve("1 1 1 1"))
        Assert.assertEquals("Second", solve("1 1 2 2"))
        Assert.assertEquals("Second", solve("2 2 2 2"))
    }

    private fun solve(s: String): String {
        return nimSim(stringToArray(s))
    }

    private fun nimSim(pile: Array<Int>): String {
        var a = reduceArray(pile.toIntArray())
        if(a.isEmpty()) {
            return "Second"
        }

        var steps = 0
        while (a.isNotEmpty()) {
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

    private fun nimGame(pile: Array<Int>): String {
        if(pile.size == 1) {
            return "First"
        }
        val stonesCount = pile.sum()
        if(pile.size == stonesCount) {
            if(pile.size % 2 == 0) {
                return "Second"
            }
            return "First"
        }

        val singleCount = pile.filter { it == 1 }.size
        val multiCount = pile.size - singleCount
        if(multiCount == 1) {
            return "First"
        }

        val res = nimGame(multiCount, stonesCount - singleCount, pile.filter { it > 1 }.toIntArray())

        if(res + singleCount % 2 == 1) {
            return "First"
        }
        return "Second"
    }

    private fun nimGame(pilesCount: Int, stonesCount: Int, piles: IntArray): Int {
        if(pilesCount == 2) {
            if(piles[0] == piles[1]) {
                return 0
            }
            return 1
        }
        if(piles.all { it == piles[0] }) {
            return pilesCount % 2
        }

        if(stonesCount % 2 == 0) {
            return 0
        }
        return 1
    }
}