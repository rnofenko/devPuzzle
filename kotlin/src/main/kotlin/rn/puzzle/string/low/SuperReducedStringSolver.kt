package rn.puzzle.string.low

class SuperReducedStringSolver {
    private val empty: String = "Empty String"

    fun superReducedString(s: String): String {
        if(s.isEmpty()) {
           return empty
        }

        var currentString = s
        var prevString = ""
        while (currentString.length != prevString.length) {
            prevString = currentString
            currentString = reduce(currentString)
        }

        if(currentString.isEmpty()) {
            return empty
        }

        return currentString
    }

    private fun reduce(s: String): String {
        if(s.isEmpty()) {
            return s
        }

        val builder = StringBuilder()
        var count = 1
        var letter = s[0]
        for (i in 1 until s.length) {
            if(s[i] == letter) {
                count++
            } else {
                if(count % 2 == 1) {
                    builder.append(letter)
                }
                count = 1
                letter = s[i]
            }
        }
        if(count % 2 == 1) {
            builder.append(letter)
        }

        return builder.toString()
    }
}