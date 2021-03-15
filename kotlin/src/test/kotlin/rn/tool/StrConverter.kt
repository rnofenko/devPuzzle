package rn.tool

import kotlin.collections.ArrayList

object StrConverter {

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

    fun toIntegerArray(line: String): Array<Int> {
        val cleaned = line.replace("(  +)".toRegex(), " ").trim()
        return cleaned.split(" ").map { it.toInt() }.toTypedArray()
    }

    @JvmStatic
    fun toIntArray(line: String): IntArray {
        if(line.startsWith("[")) {
            return bracketsStringToIntArray(line)
        }
        return toIntegerArray(line).toIntArray()
    }

    @JvmStatic
    fun toLongArray(line: String): LongArray {
        if(line.startsWith("[")) {
            return bracketsStringToLongArray(line)
        }
        return stringToLongArray(line).toLongArray()
    }

    @JvmStatic
    fun toLongList(line: String): List<Long> {
        val cleaned = line.replace("(  +)".toRegex(), " ").trim()
        return cleaned.split(" ").map { it.toLong() }.toList()
    }

    @JvmStatic
    fun toFloatArray(line: String): DoubleArray {
        val cleaned = line.replace("(  +)".toRegex(), " ").trim()
        return cleaned.split(" ").map { it.toDouble() }.toDoubleArray();
    }

    fun stringToLongArray(line: String): Array<Long> {
        val cleaned = line.replace("(  +)".toRegex(), " ").trim()
        return cleaned.split(" ").map { it.toLong() }.toTypedArray()
    }

    @JvmStatic
    fun to2dIntegerArray(line: String): Array<Array<Int>> {
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

    // format 1: "1 2,3 1,2 3"
    // format 2: "[[1,2],[3,1],[2,3]]"
    @JvmStatic
    fun to2dIntArray(line: String): Array<IntArray> {
        if(line.startsWith("[")) {
            return bracketsStringTo2dIntArray(line)
        }
        return spaceCommaStringTo2dIntArray(line)
    }

    private fun bracketsStringTo2dIntArray(line: String): Array<IntArray> {
        val noSpaces = line.replace("( +)".toRegex(), "")
        val cleaned = noSpaces.substring(2, noSpaces.length - 2)
        val stringPairs = cleaned.split("],[")
        return stringPairs
                .map { it
                        .split(",")
                        .map { s -> s.toInt() }
                        .toIntArray()
                }
                .toTypedArray()
    }

    private fun bracketsStringToIntArray(line: String): IntArray {
        val noSpaces = line.replace("( +)".toRegex(), "")
        val cleaned = noSpaces.substring(1, noSpaces.length - 1)
        return cleaned
                    .split(",")
                    .map { s -> s.toInt() }
                    .toIntArray();
    }

    private fun bracketsStringToLongArray(line: String): LongArray {
        val noSpaces = line.replace("( +)".toRegex(), "")
        val cleaned = noSpaces.substring(1, noSpaces.length - 1)
        return cleaned
            .split(",")
            .map { s -> s.toLong() }
            .toLongArray();
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
}