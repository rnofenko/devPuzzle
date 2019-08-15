package rn.puzzle.wip.string.hard

import org.junit.Assert
import org.junit.Test

class AshtonAndStringTests {
    private fun ashtonString(text: String, initK: Int): Char {
        val a = text.map { it - 'a' }.toIntArray()
        return ' '
    }

    @Test
    fun test1() {
        //a, ac, b, ba, bac, c, d, db, dba, dbac
        Assert.assertEquals('c', ashtonString("dbac", 3))
    }

    @Test
    fun test2() {
        Assert.assertEquals('b', ashtonString("abcd", 5))
    }

    class SuffixArrayNaiveBuilder {
        fun build(s: String): IntArray {
            val startIndexes = generateSequence(0, { it + 1 }).take(s.length + 1).toList()
            return startIndexes.sortedWith(SubStringComparator(s)).toIntArray()
        }

        class SubStringComparator(private val text: String) : Comparator<Int> {
            override fun compare(o1: Int?, o2: Int?): Int {
                val s1 = text.substring(o1 ?: 0)
                val s2 = text.substring(o2 ?: 0)
                return s1.compareTo(s2)
            }
        }
    }
}