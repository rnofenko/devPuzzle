package rn.tool

import kotlin.math.abs

object Ass {
    @JvmStatic
    fun <T> assertListEquals(expected: List<T>, actual: List<T>) {
        org.junit.Assert.assertEquals("Lists have different sizes", expected.size, actual.size)
        for (i in expected.indices) {
            org.junit.Assert.assertEquals("At index " + i, expected[i], actual[i])
        }
    }

    @JvmStatic
    fun <T> assertIntArrayEquals(expected: IntArray, actual: IntArray) {
        org.junit.Assert.assertEquals("Arrays size", expected.size, actual.size)
        for (i in expected.indices) {
            org.junit.Assert.assertEquals("At index $i", expected[i], actual[i])
        }
    }

    @JvmStatic
    fun <T> assertLongArrayEquals(expected: LongArray, actual: LongArray) {
        org.junit.Assert.assertEquals(expected.size, actual.size)
        for (i in expected.indices) {
            org.junit.Assert.assertEquals(expected[i], actual[i])
        }
    }

    @JvmStatic
    fun assertEquals(a: Char, b: Char) {
        if (a == b) {
            return;
        }
        org.junit.Assert.assertEquals(a.toString(), b.toString());
    }

    @JvmStatic
    fun assertSize(size: Int, list: List<Char>) {
        org.junit.Assert.assertEquals(size, list.size);
    }

    @JvmStatic
    fun assertFloatArrays(expectedStr: String, bArray: DoubleArray) {
        val expected = StrConverter.toFloatArray(expectedStr)
        val precision = 0.00000001;
        if (expected.size != bArray.size) {
            org.junit.Assert.fail("Expected array size=${expected.size} when Actual array size=${bArray.size}")
        }
        for (i in expected.indices) {
            val a = expected[i]
            val b = bArray[i]
            if (abs(a - b) > precision) {
                org.junit.Assert.fail("Element $i. Expected=$a when Actual=$b")
            }
        }
    }
}