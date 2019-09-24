package rn.puzzle.search.hard

import java.util.*
import kotlin.collections.HashMap

class CrackingTheSafeDeBruijnSolver {
    fun solve(n: Int, k: Int): String {
        if(k == 1) {
            return "0".repeat(n)
        }
        if(n == 1) {
            var res = "0"
            for (i in 1 until k) {
                res += i
            }
            return res
        }

        val start = buildGraph(n, k)
        val solution = ArrayList<String>()

        goDeep(start, solution)
        solution.reverse()

        var result = solution.joinToString("")
        val zeroPrefix = "0".repeat(n)
        while (!result.startsWith(zeroPrefix)) {
            result = "0$result"
        }
        return result
    }

    private fun goDeep(node: Node, solution: ArrayList<String>) {
        while (node.out.isNotEmpty()) {
            val nextNode = node.out.pollFirst()!!
            goDeep(nextNode, solution)
        }
        val s = node.name
        solution.add(s.substring(s.length - 1))
    }

    private fun buildGraph(n: Int, k: Int): Node {
        val nodes = HashMap<String, Node>()
        var i = 0
        while (true) {
            val pin = i.toString(k).padStart(n, '0')
            if(pin.length > n) {
                break
            }

            val from = pin.substring(0, n - 1)
            val to = pin.substring(1)

            val fromNode = nodes.getOrPut(from) { Node(from) }
            val toNode = nodes.getOrPut(to) { Node(to) }
            toNode.inDegree++

            fromNode.out.add(toNode)

            i++
        }

        return nodes["0".repeat(n - 1)]!!
    }

    private class Node(val name: String, var inDegree: Int = 0) {
        val out = LinkedList<Node>()

        override fun toString(): String {
            return "$name out=${out.size}"
        }
    }
}