package rn.standard.string

import java.util.*
import kotlin.collections.ArrayList

private const val CHARS_COUNT = 26
private const val ROOT_CHAR = -1

class AhoCorasickFinder {
    fun buildTrie(patterns: List<String>): Node {
        val root = Node()
        for (pattern in patterns) {
            addPattern(root, pattern)
        }
        buildLinks(root)
        return root
    }

    fun find(root: Node, text: String): List<String> {
        val result = ArrayList<String>()
        var node = root
        for (i in text.indices) {
            val c = text[i] - 'a'
            while (true) {
                val child = node.subKids[c]
                if(child != null) {
                    node = child

                    var finishedWordNode = node
                    while (finishedWordNode != root) {
                        if (finishedWordNode.isEndOfWord) {
                            result.add(text.substring(i + 1 - finishedWordNode.length, i + 1))
                        }
                        finishedWordNode = finishedWordNode.suffixLink ?: root
                    }

                    break
                }

                if(node.suffixLink == null) {
                    node = root
                    break
                } else {
                    node = node.suffixLink ?: node
                }
            }
        }
        return result
    }

    private fun buildLinks(root: Node) {
        val queue = LinkedList<Node>()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val node = queue.poll()
            for (child in node.kids) {
                queue.add(child)
            }
            buildLinks(root, node)
        }
    }

    private fun buildLinks(root: Node, node: Node) {
        if(node == root) {
            return
        }
        if(node.parent == root) {
            node.updateSuffixLink(root)
        } else {
            val potentialParent = node.parent.suffixLink ?: root
            val linkNode = potentialParent.subKids[node.char] ?: root
            node.updateSuffixLink(linkNode)
        }
    }

    private fun addPattern(root: Node, pattern: String) {
        var node = root
        for (c in pattern) {
            val char = c - 'a'
            var child = node.subKids[char]
            if(child != null && child.parent == node) {
                node = child
            } else {
                child = Node(char, node)
                node.subKids[char] = child
                node.kids.add(child)

                node = child
            }
        }

        node.isEndOfWord = true
    }

    class Node {
        val char: Int
        val length: Int
        val parent: Node
        var suffixLink: Node? = null
        val subKids: Array<Node?> = arrayOfNulls(CHARS_COUNT)
        val kids = ArrayList<Node>()
        var isEndOfWord = false

        constructor() {
            this.char = ROOT_CHAR
            this.parent = this
            this.length = 0
        }

        constructor(c: Int, parent: Node) {
            this.char = c
            this.parent = parent
            this.length = parent.length + 1
        }

        fun updateSuffixLink(suffixLink: Node) {
            this.suffixLink = suffixLink
            for (i in 0 until CHARS_COUNT) {
                subKids[i] = subKids[i] ?: suffixLink.subKids[i]
            }
        }

        override fun toString(): String {
            if(char == -1) {
                return "-"
            }
            val c = (char + 'a'.toInt()).toChar()
            return "$parent$c"
        }
    }
}