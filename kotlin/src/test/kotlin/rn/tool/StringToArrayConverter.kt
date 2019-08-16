package rn.tool

import java.lang.StringBuilder

object StringToArrayConverter {
    fun stringToArray(line: String): Array<Int> {
        val cleaned = line.replace("(  +)".toRegex(), " ").trim()
        return cleaned.split(" ").map { it.toInt() }.toTypedArray()
    }

    fun stringToIntArray(line: String): IntArray {
        return stringToArray(line).toIntArray()
    }

    fun stringToLongArray(line: String): Array<Long> {
        val cleaned = line.replace("(  +)".toRegex(), " ").trim()
        return cleaned.split(" ").map { it.toLong() }.toTypedArray()
    }

    fun stringTo2dArray(line: String): Array<Array<Int>> {
        val cleaned = line.replace("(  +)".toRegex(), " ").trim()
        val stringPairs = cleaned.split(",")
        return stringPairs
                .map { it
                        .split(" ")
                        .map { s -> s.toInt() }
                        .toTypedArray()
                }
                .toTypedArray()
    }

    fun stringTo2dIntArray(line: String): Array<IntArray> {
        val cleaned = line.replace("(  +)".toRegex(), " ").trim()
        val stringPairs = cleaned.split(",")
        return stringPairs
                .map { it
                        .split(" ")
                        .map { s -> s.toInt() }
                        .toIntArray()
                }
                .toTypedArray()
    }

    fun arrayToString(a: Array<Array<Int>>): String {
        val builder = StringBuilder()
        for (row in a) {
            val s = row.joinToString(" ")
            builder.append(s).append(",")
        }
        return builder.toString().dropLast(1)
    }
}