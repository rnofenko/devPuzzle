package rn.puzzle.graph.medium

import org.junit.Assert
import org.junit.Test
import rn.puzzle.FileHelper
import rn.puzzle.tool.StringToArrayConverter.stringTo2dArray
import rn.puzzle.tool.StringToArrayConverter.stringToIntArray

class CrabGraphsTests {
    @Test
    fun sample8_1() {
        val g = stringTo2dArray("19 62,76 23,19 51,20 6,33 12,72 20,52 60,83 63,27 62,12 70,35 15,39 72,61 42,70 72,57 35,10 77,41 49,44 13,74 6,51 49,71 31,5 20,46 83,46 62,78 2,2 47,2 60,82 28,50 73,55 46,37 78,17 32,45 36,10 78,64 45,3 25,55 71,74 39,19 52,24 70,20 9,14 81,8 56,62 49,34 65,31 4,48 10,84 1,73 64,20 78,84 15,18 24,11 24,3 55,1 21,62 78,74 66,36 75,45 44,1 72,49 1,4 38,74 27,19 80,53 4,52 39,48 36,45 58,80 18,24 16,45 40,74 54,78 80,78 41,5 58,44 8,58 43,38 60,55 42,11 26,42 57,61 80,55 27,6 56,2 72,66 43,74 61,45 1,43 13,29 5,67 1,20 62,11 50,55 40,53 44,71 69,46 40,41 83,42 32,5 39,32 33,41 43,7 68,2 80,57 83,59 46,67 75,35 65,59 41,75 47,38 17,28 50,49 77,42 18,8 22,34 49,64 78,1 13,70 52,71 12,18 31,74 80,57 44,82 39,77 8,38 69,78 71,43 48,4 11,38 44,49 65,55 19,43 2,23 49,33 19,62 14,27 32,34 48,19 54,35 54,84 70,9 61,77 27,13 62,66 57,54 14,64 62,57 20,52 78,36 3,42 65,54 55,44 61,48 39,83 24,79 37,59 6,9 15,65 29,63 50,21 2,71 22,15 65,40 56,52 34,48 28,48 40,20 79,45 52,56 59,69 16,18 84,61 64,1 65,40 12,69 83,4 52,49 79,21 14,13 49,32 72,72 23,9 79,25 11,32 13,67 13,74 83,50 71,61 66,22 77,43 62,39 84,44 48,33 82,44 59,15 80,30 32,68 46,42 3,35 42,61 46,34 78,13 65,66 9,57 45,51 60,43 84,8 5,18 81,60 65,68 67,53 13,41 14,40 6,19 23,69 58,80 25,22 5,48 59,59 53,77 12,49 66,75 12,68 30,12 9,65 70,27 43,34 56,74 24,10 46,61 65,47 31,44 77,74 64,80 42,56 4,6 28,78 74,24 39,5 78,3 83,29 84,63 42,76 68,39 10,81 56,51 59,46 36,78 47,57 43,80 68,75 77,57 27,50 84,15 21,16 33,46 43,77 6")
        Assert.assertEquals(84, crabGraphs(100,82,g))
    }

    private fun crabGraphs(n: Int, t: Int, graph: Array<Array<Int>>): Int {
        val links = buildMatrix(n, graph)
        val nodes = buildNodes(n, links)

        var process = nodes.filter { it.kids.isNotEmpty() }

        removeOrphans(process, t)
        removeOrphans(process, t)

        while (true) {
            process = process.filter { it.kids.size > 0 && !it.isSelected }
            if(process.isEmpty()) {
                break
            }

            process = process.filter { it.kids.size > 1 }.sortedBy { it.kids.size }
            val head = process[0]
            head.selectAsHead()

            removeOrphans(process, t)
        }
        for (node in nodes.filter { it.isHead && it.kids.isNotEmpty() }.sortedBy { it.kids.size + it.selectedKidsCount }) {
            while (node.kids.isNotEmpty() && node.selectedKidsCount < t) {
                node.selectAndRemoveKid()
            }
        }

        return nodes.filter { it.isSelected }.size
    }

    private fun removeOrphans(nodes: List<Node>, limit: Int) {
        removeOrphans(nodes, limit, 0)
        removeOrphans(nodes, limit, 1000)
    }

    private fun removeOrphans(nodes: List<Node>, limit: Int, parentsLimit: Int) {
        var process = nodes
        while (true) {
            process = process
                    .filter { !it.isHead && it.hasOrphan(parentsLimit) }
                    .sortedBy { it.kids.size * 100 + it.parentsCount }
            if(process.isEmpty()) {
                break
            }
            val head = process[0]
            head.selectAsHead()
            while (head.hasOrphan(parentsLimit) && head.selectedKidsCount < limit) {
                head.selectAndRemoveKid()
            }
        }
    }

    private fun buildNodes(n: Int, matrix: Array<IntArray>): List<Node> {
        val nodes = generateSequence(0) { it + 1 }.take(n).map { Node(it) }.toList()
        for (node in nodes) {
            val row = matrix[node.id]
            for ((ind) in row.withIndex().filter { it.value == 1 }) {
                node.kids.add(nodes[ind])
            }
        }

        return nodes
    }

    private fun buildMatrix(n: Int, graph: Array<Array<Int>>): Array<IntArray> {
        val a = Array(n) {IntArray(n)}
        for (row in graph) {
            a[row[0] - 1][row[1] - 1] = 1
            a[row[1] - 1][row[0] - 1] = 1
        }
        return a
    }

    private class Node(val id: Int,
                       val kids : ArrayList<Node> = ArrayList(),
                       var isHead: Boolean = false,
                       var isLeaf: Boolean = false,
                       var selectedKidsCount: Int = 0,
                       var parentsCount: Int = 0
                       ) {
        val isSelected: Boolean
            get() = isHead || isLeaf

        fun hasOrphan(parentsLimit: Int): Boolean {
            return kids.any { k -> k.kids.size < 2 && k.parentsCount <= parentsLimit && !k.isSelected }
        }

        fun selectAsHead() {
            isHead = true
            for (kid in kids) {
                kid.kids.remove(this)
                kid.parentsCount++
            }
            selectAndRemoveKid()
        }

        fun selectAndRemoveKid() {
            val kid = kids.sortedBy { it.kids.size * 100 + it.parentsCount }[0]
            removeKid(kid)
        }

        fun removeKid(kid: Node) {
            if(kid.isHead) {
                kids.remove(kid)
                return
            }

            if(!kid.isLeaf) {
                selectedKidsCount++
            }
            kid.isLeaf = true
            for (parent in kid.kids) {
                parent.kids.remove(kid)
            }
            kids.remove(kid)
        }

        override fun toString(): String {
            var s = "#${id+1} kids=${kids.size} selected=$selectedKidsCount"
            if(isSelected) {
                s += if(isHead) " HEAD" else " LEAF"
            }
            return s
        }
    }

    @Test
    fun sample1() {
        val g = stringTo2dArray("1 4,2 4,3 4,5 4,5 8,5 7,5 6")
        Assert.assertEquals(6, crabGraphs(8,2,g))
    }

    @Test
    fun sample2() {
        val g = stringTo2dArray("1 2,2 3,3 4,4 5,5 6,6 1,1 4,2 5")
        Assert.assertEquals(6, crabGraphs(6,3,g))
    }

    @Test
    fun sample1_1() {
        val g = stringTo2dArray("46 44,46 10,20 16,43 21")
        Assert.assertEquals(7, crabGraphs(50,4,g))
    }

    @Test
    fun sample1_2() {
        val g = stringTo2dArray("46 44,46 10,20 16,43 21")
        Assert.assertEquals(7, crabGraphs(50,4,g))
    }

    @Test
    fun sample1_3() {
        val g = stringTo2dArray("11 46")
        Assert.assertEquals(2, crabGraphs(50,2,g))
    }

    @Test
    fun sample1_5() {
        val g = stringTo2dArray("19 34,24 29,35 13,11 31,23 3")
        Assert.assertEquals(10, crabGraphs(50,39,g))
    }

    @Test
    fun sample1_6() {
        val g = stringTo2dArray("40 42,39 15")
        Assert.assertEquals(4, crabGraphs(50,2,g))
    }

    @Test
    fun sample1_7() {
        val g = stringTo2dArray("38 35,23 46,18 10,28 40,32 13,29 7,36 32,47 21,41 37," +
                "43 16,23 16,10 42,32 34,5 24,39 26,38 15,2 25,37 45,37 50," +
                "12 21,42 33,21 34,38 25,39 36,3 1,21 44,16 15,35 46,5 13,22 41," +
                "16 37,16 30,19 28,14 15,1 29,40 30,17 1,33 16,26 37,48 1,43 4," +
                "47 11,46 44,15 44,17 32,38 4,17 33,34 31,12 35,6 42,22 30," +
                "29 42,14 9,27 12,17 7,10 39,29 14,49 36,16 25")
        Assert.assertEquals(48, crabGraphs(50,44,g))
    }

    @Test
    fun sample1_8() {
        val g = stringTo2dArray("3 40,42 35,23 22,22 3,31 50,7 14,31 27,11 19,4 2,17 28,27 14,17 38,20 4,36 3,17 35,44 14,10 20,16 32,27 50,12 45,38 34,41 31,37 28,7 29,27 46,3 23,28 7,14 4,23 30,22 48,22 33,35 27,25 48,10 3,27 48,17 27,8 18,50 28,33 26,41 13,15 12,44 6,6 28,26 44,2 22,42 44,43 50,32 23,1 13,41 18,18 44,32 19,32 1,26 43,8 44,32 20,28 1,17 43,39 30,3 9,20 19,18 3,38 20,17 1,43 9,12 29,23 31,35 12,38 4,17 37,39 47,25 24,7 31,26 10,47 49,6 23,2 17,15 23,47 10,3 44,13 38,37 15,5 9,31 35,33 49,36 8,9 8,7 2,44 46,13 12,29 22,10 12,49 8,21 5,24 17,50 36,16 37,24 39,10 43,15 50,44 1,18 48,10 24,5 47,25 10,5 29,49 28,35 44,29 6,25 20,36 40,25 26,19 38,49 30")
        Assert.assertEquals(50, crabGraphs(50,3,g))
    }

    @Test
    fun sample3_1() {
        val g = stringTo2dArray("20 19,36 27,25 4,5 1,5 9,1 23,1 35")
        Assert.assertEquals(11, crabGraphs(50,2,g))
    }

    @Test
    fun sample7_2() {
        val g = stringTo2dArray("4 55,7 46,4 9,39 49,59 29,40 25,43 24,35 10,52 38,26 63,36 48,50 6,17 34,30 47,23 29,30 43,27 65,61 39,46 2,23 50,2 1,30 11,46 18,10 25,39 40,19 31,25 3,48 25,11 51,19 2,39 23,35 14,46 50")
        Assert.assertEquals(40, crabGraphs(65,2,g))
    }

    @Test
    fun sample7_3() {
        val g = stringTo2dArray("1 2,2 3,3 4")
        Assert.assertEquals(4, crabGraphs(4,2,g))
    }

    @Test
    fun sample10_3() {
        val g = stringTo2dArray("87 15,17 24,21 31,7 62,92 25,25 85,24 74,78 38,37 48,23 100,37 13,3 38,98 33,76 57,64 44,3 24,6 45,2 76,48 21,33 89,43 35,1 37,76 47,21 91,70 49,80 47,40 78,83 95,3 13,95 93,76 5,96 36,33 38,73 2,100 24,84 9,76 34,47 35,96 74,77 29,12 62,47 85,37 15,19 14,97 66,46 82,37 19,25 56,73 98,39 18,70 51,100 1,79 39,48 51,31 84,84 16,86 21,1 86,73 68,77 5,60 27,97 10,89 83,17 42,76 12,95 24,19 39,37 93,9 40,99 5,53 15,44 73,78 25,35 98,16 65,93 49,64 8,30 78,98 20,15 95,52 21,36 25,21 32,9 24")
        Assert.assertEquals(77, crabGraphs(100,2,g))
    }

    @Test
    fun fileSample1() {
        val res = fileRunner("/Users/tkma0vh/Downloads/input01.txt")
        val expected = stringToIntArray("7 50 50 4 50 10 50 50 2 48")
        Assert.assertArrayEquals(expected, res)
    }

    @Test
    fun fileSample3() {
        val res = fileRunner("/Users/tkma0vh/Downloads/input03.txt")
        val expected = stringToIntArray("11 50 49 19 29 32 49 44 50 10")
        Assert.assertArrayEquals(expected, res)
    }

    @Test
    fun fileSample7() {
        val res = fileRunner("/Users/tkma0vh/Downloads/input07.txt")
        val expected = stringToIntArray("71 40 52 35 19 41 24 14 21 77")
        Assert.assertArrayEquals(expected, res)
    }

    @Test
    fun fileSample8() {
        val res = fileRunner("/Users/tkma0vh/Downloads/input08.txt")
        val expected = stringToIntArray("4 93 7 79 44 37 84 89 57 50")
        Assert.assertArrayEquals(expected, res)
    }

    @Test
    fun fileSample10() {
        val res = fileRunner("/Users/tkma0vh/Downloads/input10.txt")
        val expected = stringToIntArray("20 44 77 100 100 55 99 72 59 83")
        Assert.assertArrayEquals(expected, res)
    }

    private fun fileRunner(path: String): Array<Int> {
        val resList = ArrayList<Int>()
        val lines = FileHelper.load(path)
        val c = lines[0].toInt()
        var i = 1
        for (cItr in 1..c) {
            val ntm = lines[i++].split(" ")
            val n = ntm[0].trim().toInt()
            val t = ntm[1].trim().toInt()
            val m = ntm[2].trim().toInt()

            val graph = Array(m) { Array(2) { 0 } }
            for (graphRowItr in 0 until m) {
                graph[graphRowItr] = lines[i++].split(" ").map{ it.trim().toInt() }.toTypedArray()
            }
//            val ss = arrayToString(graph)
            val res = crabGraphs(n, t, graph)
            resList.add(res)
        }
        return resList.toTypedArray()
    }

    //https://www.hackerrank.com/challenges/crab-graphs/problem
}