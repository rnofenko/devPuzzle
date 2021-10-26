package rn.puzzle.string.medium

import org.junit.Assert
import org.junit.Test

class SherlockAndTheValidStringTests {
    @Test
    fun sample1() {
        val res = isValid("aabbccddeefghi")
        Assert.assertEquals("NO", res)
    }

    @Test
    fun sample2() {
        val res = isValid("aabbcd")
        Assert.assertEquals("NO", res)
    }

    @Test
    fun sample3() {
        val res = isValid("aabbc")
        Assert.assertEquals("YES", res)
    }

    @Test
    fun sample4() {
        val res = isValid("ibfdgaeadiaefgbhbdghhhbgdfgeiccbiehhfcggchgghadhdhagfbahhddgghbdehidbibaeaagaeeigffcebfbaieggabcfbiiedcabfihchdfabifahcbhagccbdfifhghcadfiadeeaheeddddiecaicbgigccageicehfdhdgafaddhffadigfhhcaedcedecafeacbdacgfgfeeibgaiffdehigebhhehiaahfidibccdcdagifgaihacihadecgifihbebffebdfbchbgigeccahgihbcbcaggebaaafgfedbfgagfediddghdgbgehhhifhgcedechahidcbchebheihaadbbbiaiccededchdagfhccfdefigfibifabeiaccghcegfbcghaefifbachebaacbhbfgfddeceababbacgffbagidebeadfihaefefegbghgddbbgddeehgfbhafbccidebgehifafgbghafacgfdccgifdcbbbidfifhdaibgigebigaedeaaiadegfefbhacgddhchgcbgcaeaieiegiffchbgbebgbehbbfcebciiagacaiechdigbgbghefcahgbhfibhedaeeiffebdiabcifgccdefabccdghehfibfiifdaicfedagahhdcbhbicdgibgcedieihcichadgchgbdcdagaihebbabhibcihicadgadfcihdheefbhffiageddhgahaidfdhhdbgciiaciegchiiebfbcbhaeagccfhbfhaddagnfieihghfbaggiffbbfbecgaiiidccdceadbbdfgigibgcgchafccdchgifdeieicbaididhfcfdedbhaadedfageigfdehgcdaecaebebebfcieaecfagfdieaefdiedbcadchabhebgehiidfcgahcdhcdhgchhiiheffiifeegcfdgbdeffhgeghdfhbfbifgidcafbfcd")
        Assert.assertEquals("YES", res)
    }

    @Test
    fun sample5() {
        val res = isValid("aaaabbcc")
        Assert.assertEquals("NO", res)
    }

    @Test
    fun sample6() {
        val res = isValid("aaaaabc")
        Assert.assertEquals("NO", res)
    }

    private fun isValid(s: String): String {
        val r = s.groupingBy { it }.eachCount().values.sorted()
        val g = r.groupingBy { it }.eachCount()
        if(g.count() > 2) {
            return "NO"
        }
        if(g.count() < 2) {
            return "YES"
        }
        if((g.values.minOrNull() ?: 0) != 1) {
            return "NO"
        }
        val maxLen = g.keys.maxOrNull() ?: 0
        val minLen = g.keys.minOrNull() ?: 0
        val minLenCount = g[minLen] ?: 0
        if(minLen == 1 && minLenCount == 1) {
            return "YES"
        }
        if(maxLen - minLen == 1) {
            return "YES"
        }
        return "NO"
    }
}