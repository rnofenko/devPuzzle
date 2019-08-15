package rn.standard.string

import org.junit.Assert
import org.junit.Test
import rn.tool.Assert.assertListEquals

class AhoCorasickTests {
    @Test
    fun trie1() {
        val root = finder.buildTrie(listOf("a", "ab", "bab", "bc", "bca", "c", "caa"))
        Assert.assertEquals(3, root.kids.size)
    }

    @Test
    fun find1() {
        val root = finder.buildTrie(listOf("a", "ab", "bab", "bc", "bca", "c", "caa"))
        val res = finder.find(root, "abccab")
        assertListEquals(listOf("a", "ab", "bc", "c", "c", "a", "ab"), res)
    }

    private val finder = AhoCorasickFinder()
}