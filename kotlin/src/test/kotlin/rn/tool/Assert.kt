package rn.tool

object Assert {
    fun <T> assertListEquals(expected: List<T>, actual: List<T>) {
        org.junit.Assert.assertEquals(expected.size, actual.size)
        for (i in expected.indices) {
            org.junit.Assert.assertEquals(expected[i], actual[i])
        }
    }
}