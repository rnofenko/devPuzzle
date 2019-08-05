package rn.puzzle.wip.string.hard

import org.junit.Assert
import org.junit.Test
import rn.puzzle.FileHelper
import rn.puzzle.string.hard.StringFunctionCalculationSolver

class StringFunctionCalculationTests {
    @Test
    fun sample1() {
        Assert.assertEquals(12, solve("aaaaaa"))
    }

    @Test
    fun sample2() {
        Assert.assertEquals(9, solve("abcabcddd"))
    }

    @Test
    fun sample3() {
        Assert.assertEquals(12, solve("aabaaabaaa"))
    }

    @Test
    fun sample31() {
        Assert.assertEquals(7, solve("aabaaab"))
    }

    @Test
    fun sample4() {
        Assert.assertEquals(6, solve("ababa"))
    }

    @Test
    fun sample5() {
        Assert.assertEquals(8, solve("ababab"))
    }

    @Test
    fun sample6() {
        Assert.assertEquals(5, solve("aabaa"))
    }

    @Test
    fun input05() {
        Assert.assertEquals(6965250, fileRunner(5))
        //8.5s
    }

    @Test
    fun input06() {
        Assert.assertEquals(37928804, fileRunner(6))
        //34.9s
    }

    @Test
    fun input07() {
        Assert.assertEquals(449445, fileRunner(7))
        //9.5s
    }

    @Test
    fun input10() {
        Assert.assertEquals(90000, fileRunner(10))
        //4s
    }

    private fun fileRunner(no: Int): Int {
        val lines = FileHelper.loadByInputNo(no)
        val line = lines[0]
        return solve(line)
    }

    private fun solve(s: String): Int {
        val solver = StringFunctionCalculationSolver()
        return solver.solve(s)
    }

    private fun maxValue(t: String): Int {
        val stats = calcOccurrence(t)

        var max = t.length
        for (stat in stats) {
            val s = stat.map { it }.sortedByDescending { it.key }
            if(s.isEmpty()) {
                continue
            }

            for (i in 2..s[0].key) {
                var sum = 0
                for (entry in s.filter { it.key >= i }) {
                    val occurrenceCount = (entry.key - i + 1)
                    val points = occurrenceCount * i
                    val total = points * entry.value
                    sum += total
                }

                max = Math.max(max, sum)
            }
        }

        return max
    }

    private fun calcOccurrence(s: String): Array<HashMap<Int, Int>> {
        var count = 1
        val stats = generateSequence { HashMap<Int, Int>() }.take('z' - 'a' + 1).toList().toTypedArray()
        for (i in 1 until s.length) {
            if(s[i] != s[i-1]) {
                if(count > 1) {
                    val map = stats[s[i-1]-'a']
                    val existingCount = map.getOrPut(count) { 0 }
                    map[count] = existingCount + 1
                    count = 1
                }
            } else {
                count++
            }
        }
        if(count > 1) {
            val map = stats[s[s.length-1]-'a']
            val existingCount = map.getOrPut(count) { 0 }
            map[count] = existingCount + 1
        }

        return stats
    }
}