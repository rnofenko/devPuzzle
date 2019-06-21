package rn.puzzle.search.hard

import org.junit.Assert
import org.junit.Test
import rn.puzzle.FileHelper
import rn.puzzle.Stopwatch

class MaximizingMissionPointsTests {
    @Test
    fun sample1() {
        val solver = MaximizingMissionPointsSolver(1, 1)
        solver.add(1,1,1,3)
        solver.add(2,2,2,-1)
        solver.add(3,3,3,3)
        Assert.assertEquals(5, solver.solve())
    }

    @Test
    fun sample2() {
        val solver = MaximizingMissionPointsSolver(1, 1)
        solver.add(1,1,1,3)
        solver.add(2,2,2,-1)
        solver.add(3,3,3,3)
        solver.add(1,3,4,5)
        Assert.assertEquals(7, solver.solve())
    }

    @Test
    fun fileSample1() {
        val res = fileRunner("/Users/tkma0vh/Downloads/input01.txt")
        Assert.assertEquals(6825687, res)
    }

    @Test
    fun fileSample4() {
        val w = Stopwatch()
        val res = fileRunner("/Users/tkma0vh/Downloads/input04.txt")
        Assert.assertEquals(49999, res)
        w.show("Sample4")//16s - 1.04 - 0.44
    }

    @Test
    fun fileSample6() {
        val w = Stopwatch()
        val res = fileRunner("/Users/tkma0vh/Downloads/input06.txt")
        Assert.assertEquals(359638, res)
        w.show("Sample6")//
    }

    @Test
    fun fileSample7() {
        val w = Stopwatch()
        val res = fileRunner("/Users/tkma0vh/Downloads/input07.txt")
        Assert.assertEquals(1254375, res)
        w.show("Sample7")//
    }

    @Test
    fun fileSample9() {
        val w = Stopwatch()
        val res = fileRunner("/Users/tkma0vh/Downloads/input09.txt")
        Assert.assertEquals(703428446, res)
        w.show("Sample9")//177s - 30.22 - 13.86
    }

    private fun fileRunner(path: String): Long {
        val lines = FileHelper.load(path)
        val line = lines[0]
        val globalParams = line.split(" ")
        val n = globalParams[0].trim().toInt()
        val xDiff = globalParams[1].trim().toInt()
        val yDiff = globalParams[2].trim().toInt()
        val solver = MaximizingMissionPointsSolver(xDiff, yDiff)

        for (nItr in 1..n) {
            val latitudeLongitude = lines[nItr].split(" ")
            val latitude = latitudeLongitude[0].trim().toInt()
            val longitude = latitudeLongitude[1].trim().toInt()
            val height = latitudeLongitude[2].trim().toInt()
            val points = latitudeLongitude[3].trim().toInt()
            solver.add(latitude,longitude,height,points)
        }
        return solver.solve()
    }
}