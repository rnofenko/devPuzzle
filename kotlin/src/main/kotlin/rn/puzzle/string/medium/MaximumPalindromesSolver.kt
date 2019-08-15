package rn.puzzle.string.medium

import rn.tool.Stopwatch
import java.math.BigInteger

class MaximumPalindromesSolver {
    fun initialize(s: String) {
        this.s = s.map { it - 'a' }.toIntArray()
        factorialMap.add(BigInteger.ONE)
    }

    fun answerQuery(l: Int, r: Int): Int {
        val totalCounts = getCounts(l, r)
        val singleCount = totalCounts.count { it % 2 == 1 }
        val pairCounts = totalCounts.map { it / 2 }.filter { it > 0 }.toIntArray()
        var facSize = 0
        val facDivs = ArrayList<Int>()
        for (i in pairCounts) {
            facSize += i
            if(i > 1) {
                facDivs.add(i)
            }
        }

        var t = BigInteger.ONE
        val w = Stopwatch()
        if(facSize > 0) {
            t = calcFactorial(facSize)
            w.show("1")
            val b = calcBottom(facDivs)
            w.show("2")
            if (b != BigInteger.ZERO) {
                t = t.divide(b)
            }
        }
        w.show("3")
        if(singleCount != 0) {
            t = t.multiply(BigInteger.valueOf(singleCount.toLong()))
        }
        val final = t.mod(BigInteger.valueOf(1000_000_007))
        return final.toInt()
    }

    private fun calcBottom(list: List<Int>): BigInteger {
        var b = BigInteger.ONE
        for (i in list) {
            val f = calcFactorial(i)
            b = b.multiply(f)
        }
        return b
    }

    private fun calcFactorial(k: Int): BigInteger {
        var b = factorialMap.last()
        for (i in (factorialMap.size + 1)..k) {
            b = b.multiply(BigInteger.valueOf(i.toLong()))
            factorialMap.add(b)
        }
        return factorialMap[k - 1]
    }

    private fun getCounts(l: Int, r: Int): IntArray {
        val counts = IntArray('z' - 'a' + 1)
        for (i in (l-1) until r) {
            counts[s[i]]++
        }
        return counts.filter { it > 0 }.toIntArray()
    }

    private var s: IntArray = IntArray(0)
    private var factorialMap = ArrayList<BigInteger>()
}