@file:Suppress("SpellCheckingInspection")

package rn.puzzle.string.hard

import org.junit.Assert
import org.junit.Test
import rn.tool.Stopwatch
import rn.tool.FileHelper
import java.util.*
import kotlin.math.abs

class AshtonAndStringTests {
    @Test
    fun test1() {
        //a, ac, b, ba, bac, c, d, db, dba, dbac
        Assert.assertEquals('c', solve("dbac", 3))
    }

    @Test
    fun test2() {
        //a ab abc abcd b bc bcd c cd d
        Assert.assertEquals('b', solve("abcd", 5))
    }

    @Test
    fun aabaacaad() {
        fullScan("aabaacaad")
    }

    @Test
    fun aabaac3() {
        //a aa aab
        compare("aabaac", 3)
    }

    @Test
    fun aabaac4() {
        compare("aabaac", 4)
    }

    @Test
    fun xulu() {
        //l lu u ul ulu
        compare("xulu", 4)
    }

    @Test
    fun nnva() {
        //a n nn nnv
        compare("nnva", 4)
    }

    @Test
    fun aabaacaadCompare() {
        fullScan("aabaacaad")
    }

    @Test
    fun aabaacCompare() {
        fullScan("aabaac")
    }

    @Test
    fun test3_2() {
        val s = "ricwielfhyxjkaexwmkawkqvzznbzkltsnpaucibcflmgplebxfzhxviwkmwuxrnmipimzkpfxdnoptsoyryvogsyuqtrjigrzodzasgxwtmnmebnxzkmickcufwdnewpuaovuwuspihdmlsmmdyuhi"
        Assert.assertEquals('s', compare(s, 9336))
    }

    @Test
    fun test3_3() {
        val s = "qxhddeuxgiscgpxxxebmpvycywyggzkwwtczzwzhhrknihlglosdjqhimfqufcrdvtfxpeeyxomgxxmlleouwvekcuejyxnwqstiwzgtquznrnaetozplfzqcgzadowwgrgeroyhjzuanvfilgyzlzrnfqqlfohngnsxbsektanixurlcpmnoddwvvhajoqpdkogcurvxgeuaxhcmtrcywyurhwbvmsawhhabawygcvgzeknydpyaqstxpwvdpvaycabcyakcxqdbcqbgiaiasbxjyunpqnnupozqqjsncwrgosmysuynywwwqjniyafoqgehrywvwncliqjaliplgmjyvwjwyqmowsxnqwjnlnatdlwqvlbdzkexintgdfxbxwonszcgmezqsxinjktiwxh"
        Assert.assertEquals('y', compare(s, 21568))
    }

    @Test
    fun test3_1() {
        val s = "zsbhetzecbhhepbqrrxwoipsrnqfaplbjmiqgkwklesrvvhmpeidnzxgopnphyqqnbitlfdwlxpgtxtibblqdlyramihkzazcisppynaxchqzcadfotizuazgihsjhsnrmchmphksqcruduatqkskktsvcmgjeudtwkfmtrgmuxgzugsmrmwdfqyichtjbwexijlcdtoxtuyndqzwcxbhnarrhlckjgjssvuvokuhgswjixhmujvjlnduafejmnbgkxbzhviophzzehlbsgkdvpxxvdhjqkpdiscrpmhethdaqqbkynnudmrapalhlckvuooldwryfuyxmzhknugsiaszafinhtkdjzqoxhncdlzpngbaciumimlkttyaokfylvoicbmhpnwctxcxhyjpmwafqahemmeyjtinwvulisneprdxppoblqgdsnkfbodmjlziittrlhxdaacqpstdjcgbrqgthlfqzhyhcuaodztdaxvrppuarbcitkdawltxstewnhmqgfwieraugwuzzyktjnthaoeuikrxtdoclkkpdmlklilkivdtlzanpgixtbvmglqrxbjapwmaeylpvqiirlwhrggmhbzpophlssoioakvavkyoughfcozkvlrymhpeqawkqgatrvvohteecmjeairxuiygrnkjpjvgpwzitwzbqdfspqyszprvapbteofuxacowdzqzytpbajtqilpzckzuovycasbaupzzopzmiqqiajakwqjyclsqiruilvkiljhbagqkzguzrvlnlunphggpzazkykujvcmwkcgjkbkdwvsjsgyzogrnjqzhauswweuijcsvdczbarksxlrlriaabahbubzsgvaryumbzlfzfprcckquvqwwqgzmhzepuhgubtwagbzlssovfgrawnwptxddykstrzpsslvyowkjpaguizwgmuvirbnqlflceawyorzeolnflvbvuxbjtztkagcllnpsqlqgfrkvcaapvdksambvlwfnckypzphpca"
        Assert.assertEquals('l', compare(s, 476910))
    }

    @Test
    fun test12() {
        val lines = FileHelper.loadByInputNo(12)
        val no = lines[2].toInt()
        Assert.assertEquals('e', solve(lines[1], no))
    }

    @Test
    fun random() {
        val rand = Random()
        for (iter in 0..10000) {
            var s = ""
            while (s.length < 7) {
                val c = 'a' + abs(rand.nextInt()) % 26
                s += c
            }
            fullScan(s)
        }
    }

    private fun solve(text: String, initK: Int): Char {
        return solver.ashtonString(text, initK)
    }

    private fun fullScan(s: String) {
        val fullStr = AshtonAndStringNaiveSolver().buildString(s)
        for (i in fullStr.indices) {
            val k = i + 1
            val actual = solver.ashtonString(s, k)
            val expected = fullStr[i]
            if(actual != fullStr[i]) {
                Assert.fail("|$s| k=$k expected=$expected actual=$actual")
            }
        }
    }

    private fun compare(s: String, k: Int): Char {
        val w = Stopwatch()
        val naiveResult = AshtonAndStringNaiveSolver().ashtonString(s, k)
        w.show("naive").start()
        val actualResult = solver.ashtonString(s, k)
        w.show("fast")
        Assert.assertEquals(naiveResult, actualResult)
        return actualResult
    }

    private val solver = AshtonAndStringSolver()
}