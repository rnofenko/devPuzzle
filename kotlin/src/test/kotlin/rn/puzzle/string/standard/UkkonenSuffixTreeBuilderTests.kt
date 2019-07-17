package rn.puzzle.string.standard

import org.junit.Test

class UkkonenSuffixTreeBuilderTests {

    @Test
    fun test1() {
        builder.build("abcabxabcd")
    }

    @Test
    fun test2() {
        builder.build("banana")
    }

    @Test
    fun test3() {
        builder.build("abcabcddd")
    }

    @Test
    fun test4() {
        builder.build("ababab")
    }

    private val builder = UkkonenSuffixTreeBuilder()
}