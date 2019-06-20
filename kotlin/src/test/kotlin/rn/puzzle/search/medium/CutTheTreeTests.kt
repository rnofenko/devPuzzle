package rn.puzzle.search.medium

import org.junit.Assert
import org.junit.Test

class CutTheTreeTests {
    @Test
    fun sample1() {
        val edges = arrayOf(arrayOf(1,2),arrayOf(2,3),arrayOf(2,5),arrayOf(4,5),arrayOf(5,6))
        val res = cutTheTree(arrayOf(100,200,100,500,100,600), edges)
        Assert.assertEquals(400, res)
    }

    @Test
    fun sample2() {
        val edges = arrayOf(arrayOf(2,8),arrayOf(10,5),arrayOf(1,7),arrayOf(6,9),arrayOf(4,3),arrayOf(8,10),arrayOf(5,1),arrayOf(7,6),arrayOf(9,4))
        val res = cutTheTree(arrayOf(205,573,985,242,830,514,592,263,142,915), edges)
        Assert.assertEquals(99, res)
    }

    private fun cutTheTree(data: Array<Int>, edges: Array<Array<Int>>): Int {
        val root = buildTree(data, edges)
        root.calcSum()
        return root.getMinDiff()
    }

    private fun buildTree(data: Array<Int>, edges: Array<Array<Int>>): Node {
        val nodes = HashMap<Int, Node>()
        for (edge in edges) {
            val v0 = edge[0]
            val v1 = edge[1]
            val n0 = nodes.getOrPut(v0) { Node(data[v0 - 1]) }
            val n1 = nodes.getOrPut(v1) { Node(data[v1 - 1]) }
            n0.kids.add(n1)
            n1.kids.add(n0)
        }

        val root = nodes[1]!!
        root.sanitize()
        return root
    }

    class Node(val value: Int) {
        val kids = ArrayList<Node>()
        var sum: Int = 0

        fun calcSum(): Int {
            sum = 0
            for (kid in kids) {
                sum += kid.calcSum()
            }
            sum += value

            return sum
        }

        fun getMinDiff(): Int {
            return kids.map { it.getMinDiff(sum) }.min() ?: Int.MAX_VALUE
        }

        private fun getMinDiff(total: Int): Int {
            val min = kids.map { it.getMinDiff(total) }.min() ?: Int.MAX_VALUE
            return Math.min(min, Math.abs(total - sum * 2))
        }

        fun sanitize() {
            for (kid in kids) {
                kid.kids.remove(this)
            }
            for (kid in kids) {
                kid.sanitize()
            }
        }
    }
}