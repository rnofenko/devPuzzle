package rn.puzzle.graph.medium

import org.junit.Assert
import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class ReconstructItineraryTests {

    @Test
    fun test1() {
        val tickets = listOf(listOf("MUC", "LHR"), listOf("JFK", "MUC"), listOf("SFO", "SJC"), listOf("LHR", "SFO"))
        val expected = listOf("JFK", "MUC", "LHR", "SFO", "SJC")
        solve(tickets, expected)
    }

    @Test
    fun test2() {
        val tickets = listOf(listOf("JFK","SFO"), listOf("JFK","ATL"), listOf("SFO","ATL"), listOf("ATL","JFK"), listOf("ATL","SFO"))
        val expected = listOf("JFK","ATL","JFK","SFO","ATL","SFO")
        solve(tickets, expected)
    }

    @Test
    fun test3() {
        val tickets = listOf(listOf("1","2"), listOf("1","3"),
                listOf("2","2"), listOf("2","4"), listOf("2","4"),
                listOf("3","1"), listOf("3","2"), listOf("3","5"),
                listOf("4","6"), listOf("4","3"),
                listOf("5","6"),
                listOf("6","3"))
        val expected = listOf("1","2","2","4","3","1","3","2","4","6","3","5","6")
        solve(tickets, expected)
    }

    private fun solve(tickets: List<List<String>>, expected: List<String>) {
        val result = findItinerary(tickets)
        Assert.assertArrayEquals(expected.toTypedArray(), result.toTypedArray())
    }

    private fun findItinerary(tickets: List<List<String>>): List<String> {
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

        for (node in nodes.values) {
            node.out.sortBy { it.name }
        }

        return nodes["JFK"] ?: getStartingNode(nodes.values)
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