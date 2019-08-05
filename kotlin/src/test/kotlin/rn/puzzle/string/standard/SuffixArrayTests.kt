package rn.puzzle.string.standard

import org.junit.Test

class SuffixArrayTests {
    @Test
    fun test1() {
        builder.build("abcabxabcd")
    }

    @Test
    fun test2() {
        builder.build("banana$")
    }

    @Test
    fun test3() {
        builder.build("abcabcddd")
    }

    @Test
    fun test4() {
        builder.build("ababa$")
    }

    @Test
    fun sample6() {
        builder.build("aabaa$")
    }

    @Test
    fun sample31() {
        builder.build("aabaaab$")
    }

    private val builder = SuffixArrayNaiveBuilder()
}