package rn.puzzle.string.easy

import org.junit.Assert
import org.junit.Test

class SeparateTheNumbersTests {
    @Test
    fun sample1() {
        val res = findSeparateNumber("1234")
        Assert.assertEquals("YES 1", res)
    }

    @Test
    fun sample2() {
        val res = findSeparateNumber("91011")
        Assert.assertEquals("YES 9", res)
    }

    @Test
    fun sample3() {
        val res = findSeparateNumber("99100")
        Assert.assertEquals("YES 99", res)
    }

    @Test
    fun sample4() {
        Assert.assertEquals("NO", findSeparateNumber("11111111111111111111111111111111"))
        Assert.assertEquals("NO", findSeparateNumber("88888888888888888888888888888888"))
        Assert.assertEquals("YES 1000", findSeparateNumber("10001001100210031004100510061007"))
    }

    @Test
    fun sample5() {
        Assert.assertEquals("YES 9007199254740992", findSeparateNumber("90071992547409929007199254740993"))
    }

    fun separateNumbers(s: String): Unit {
        val number = findSeparateNumber(s)
        System.out.println(number)
    }

    private fun findSeparateNumber(s: String, length: Int = 1): String {
        if(length * 2 > s.length || length > 18) {
            return "NO"
        }

        val startNumber = s.substring(0, length).toLong()
        var nextNumber = startNumber
        var str = s.substring(length)

        while (str.isNotEmpty()) {
            nextNumber++
            val numberStr = nextNumber.toString()
            if(numberStr.length > str.length) {
                return findSeparateNumber(s, length + 1)
            }
            if(str.startsWith(numberStr)) {
                str = str.substring(numberStr.length)
            } else {
                return findSeparateNumber(s, length + 1)
            }
        }

        return "YES $startNumber"
    }
}