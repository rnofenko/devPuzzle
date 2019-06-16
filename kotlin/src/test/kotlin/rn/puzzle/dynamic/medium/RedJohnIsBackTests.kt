package rn.puzzle.dynamic.medium

import org.junit.Assert
import org.junit.Test

class RedJohnIsBackTests {

    @Test
    fun sample1() {
        Assert.assertEquals(0, redJohn(1))
        Assert.assertEquals(0, redJohn(2))
        Assert.assertEquals(0, redJohn(3))
        Assert.assertEquals(1, redJohn(4))
        Assert.assertEquals(2, redJohn(5))
        Assert.assertEquals(3, redJohn(7))
        Assert.assertEquals(4, redJohn(9))
        Assert.assertEquals(6, redJohn(10))
        Assert.assertEquals(15, redJohn(14))
        Assert.assertEquals(19, redJohn(15))
        Assert.assertEquals(42, redJohn(18))
        Assert.assertEquals(155, redJohn(23))
        Assert.assertEquals(269, redJohn(25))
        Assert.assertEquals(462, redJohn(27))
        Assert.assertEquals(1432, redJohn(31))
        Assert.assertEquals(3385, redJohn(34))
        Assert.assertEquals(4522, redJohn(35))
        Assert.assertEquals(10794, redJohn(38))
    }

    @Test
    fun sample40() {
        Assert.assertEquals(19385, redJohn(40))
    }

    @Test
    fun calcCombination() {
        Assert.assertEquals(15, calcCombination(3, 4))
        Assert.assertEquals(37, calcCombination(2, 36))
        Assert.assertEquals(561, calcCombination(3, 32))
        Assert.assertEquals(4495, calcCombination(4, 28))
        Assert.assertEquals(20475, calcCombination(5, 24))
        Assert.assertEquals(53130, calcCombination(6, 20))
        Assert.assertEquals(74613, calcCombination(7, 16))
    }

    private fun redJohn(n: Int): Int {
        var total = 1
        var bigBlocksCount = 0
        while (true) {
            bigBlocksCount++

            val smallBlocksCount = n - bigBlocksCount * 4
            if(smallBlocksCount < 0) {
                break
            }
            total += calcCombination(bigBlocksCount + 1, smallBlocksCount)
        }
        return calcPrimeNumbers(total)
    }

    private fun calcPrimeNumbers(limit: Int): Int {
        if(limit <2) {
            return 0
        }
        buildPrimeNumbers(limit)

        for(i in 0 until primes.size) {
            val p = primes[i]
            if(p == limit) {
                return i + 1
            } else if (p > limit) {
                return i
            }
        }
        return primes.size
    }

    private fun buildPrimeNumbers(limit: Int) {
        var current = primes.last()
        if(primes.last() >= limit) {
            return
        }

        while (current < limit) {
            current++
            var isPrime = true
            for (prime in primes) {
                if(current % prime == 0) {
                    isPrime = false
                    break
                }
            }
            if(isPrime) {
                primes.add(current)
            }
        }
    }

    private fun calcCombination(k: Int, n: Int): Int {
        val kn = Pair(k, n)
        if(knResults.containsKey(kn)) {
            return knResults[kn] ?: 0
        }

        if(k == 1 || n == 0) {
            return 1
        }
        if(n == 1) {
            return k
        }

        val kp = calcCombination(k - 1, n)
        val np = calcCombination(k, n - 1)
        val res = kp + np
        knResults[kn] = res

        return res
    }

    private val knResults = HashMap<Pair<Int, Int>, Int>()

    private val primes = arrayListOf(2,3,5)
}