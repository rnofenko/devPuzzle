package rn.puzzle

object StringToArrayHelpers {
    fun stringToIntArray(line: String): Array<Int> {
        val cleaned = line.replace("(  +)".toRegex(), " ").trim()
        return cleaned.split(" ").map { it.toInt() }.toTypedArray()
    }
}