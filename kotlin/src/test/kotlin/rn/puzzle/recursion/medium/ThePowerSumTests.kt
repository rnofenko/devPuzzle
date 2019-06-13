package rn.puzzle.recursion.medium

import org.junit.Assert
import org.junit.Test

//https://www.hackerrank.com/challenges/the-power-sum/problem
class ThePowerSumTests {
    @Test
    fun sample1() {
        val res = powerSum(100, 3)
        Assert.assertEquals(1, res)
    }

    @Test
    fun sample2() {
        val res = powerSum(100, 2)
        Assert.assertEquals(3, res)
    }

    @Test
    fun sample3() {
        val res = powerSum(10, 2)
        Assert.assertEquals(1, res)
    }

    @Test
    fun sample4() {
        val res = powerSum(13, 2)
        Assert.assertEquals(1, res)
    }

    private fun powerSum(target: Int, n: Int): Int {
        val refTable = createRefTable(target, n)
        return calc(refTable, target, 0, 0)
    }

    private fun calc(ref: IntArray, target: Int, startIndex: Int, sum: Int): Int {
        var count = 0
        for(i in startIndex until ref.size) {
            val v = ref[i]
            if(sum + v > target) {
                continue
            }
            val currentSum = sum + v
            if(currentSum == target) {
                count++
                continue
            }
            count += calc(ref, target, i + 1, currentSum)
        }
        return count
    }

    private fun createRefTable(max: Int, powInt: Int): IntArray {
        val list = ArrayList<Int>()
        val powDouble = powInt.toDouble()
        var n = 1.0
        while (true) {
            val r = Math.pow(n, powDouble).toInt()
            if(r > max) {
                break
            }
            list.add(r)
            n += 1
        }
        list.reverse()
        return list.toIntArray()
    }
}