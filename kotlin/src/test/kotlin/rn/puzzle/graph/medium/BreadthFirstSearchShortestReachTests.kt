package rn.puzzle.graph.medium

import org.junit.Assert.assertArrayEquals
import org.junit.Test
import rn.puzzle.FileHelper
import rn.puzzle.Stopwatch

class BreadthFirstSearchShortestReachTests {
    @Test
    fun sample1() {
        val res = bfs(4, arrayOf(arrayOf(1,2),arrayOf(1,3)),1)
        assertArrayEquals(arrayOf(6,6,-1), res)
    }

    @Test
    fun sample2() {
        val res = bfs(3, arrayOf(arrayOf(2,3)),2)
        assertArrayEquals(arrayOf(-1,6), res)
    }

    @Test
    fun sample3() {
        val res = bfs(5, arrayOf(arrayOf(1,2),arrayOf(1,3),arrayOf(3,1),arrayOf(4,1),arrayOf(4,3),arrayOf(2,3),arrayOf(4,5),arrayOf(3,5)),2)
        assertArrayEquals(arrayOf(6,6,12,12), res)
    }

    @Test
    fun file5() {
        val lines = FileHelper.load("/Users/tkma0vh/Downloads/input05.txt")
        var lineNo = 0
        val q = lines[lineNo++].toInt()
        for (qItr in 1..q) {
            val nm = lines[lineNo++].split(" ")

            val n = nm[0].trim().toInt()

            val m = nm[1].trim().toInt()

            val edges = Array(m) { Array(2) { 0 } }

            for (i in 0 until m) {
                edges[i] = lines[lineNo++].split(" ").map{ it.trim().toInt() }.toTypedArray()
            }

            val s = lines[lineNo++].trim().toInt()

            if(qItr == 3) {
                val w = Stopwatch.start()
                val result = bfs(n, edges, s)
                w.show(result.size.toString() + " total")
            }
        }
    }

    private fun bfs(n: Int, edges: Array<Array<Int>>, s: Int): Array<Int> {
        val node = build(edges, s)
        node.price = 1
        val prices = Array(n) { 0 }
        node.infect(prices)

        val list = prices.toMutableList()
        list.removeAt(s - 1)
        return list.map { it - 1 }.toTypedArray()
    }

    private fun build(edges: Array<Array<Int>>, s: Int): Node {
        val nodes = HashMap<Int, Node>()
        var startNode: Node? = null
        for (edge in edges) {
            val v0 = edge[0]
            val v1 = edge[1]
            val n0 = nodes.getOrPut(v0) { Node(v0) }
            val n1 = nodes.getOrPut(v1) { Node(v1) }
            n0.kids.add(n1)
            n1.kids.add(n0)
            if(v0 == s) {
                startNode = n0
            } else if(v1 == s){
                startNode = n1
            }
        }

        return startNode ?: Node(s)
    }

    class Node(private val number: Int) {
        var price = 0
        val kids = ArrayList<Node>()

        fun infect(prices: Array<Int>) {
            val newPrice = price + 6
            for (kid in kids) {
                if(kid.price == 0 || kid.price > newPrice) {
                    kid.price = newPrice
                    prices[kid.number - 1] = newPrice
                    kid.infect(prices)
                }
            }
        }

        override fun toString(): String {
            return "$number $price"
        }
    }
}