package rn.puzzle.greedy.easy

import org.junit.Assert
import org.junit.Test
import rn.tool.FileHelper
import rn.tool.StringToArrayConverter.stringToArray

class LargestPermutationTests {
    @Test
    fun sample1() {
        val res = largestPermutation(1, stringToArray("4 2 3 5 1"))
        val expected = stringToArray("5 2 3 4 1")
        Assert.assertArrayEquals(expected, res)
    }

    @Test
    fun sample2() {
        val res = largestPermutation(5, stringToArray("1 2 3 4 5"))
        val expected = stringToArray("5 4 3 2 1")
        Assert.assertArrayEquals(expected, res)
    }

    @Test
    fun sample3() {
        val res = largestPermutation(3, stringToArray("8 7 6 5 4 2 3 1"))
        val expected = stringToArray("8 7 6 5 4 3 2 1")
        Assert.assertArrayEquals(expected, res)
    }

    @Test
    fun fileSample1() {
        fileRunner("input01.txt", "output01.txt")
    }

    private fun fileRunner(path: String, outputPath: String) {
        val prefix = "/Users/tkma0vh/Downloads/"
        val lines = FileHelper.load(prefix + path)
        val k = lines[0].split(" ")[1].toInt()
        val c = lines[1].split(" ").map { it.toInt() }.toTypedArray()

        val outLines = FileHelper.load(prefix + outputPath)
        val expectedRes = outLines[0].split(" ").map { it.toInt() }.toTypedArray()
        val res = largestPermutation(k, c)
        Assert.assertArrayEquals(expectedRes, res)
    }

    private fun largestPermutation(k: Int, arr: Array<Int>): Array<Int> {
        val indices = IntArray(arr.size)
        val lastIndex = indices.indices.last
        for(i in arr.indices) {
            indices[lastIndex - (arr[i] - 1)] = i
        }

        var swapsCount = k
        for(expectedIndex in indices.indices) {
            val expectedValue = lastIndex - expectedIndex + 1
            val realValue = arr[expectedIndex]
            if(expectedValue != realValue) {
                val realIndex = indices[expectedIndex]

                arr[realIndex] = realValue
                arr[expectedIndex] = expectedValue

                indices[expectedIndex] = expectedIndex
                indices[lastIndex - (realValue - 1)] = realIndex

                swapsCount--
                if(swapsCount == 0) {
                    break
                }
            }
        }

        return arr
    }
}