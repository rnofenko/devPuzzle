package rn.puzzle.graph.medium

import org.junit.Assert
import org.junit.Test
import rn.tool.FileHelper
import rn.tool.Stopwatch
import rn.puzzle.game.medium.roadaandlibraries.RoadsAndLibrariesDependencySolver
import rn.tool.StringToArrayConverter.stringTo2dIntArray
import rn.tool.StringToArrayConverter.stringToLongArray

class RoadsAndLibrariesTests {
    private val solver = RoadsAndLibrariesDependencySolver()

    @Test
    fun sample1() {
        val a = stringTo2dIntArray("1 2,3 1,2 3")
        val res = solver.roadsAndLibraries(3, 2, 1, a)
        Assert.assertEquals(4, res)
    }

    @Test
    fun sample2() {
        val a = stringTo2dIntArray("1 3,3 4,2 4,1 2,2 3,5 6")
        val res = solver.roadsAndLibraries(6, 2, 5, a)
        Assert.assertEquals(12, res)
    }

    @Test
    fun sample3() {
        val a = stringTo2dIntArray("1 2,3 4,5 6,5 7,2 4,2 6")
        val res = solver.roadsAndLibraries(7, 2, 1, a)
        Assert.assertEquals(8, res)
    }

    @Test
    fun fileSample2() {
        val res = fileRunner("/Users/tkma0vh/Downloads/input02.txt")
        val expected = stringToLongArray("5649516 5295483 9261576 3960530 7629795 40216260 6701050 40280315 4614540 12407190")
        Assert.assertArrayEquals(expected, res)
    }

    @Test
    fun fileSample3() {
        val res = fileRunner("/Users/tkma0vh/Downloads/input03.txt")
        val expected = stringToLongArray("7850257285 6785201034 813348013 4211840970 8610471142 7263742960 4331105640 1226092626 7288635830 8276704464")
        Assert.assertArrayEquals(expected, res)
    }

    private fun fileRunner(path: String): Array<Long> {
        val resList = ArrayList<Long>()
        val lines = FileHelper.load(path)
        var i = 0
        val q = lines[i++].trim().toInt()

        for (qItr in 1..q) {
            val nmC_libC_road = lines[i++].split(" ")
            val n = nmC_libC_road[0].trim().toInt()
            val m = nmC_libC_road[1].trim().toInt()
            val c_lib = nmC_libC_road[2].trim().toInt()
            val c_road = nmC_libC_road[3].trim().toInt()
            val cities = Array(m) { IntArray(2) }

            for (j in 0 until m) {
                val parts = lines[i++].split(" ")
                val cityParts = cities[j]
                cityParts[0] = parts[0].toInt()
                cityParts[1] = parts[1].toInt()
            }

            val result = solve(n, c_lib, c_road, cities)
            resList.add(result)
            println(result)
        }
        return resList.toTypedArray()
    }

    private fun solve(n: Int, libCost: Int, roadCost: Int, graph: Array<IntArray>): Long {
        val w = Stopwatch()
        val res = solver.roadsAndLibraries(n, libCost, roadCost, graph)
        w.show()
        return res
    }
}