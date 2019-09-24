package rn.puzzle.graph.medium

import java.util.*
import kotlin.collections.HashSet

class OptimizeWaterDistributionInAVillageLoop {
    fun minCostToSupplyWater(wells: IntArray, initialPipes: Array<IntArray>): Int {
        val tree = Tree(0)
        var minI = 0
        for (i in wells.indices) {
            val cost = wells[i]
            if(wells[minI] > cost) {
                minI = i
            }
            tree.addNeighbor(intArrayOf(0, i + 1, cost))
        }

        var pipes = initialPipes.toList()
        var nextTown = tree.selectPipe()
        while (nextTown != -1 && pipes.isNotEmpty()) {
            val notConnectedPipes = ArrayList<IntArray>()
            for (pipe in pipes) {
                if(pipe[0] == nextTown || pipe[1] == nextTown) {
                    tree.addNeighbor(pipe)
                } else {
                    notConnectedPipes.add(pipe)
                }
            }

            pipes = notConnectedPipes
            nextTown = tree.selectPipe()
        }

        tree.selectRest()

        return tree.sum
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