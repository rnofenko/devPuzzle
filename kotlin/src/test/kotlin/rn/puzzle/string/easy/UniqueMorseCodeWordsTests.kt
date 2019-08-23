package rn.puzzle.string.easy

import org.junit.Assert
import org.junit.Test
import java.lang.StringBuilder

class UniqueMorseCodeWordsTests {
    private fun uniqueMorseRepresentations(words: Array<String>): Int {
        val morseSymbols = arrayOf(".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--..")
        val charToMorseMap = morseSymbols.mapIndexed { index, morse ->  Pair('a' + index, morse)}.toMap()

        val convertedWords = HashSet<String>()
        for (word in words) {
            val builder = StringBuilder()
            for (c in word) {
                builder.append(charToMorseMap[c])
            }
            convertedWords.add(builder.toString())
        }

        return convertedWords.size
    }

    @Test
    fun test1() {
        Assert.assertEquals(2, uniqueMorseRepresentations(arrayOf("gin", "zen", "gig", "msg")))
    }
}