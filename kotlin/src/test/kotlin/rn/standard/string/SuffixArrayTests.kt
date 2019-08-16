@file:Suppress("SpellCheckingInspection")

package rn.standard.string

import org.junit.Assert
import org.junit.Test
import rn.tool.StringToArrayConverter.stringToIntArray
import rn.standard.string.suffix.array.SaIsSuffixArrayBuilder
import rn.standard.string.suffix.array.SuffixArrayNaiveBuilder
import rn.tool.Stopwatch
import java.lang.StringBuilder
import java.util.Random



class SuffixArrayTests {
    @Test
    fun cabbage() {
        val expectedResult = stringToIntArray("7 1 4 3 2 0 6 5")
        solveAndPrint("cabbage", expectedResult)
    }

    @Test
    fun baabaabac() {
        val expectedResult = stringToIntArray("9 1 4 2 5 7 0 3 6 8")
        solveAndPrint("baabaabac", expectedResult)
    }

    @Test
    fun banana() {
        val expectedResult = stringToIntArray("6 5 3 1 0 4 2")
        solveAndPrint("banana", expectedResult)
    }

    @Test
    fun dbac() {
        val expectedResult = stringToIntArray("4 2 1 3 0")
        solveAndPrint("dbac", expectedResult)
    }

    @Test
    fun test1() {
        val expectedResult = stringToIntArray("0")
        solveAndPrint("abcabxabcd", expectedResult)
    }

    @Test
    fun test3() {
        builders[0].build("abcabcddd")
    }

    @Test
    fun test4() {
        builders[0].build("ababa$")
    }

    @Test
    fun test6() {
        builders[0].build("siippii")
    }

    @Test
    fun sample6() {
        builders[0].build("aabaa$")
    }

    @Test
    fun sample31() {
        builders[0].build("aabaaab$")
    }

    @Test
    fun perf1() {
        val s = getRandString(20000)
        solveAndCompare(s)
    }

    @Test
    fun perf2() {
        val s = getRandString(200)
        builders[1].build(s)
    }

    private fun getRandString(size: Int): String {
        val rand = Random()
        rand.setSeed(123456789)
        val builder = StringBuilder()
        val aInt = 'a'.toInt()
        for (i in 0..size) {
            val nextNum = aInt + (Math.abs(rand.nextInt()) % 26)
            builder.append(nextNum.toChar())
        }
        return builder.toString()
    }

    private fun solveAndPrint(s: String, expectedResult: IntArray) {
        for (builder in builders) {
            println("\n" + builder.javaClass.simpleName)
            val w = Stopwatch()
            val a = builder.build(s)
            printSuffixArray(a, s)
            w.show(builder.javaClass.simpleName)
            Assert.assertArrayEquals(expectedResult, a)
        }
    }

    private fun solveAndCompare(s: String) {
        var expectedResult : IntArray? = null
        for (builder in builders) {
            val w = Stopwatch()
            val a = builder.build(s)
            w.show(builder.javaClass.simpleName)
            if(expectedResult != null) {
                Assert.assertArrayEquals(expectedResult, a)
            } else {
                expectedResult = a
            }
        }
    }

    private val builders = listOf(SuffixArrayNaiveBuilder(), SaIsSuffixArrayBuilder(false))

    private fun printSuffixArray(a: IntArray, s: String) {
        for (i in a) {
            print(i.toString().padStart(2, ' ') + " ")
            println(s.substring(i))
        }
    }
}