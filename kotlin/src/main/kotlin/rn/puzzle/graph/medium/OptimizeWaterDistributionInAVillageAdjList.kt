package rn.puzzle.graph.medium

import java.lang.Exception
import java.util.*
import kotlin.collections.HashSet

class OptimizeWaterDistributionInAVillageAdjList {
    fun minCostToSupplyWater(wells: IntArray, initialPipes: Array<IntArray>): Int {
        val tree = Tree(0)
        for (i in wells.indices) {
            tree.addNeighbor(intArrayOf(0, i + 1, wells[i]))
        }

        val pipes = AdjacencyList(wells.size, initialPipes)
        var nextTown = tree.selectPipe()
        while (nextTown != -1 && pipes.isNotEmpty()) {
            val newNeighbors = pipes.poll(nextTown)
            for (pipe in newNeighbors) {
                tree.addNeighbor(pipe)
            }
            nextTown = tree.selectPipe()
        }

        tree.selectRest()
        return tree.sum
    }

    class AdjacencyList(size: Int, initialEdges: Array<IntArray>) {
        private var edgesCount = 0
        private val vertices: Array<HashMap<Int, IntArray>> = Array(size + 1) { HashMap<Int, IntArray>() }

        init {
            for (edge in initialEdges) {
                val v1 = edge[0]
                val v2 = edge[1]
                if(vertices[v1].containsKey(v2)) {
                    val oldEdge = vertices[v1][v2] ?: throw Exception()
                    oldEdge[2] = Math.min(oldEdge[2], edge[2])
                } else {
                    vertices[v1][v2] = edge
                    vertices[v2][v1] = edge
                    edgesCount++
                }
            }
        }

        fun isNotEmpty(): Boolean {
            return edgesCount != 0
        }

        fun poll(vertex: Int): List<IntArray> {
            val edges = vertices[vertex].values.toList()
            edgesCount -= edges.size

            for (edge in edges) {
                val secondVertex = if(edge[0] == vertex) edge[1] else edge[0]
                vertices[secondVertex].remove(vertex)
            }
            vertices[vertex].clear()

            return edges
        }
    }

    class Tree(initialTown: Int) {
        private val costToPipes = TreeMap<Int, ArrayList<IntArray>>()
        private val vertices = HashSet<Int>()
        var sum: Int = 0

        init {
            vertices.add(initialTown)
        }

        fun addNeighbor(pipe: IntArray) {
            val cost = pipe[2]
            val list = costToPipes.getOrPut(cost) { ArrayList() }
            list.add(pipe)
        }

        fun selectPipe(): Int {
            while(costToPipes.isNotEmpty()) {
                val list = costToPipes.firstEntry().value
                val pipe = list[0]
                val v0 = pipe[0]
                val v1 = pipe[1]
                val cost = pipe[2]
                list.removeAt(0)
                if (list.isEmpty()) {
                    costToPipes.remove(cost)
                }
                if(!vertices.contains(v0) || !vertices.contains(v1)) {
                    val v = if(vertices.contains(v0)) v1 else v0
                    sum += cost
                    vertices.add(v)
                    return v
                }
            }
            return -1
        }

        fun selectRest() {
            while (costToPipes.isNotEmpty()) {
                selectPipe()
            }
        }
    }
}