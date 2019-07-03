package rn.puzzle.game.medium.roadaandlibraries

class RoadsAndLibrariesClassSolver {
    fun roadsAndLibraries(n: Int, libCost: Int, roadCost: Int, graph: Array<Array<Int>>): Long {
        val nodes = buildNodes(n, graph)
        var libsCount = 0L
        var totalKidsCount = 0L
        for (node in nodes) {
            if(node.isSelected) {
                continue
            }
            libsCount++
            totalKidsCount += (node.infect() - 1)
        }
        var total = libsCount * libCost
        if(libCost > roadCost) {
            total += totalKidsCount * roadCost
        } else {
            total += totalKidsCount * libCost
        }
        return total
    }

    private fun buildNodes(n: Int, graph: Array<Array<Int>>): List<Node> {
        val nodes = generateSequence(0) { it + 1 }.take(n).map { Node() }.toList()
        for (points in graph) {
            val n0 = nodes[points[0] - 1]
            val n1 = nodes[points[1] - 1]
            n0.kids.add(n1)
            n1.kids.add(n0)
        }

        return nodes
    }

    private class Node(val kids : ArrayList<Node> = ArrayList(),
                       var isSelected: Boolean = false) {
        fun infect(): Int {
            isSelected = true
            var count = 1
            for (kid in kids) {
                if(kid.isSelected) {
                    continue
                }
                count += kid.infect()
            }
            return count
        }
    }
}