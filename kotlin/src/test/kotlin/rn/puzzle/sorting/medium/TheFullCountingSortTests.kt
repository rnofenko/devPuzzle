package rn.puzzle.sorting.medium

import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.StringBuilder
import kotlin.system.measureTimeMillis

class TheFullCountingSortTests {

    @Test
    fun sample1() {
        val data = arrayOf(arrayOf("1", "e"), arrayOf("2", "a"), arrayOf("1", "b"), arrayOf("3", "a"), arrayOf("4", "f"),
                arrayOf("1", "f"), arrayOf("2", "a"), arrayOf("1", "e"), arrayOf("1", "b"), arrayOf("1", "c"))
        val res = createAnswer(data)
        assertEquals("- - f e b c - a - -", res)
        System.out.println(res)
    }

    @Test
    fun perf1() {

        val data = ArrayList<Array<String>>()
        var k = 0
        for (i in 0..1000000) {
            k++
            if(k == 100) {
                k = 0
            }

            val charCode = k % 20 + 'a'.toByte()
            val a = arrayOf("" + k, charCode.toChar().toString())
            data.add(a)
        }

        val typedData = data.toTypedArray()

        val time = measureTimeMillis { createAnswer(typedData) }
        System.out.println("TIME $time")
    }

    private fun createAnswer(data: Array<Array<String>>): String {
        val agg = aggregate(data)
        val builder = StringBuilder()
        for(i in 0 until agg.size) {
            val list = agg[i]
            for (s in list) {
                builder.append(s).append(" ")
            }
        }

        return builder.trim().toString()
    }

    private fun aggregate(initialData: Array<Array<String>>): Array<ArrayList<String>> {
        val res = Array(100) { ArrayList<String>() }

        val middle = initialData.size / 2
        for ((i, pair) in initialData.withIndex()) {
            val isDash = i < middle
            val no = pair[0].toInt()
            res[no].add(if(isDash) "-" else pair[1])
        }

        return res
    }
}