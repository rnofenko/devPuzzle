@file:Suppress("SpellCheckingInspection")

package rn.puzzle.string.standard

import org.junit.Assert
import org.junit.Test

class UkkonenSuffixTreeBuilderTests {

    @Test
    fun test1() {
        solve("aabcdaabcdaabcx")
        solve("abcdbkabcx")
        solve("banana$")
        solve("aabaaab$")
    }

    @Test
    fun abcabcddd() {
        val expected = arrayOf(
                "-$",
                "-abc",
                "--abcddd$",
                "--ddd$",
                "-bc",
                "--abcddd$",
                "--ddd$",
                "-c",
                "--abcddd$",
                "--ddd$",
                "-d",
                "--$",
                "--d",
                "---$",
                "---d$"
        )
        compare("abcabcddd$", expected)
    }

    @Test
    fun abcabxabcd() {
        val expected = arrayOf(
                "-$",
                "-ab",
                "--c",
                "---abxabcd$",
                "---d$",
                "--xabcd$",
                "-b",
                "--c",
                "---abxabcd$",
                "---d$",
                "--xabcd$",
                "-c",
                "--abxabcd$",
                "--d$",
                "-d$",
                "-xabcd$"
        )
        compare("abcabxabcd$", expected)
    }

    @Test
    fun aabaa() {
        val expected = arrayOf(
                "-$",
                "-a",
                "--$",
                "--a",
                "---$",
                "---baa$",
                "--baa$",
                "-baa$"
        )
        compare("aabaa$", expected)
    }

    @Test
    fun ababa() {
        val expected = arrayOf(
                "-$",
                "-a",
                "--$",
                "--ba",
                "---$",
                "---ba$",
                "-ba",
                "--$",
                "--ba$"
        )
        compare("ababa$", expected)
    }

    @Test
    fun aabaab() {
        val expected = arrayOf(
                "-$",
                "-a",
                "--ab",
                "---$",
                "---aab$",
                "--b",
                "---$",
                "---aab$",
                "-b",
                "--$",
                "--aab$"
        )
        compare("aabaab$", expected)
    }

    @Test
    fun aabaaa() {
        val expected = arrayOf(
                "-$",
                "-a",
                "--$",
                "--a",
                "---$",
                "---a$",
                "---baaa$",
                "--baaa$",
                "-baaa$"
        )
        compare("aabaaa$", expected)
    }

    @Test
    fun aabaac() {
        val expected = arrayOf(
                "-$",
                "-a",
                "--a",
                "---baac$",
                "---c$",
                "--baac$",
                "--c$",
                "-baac$",
                "-c$"
        )
        compare("aabaac$", expected)
    }

    private fun solve(s: String): List<String> {
        return builder.buildAndPrint(s)
    }

    private fun compare(s: String, expected: Array<String>) {
        val res = builder.buildAndPrint(s)
        for(i in expected.indices) {
            Assert.assertEquals(expected[i], res[i])
        }
    }

    private val builder = SuffixTreeNaiveBuilder()
}