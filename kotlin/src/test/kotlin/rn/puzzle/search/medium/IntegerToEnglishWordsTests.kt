package rn.puzzle.search.medium

import org.junit.Assert
import org.junit.Test

class IntegerToEnglishWordsTests {

    @Test
    fun test1() {
        Assert.assertEquals("One Hundred Twenty Three", solve(123))
    }

    @Test
    fun test2() {
        Assert.assertEquals("Twelve Thousand Three Hundred Forty Five", solve(12345))
    }

    @Test
    fun test3() {
        Assert.assertEquals("One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven", solve(1234567))
    }

    @Test
    fun test4() {
        Assert.assertEquals("One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One", solve(1234567891))
    }

    @Test
    fun test0() {
        Assert.assertEquals("Zero", solve(0))
    }

    @Test
    fun test1000() {
        Assert.assertEquals("One Thousand", solve(1000))
    }

    @Test
    fun test20() {
        Assert.assertEquals("Twenty", solve(20))
    }

    private fun solve(initNum: Int): String {
        val threeDigits = mapOf(1000 to "Thousand", 1000_000 to "Million", 1000_000_000 to "Billion")

        var s = ""
        var num = initNum
        for (threeDigitValue in threeDigits.keys.sortedDescending()) {
            val count = num / threeDigitValue
            num -= count * threeDigitValue
            if(count == 0) {
                continue
            }

            s += " " + wordThreeDigits(count) + " " + threeDigits[threeDigitValue]
        }

        if(num > 0 || s.isEmpty()) {
            s += " " + wordThreeDigits(num)
        }
        return s.trim()
    }

    private fun wordThreeDigits(initNum: Int): String {
        val digitMap = mapOf(1 to "One", 2 to "Two", 3 to "Three", 4 to "Four", 5 to "Five", 6 to "Six", 7 to "Seven",
                8 to "Eight", 9 to "Nine", 10 to "Ten", 11 to "Eleven", 12 to "Twelve", 13 to "Thirteen", 15 to "Fifteen",
                18 to "Eighteen", 0 to "Zero")
        val tenMap = mapOf(2 to "Twenty", 3 to "Thirty", 4 to "Forty", 5 to "Fifty", 6 to "Sixty", 7 to "Seventy",
                8 to "Eighty", 9 to "Ninety")

        var num = initNum
        var s = ""
        val hundredCount = num / 100
        if(hundredCount > 0) {
            s = digitMap[hundredCount] + " Hundred"
        }

        num %= 100
        if(num >= 20) {
            val tenCount = num / 10
            s += " " + tenMap[tenCount]
            num %= 10
        }

        if(num == 0 && s.isNotEmpty()) {
            return s.trim()
        }

        if(digitMap.containsKey(num)) {
            s += " " + digitMap[num]
        } else {
            s += " " + digitMap[num % 10] + "teen"
        }

        return s.trim()
    }
}