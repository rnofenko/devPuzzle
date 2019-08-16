package rn.puzzle.string.hard

import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToLong

private const val CHARS_COUNT = 26

class AshtonAndStringSolver {
    fun ashtonString(text: String, k: Int): Char {
        val s = text.map { it - 'a' }.toIntArray()
        val cursor = Cursor(s, k - 1, 0)
        try {
            groupByChar(cursor)
        } catch (ex: IllegalArgumentException) {
            return ex.message!![0]
        }
        return text[0]
    }

    private fun groupByChar(cursor: Cursor) {
        val indices = generateSequence { ArrayList<Int>() }.take(CHARS_COUNT).toList()
        val s = cursor.s
        for (i in s.indices) {
            indices[s[i]].add(i)
        }

        for (indicesForChar in indices.filter { it.isNotEmpty() }) {
            count(indicesForChar, 0, cursor)
        }
    }

    private fun groupByChar(shift: Int, indices: List<Int>, cursor: Cursor) {
        val map = TreeMap<Int, ArrayList<Int>>()
        val s = cursor.s
        for (i in indices) {
            val idx = i + 1
            if(idx < s.size) {
                val list = map.getOrPut(s[idx]) { ArrayList() }
                list.add(idx)
            }
        }

        for (indicesForChar in map.values) {
            count(indicesForChar, shift, cursor)
        }
    }

    private fun count(indicesForChar: List<Int>, shift: Int, cursor: Cursor) {
        val idx = indicesForChar.first()
        if(indicesForChar.size == 1) {
            countForOneChar(idx, shift, cursor)
        } else {
            if(cursor.pos + shift + 1 > cursor.k) {
                showAnswer(cursor, idx, shift)
            } else {
                cursor.pos += shift + 1
            }
            groupByChar(shift + 1, indicesForChar, cursor)
        }
    }

    private fun countForOneChar(idx: Int, shift: Int, cursor: Cursor) {
        val length = cursor.s.size
        val count = calcCountOfSubstrings(idx, shift, length)
        if(cursor.pos + count > cursor.k) {
            for (i in idx until length) {
                val localLen = shift + i - idx + 1
                if(cursor.pos + localLen > cursor.k) {
                    showAnswer(cursor, idx, shift)
                }
                cursor.pos += localLen
            }
            throw IllegalArgumentException("z")
        } else {
            cursor.pos += count
        }
    }

    private fun showAnswer(cursor: Cursor, idx: Int, shift: Int) {
        val kk = cursor.k - cursor.pos
        val ki = idx + kk - shift
        val c = 'a' + cursor.s[ki.toInt()]
        throw IllegalArgumentException("$c")
    }

    private fun calcCountOfSubstrings(idx: Int, shift: Int, stringLength: Int): Long {
        val n = stringLength - idx
        val s = calcCountOfSubstrings(n)
        return s + shift * n
    }

    private fun calcCountOfSubstrings(n: Int): Long {
        val r = (1 + n) * (n / 2.0)
        return r.roundToLong()
    }

    private class Cursor(val s: IntArray, val k: Int, var pos: Long) {
        override fun toString(): String {
            return "k=$k p=$pos"
        }
    }
}