package rn.puzzle.wip.string.hard

import org.junit.Assert
import org.junit.Test

class StringFunctionCalculationTests {
    @Test
    fun sample1() {
        Assert.assertEquals(12, maxValue("aaaaaa"))
    }

    @Test
    fun sample2() {
        Assert.assertEquals(9, maxValue("abcabcddd"))
    }

    @Test
    fun sample3() {
        Assert.assertEquals(10, maxValue("aa aaa aaa".replace(' ', 'x')))
    }

    @Test
    fun sample4() {
        Assert.assertEquals(6, maxValue("ababa"))
    }

    @Test
    fun sample5() {
        Assert.assertEquals(8, maxValue("ababab"))
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