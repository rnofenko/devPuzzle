package rn.puzzle.sorting.medium

import org.junit.Assert
import org.junit.Test
import rn.puzzle.FileHelper
import java.util.Random



class LilysHomeworkTests {
    @Test
    fun sample1() {
        Assert.assertEquals(2, lilysHomework(arrayOf(2,5,3,1)))
    }

    @Test
    fun sample2() {
        Assert.assertEquals(2, lilysHomework(arrayOf(3,4,2,5,1)))
    }

    @Test
    fun sample3() {
        Assert.assertEquals(1, lilysHomework(arrayOf(3,9,9,3)))
    }

    @Test
    fun fileSample1() {
        val res = fileRunner("/Users/tkma0vh/Downloads/input01.txt")
        Assert.assertEquals(268, res)
    }

    @Test
    fun random() {
        val rand = Random()
        var w = 0
        var d = 0
        var l = 0
        for (iter in 0..100000) {
            val list = ArrayList<Int>()
            while (list.size < 10) {
                val n = rand.nextInt() % 100
                if(n < 0 || list.contains(n)) {
                    continue
                }
                list.add(n)
            }
            val r = compare(list.toTypedArray())
            if(r == 1) {
                w++
            } else if(r == 0) {
                d++
            } else {
                l++
            }
        }

        println("$w $d $l")
    }

    private fun compare(arr: Array<Int>): Int {
        val c1 = find(arr, true)
        val c2 = solveWithMin(arr.clone())
        return c2.compareTo(c1)
    }

    private fun lilysHomework(arr: Array<Int>): Int {
        val c1 = find(arr, true)
        val c2 = find(arr, false)
        return Math.min(c1, c2)
    }

    private fun find(arr: Array<Int>, asc: Boolean): Int
    {
        val pairs = ArrayList<Pair<Int, Int>>()
        arr.forEachIndexed { index, value -> pairs.add(Pair(value, index)) }
        if(asc) {
            pairs.sortBy { it.first }
        } else {
            pairs.sortByDescending { it.first }
        }

        val vis = BooleanArray(arr.size)

        var count = 0
        for (i in 0 until arr.size)
        {
            if (vis[i] || pairs[i].second == i)
                continue

            var cycleSize = 0
            var j = i
            while (!vis[j])
            {
                vis[j] = true
                j = pairs[j].second
                cycleSize++
            }

            if(cycleSize > 0)
            {
                count += (cycleSize - 1)
            }
        }

        return count
    }

    private fun fileRunner(path: String): Int {
        val lines = FileHelper.load(path)
        val a = lines[1].split(" ").map { it.toInt() }.toTypedArray()
        return lilysHomework(a)
    }

    private fun solveWithMin(arr: Array<Int>): Int {
        var count = 0
        for(i in 0 until arr.size - 1) {
            var minInd = i + 1
            for(j in i + 2 until arr.size) {
                if(arr[j] < arr[minInd]) {
                    minInd = j
                }
            }
            val v = arr[i]
            if(arr[minInd] < v) {
                arr[i] = arr[minInd]
                arr[minInd] = v
                count++
            }
        }
        return count
    }
}