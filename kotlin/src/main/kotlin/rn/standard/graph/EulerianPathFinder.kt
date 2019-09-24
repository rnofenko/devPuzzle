package rn.standard.graph

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class EulerianPathFinder {
    fun find(tickets: List<List<String>>): List<String> {
        val start = buildNodes(tickets)
        val solution = ArrayList<String>()
        goDeep(start, solution)

        return solution.reversed()
    }

    private fun goDeep(node: Node, solution: ArrayList<String>) {
        while (node.out.isNotEmpty()) {
            val nextNode = node.out.pollFirst()!!
            goDeep(nextNode, solution)
        }
        solution.add(node.name)
    }

    private fun buildNodes(tickets: List<List<String>>): Node {
        val nodes = HashMap<String, Node>()
        for (ticket in tickets) {
            val from = ticket[0]
            val to = ticket[1]
            val fromNode = nodes.getOrPut(from) { Node(from) }
            val toNode = nodes.getOrPut(to) { Node(to) }
            toNode.inDegree++

            fromNode.out.add(toNode)
        }

        return getStartingNode(nodes.values)
    }

    private fun getStartingNode(nodes: Collection<Node>): Node {
        var secondBest = nodes.first()
        for (node in nodes) {
            if(node.out.size > node.inDegree) {
                return node
            }
            if(node.out.isNotEmpty()) {
                secondBest = node
            }
        }
        return secondBest
    }

    private class Node(val name: String, var inDegree: Int = 0) {
        val out = LinkedList<Node>()

        override fun toString(): String {
            return "$name out=${out.size}"
        }
    }
}