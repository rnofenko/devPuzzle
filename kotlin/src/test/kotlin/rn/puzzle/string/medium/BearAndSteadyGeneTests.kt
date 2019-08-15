package rn.puzzle.string.medium

import org.junit.Assert
import org.junit.Test

class BearAndSteadyGeneTests {
    private fun steadyGene(geneStr: String): Int {
        val gene = convertToInts(geneStr)
        val counts = calcCounts(gene)
        val pointer = Pointer(gene, counts)
        var minSize = Int.MAX_VALUE
        while (minSize > 0 && !pointer.isEnd()) {
            if(pointer.isValid()) {
                minSize = Math.min(minSize, pointer.size)
                pointer.decrease()
            } else {
                if(!pointer.increase()) {
                    break
                }
            }
        }
        return minSize
    }

    private fun calcCounts(gene: IntArray): IntArray {
        val counts = IntArray(4)
        gene.forEach { counts[it]++ }
        val min = counts.min() ?: 0
        return counts.map { it - min }.toIntArray()
    }

    private fun convertToInts(gene: String): IntArray {
        return gene.map { when(it) {
            'A' -> 0
            'C' -> 1
            'G' -> 2
            else -> 3
        } }.toIntArray()
    }

    class Pointer(private val gene: IntArray, private val counts: IntArray, var startIndex: Int = 0, var size: Int = 0) {
        fun isEnd(): Boolean {
            return startIndex + size > gene.size
        }

        fun isValid(): Boolean {
            val max = counts.max() ?: 0
            val diffs = counts.sumBy { Math.abs(it - max) }
            return diffs == size
        }

        fun decrease() {
            val g = gene[startIndex]
            counts[g]++
            startIndex++
            size--
        }

        fun increase(): Boolean {
            size++
            val index = startIndex + size - 1
            if(index >= gene.size) {
                return false
            }
            val g = gene[index]
            counts[g]--
            return true
        }
    }

    @Test
    fun test1() {
        Assert.assertEquals(5, steadyGene("GAAATAAA"))
    }

    @Test
    fun test2() {
        Assert.assertEquals(2, steadyGene("ACTGAAAG"))
    }
}