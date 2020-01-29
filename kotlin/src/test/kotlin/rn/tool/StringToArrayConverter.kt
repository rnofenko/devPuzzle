package rn.tool

import java.lang.StringBuilder
import kotlin.collections.ArrayList

object StringToArrayConverter {

    fun doubleCharArrayToStringArray(input: Array<CharArray>): Array<String> {
        val result = ArrayList<String>();

        for (chars in input) {
            result.add(String(chars));
        }

        return result.toTypedArray();
    }

    fun stringArrayToDoubleCharArray(input: Array<String>): Array<CharArray> {
        return stringArrayToDoubleCharArray(input.toList());
    }

    fun stringArrayToDoubleCharArray(input: Iterable<String>): Array<CharArray> {
        val result = ArrayList<CharArray>();
        for (s in input) {
            result.add(s.toCharArray());
        }
        return result.toTypedArray();
    }

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
        if(line.startsWith("[")) {
            return bracketsStringTo2dIntArray(line)
        }
        return spaceCommaStringTo2dIntArray(line)
    }

    private fun bracketsStringTo2dIntArray(line: String): Array<IntArray> {
        val cleaned = line.trim().substring(2, line.length - 2)
        val stringPairs = cleaned.split("],[")
        return stringPairs
                .map { it
                        .split(",")
                        .map { s -> s.toInt() }
                        .toIntArray()
                }
                .toTypedArray()
    }

    private fun spaceCommaStringTo2dIntArray(line: String): Array<IntArray> {
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