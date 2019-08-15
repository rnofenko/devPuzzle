package rn.puzzle.graph.medium

import junit.framework.Assert.assertEquals
import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList

class PrimSpecialSubtreeTests {
    @Test
    fun sample1() {
        val res = prims(5, arrayOf(arrayOf(1,2,3),arrayOf(1,3,4),arrayOf(4,2,6),arrayOf(5,2,2),arrayOf(2,3,5),arrayOf(3,5,7)),1)
        assertEquals(15, res)
    }

    private fun prims(n: Int, initialEdges: Array<Array<Int>>, start: Int): Int {
        var edges = initialEdges.toList()
        var nextVertex = start
        val tree = Tree(nextVertex)
        while (nextVertex != -1 && edges.isNotEmpty()) {
            val notConnectedEdges = ArrayList<Array<Int>>()
            for (edge in edges) {
                if(edge[0] == nextVertex || edge[1] == nextVertex) {
                    tree.add(edge)
                } else {
                    notConnectedEdges.add(edge)
                }
            }

            edges = notConnectedEdges
            nextVertex = tree.selectEdge()
        }

        tree.selectRest()

        return tree.sum
    }

    class Tree(vertex: Int) {
        private val tempEdges = TreeMap<Int, ArrayList<Array<Int>>>()
        private val vertices = HashSet<Int>()
        var sum: Int = 0

        init {
            vertices.add(vertex)
        }

        fun add(edge: Array<Int>) {
            val w = edge[2]
            val list = tempEdges.getOrPut(w) { ArrayList() }
            list.add(edge)
        }

        fun selectEdge(): Int {
            if(tempEdges.isEmpty()) {
                return -1
            }

            val list = tempEdges.firstEntry().value
            val edge = list[0]
            val v0 = edge[0]
            val v1 = edge[1]
            val w = edge[2]
            list.removeAt(0)
            if(list.isEmpty()) {
                tempEdges.remove(w)
            }

            if(vertices.contains(v0) && vertices.contains(v1)) {
                return selectEdge()
            }

            sum += w
            val v = if(vertices.contains(v0)) v1 else v0
            vertices.add(v)

            return v
        }

        fun selectRest() {
            while (tempEdges.isNotEmpty()) {
                selectEdge()
            }
        }
    }
}