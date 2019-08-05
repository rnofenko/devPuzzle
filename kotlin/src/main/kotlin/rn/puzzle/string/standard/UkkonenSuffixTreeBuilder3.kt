package rn.puzzle.string.standard

class UkkonenSuffixTreeBuilder3 {
    private fun buildUkkonen(text: String): Node {
        val root = Node()
        val cursor = Cursor(root, text)

        for(i in text.indices) {
            val c = text[i]
            cursor.remainder++

            var prevInsertedNode = root
            var wasSplitted = false
            var wasSuffixTransfer = false
            while (cursor.remainder > 0) {
                var wasSelectedNext = false
                if(cursor.length == 0) {
                    if(cursor.node.kids.containsKey(c)) {
                        cursor.char = c
                        cursor.length = 1
                        wasSelectedNext = true
                    } else {
                        cursor.node.setKid(Node(i, text), text)
                        cursor.remainder--
                        wasSplitted = true
                    }
                } else if(cursor.needToSplit(c)) {
                    val insertedNode = split(cursor, i)
                    if(!prevInsertedNode.isRoot) {
                        prevInsertedNode.link = insertedNode
                        prevInsertedNode.isSuffixLink = true
                    }
                    prevInsertedNode = insertedNode
                    cursor.remainder--

                    if(cursor.node.isSuffixLink || wasSuffixTransfer) {
                        cursor.node = cursor.node.link ?: root
                        wasSuffixTransfer = true
                        if(cursor.remainder == 1) {
                            cursor.length--
                        }
                    } else {
                        cursor.length--
                        if (cursor.length > 0) {
                            cursor.char = cursor.text[cursor.node.startIndex + cursor.length - 1]
                        }
                    }
                    wasSplitted = true
                    continue
                } else {
                    cursor.length++
                    wasSelectedNext = true
                }

                wasSelectedNext = cursor.selectNextNodeIfEdgeIsShort() || wasSelectedNext

                if(cursor.remainder == 0) {
                    if(wasSplitted || wasSelectedNext) {
                        break
                    }
                } else {
                    if(wasSelectedNext) {
                        break
                    }
                    cursor.node = cursor.node.link ?: root
                }
            }
        }

        return root
    }

    private fun split(cursor: Cursor, index: Int): Node {
        val parent = cursor.node
        val updatedEdge = cursor.getEdge()
        val insertedEdge = Node(updatedEdge.startIndex, updatedEdge.startIndex + cursor.length - 1, cursor.text)
        updatedEdge.startIndex = insertedEdge.endIndex + 1

        insertedEdge.link = cursor.node
        parent.setKid(insertedEdge, cursor.text)

        val kidNewChar = Node(index, cursor.text)

        insertedEdge.setKid(updatedEdge, cursor.text)
        insertedEdge.setKid(kidNewChar, cursor.text)

        return insertedEdge
    }

    class Cursor(var node: Node, val text: String) {
        var char: Char = '-'
        var length: Int = 0
        var remainder: Int = 0

        fun getEdge(): Node {
            return node.kids[char] ?: throw UnknownError()
        }

        fun selectNextNodeIfEdgeIsShort(): Boolean {
            if(length > 0) {
                val edge = getEdge()
                if (edge.endIndex > -1) {
                    val edgeLen = edge.endIndex - edge.startIndex + 1
                    if(edgeLen <= length) {
                        node = edge
                        length -= edgeLen
                        return true
                    }
                }
            }
            return false
        }

        fun needToSplit(c: Char): Boolean {
            if(length == 0) {
                return false
            }
            val edge = getEdge()
            return text[edge.startIndex + length] != c
        }

        override fun toString(): String {
            val edge = if(length == 0) "" else "edge=$char"
            return "node={$node} len=$length $edge rem=$remainder"
        }
    }

    class Node(var startIndex: Int, var endIndex: Int, private val text: String) {
        constructor() : this(0, -1, "")
        constructor(startIndex: Int, text: String) : this(startIndex, -1, text)

        val kids = HashMap<Char, Node>()
        var link: Node? = null
        var isSuffixLink = false

        val isRoot: Boolean
            get() = text.isEmpty()

        fun setKid(kid: Node, text: String) {
            kids[text[kid.startIndex]] = kid
        }

        override fun toString(): String {
            if(isRoot) {
                return "root kids=${kids.size}"
            }
            var s = text.substring(startIndex, if(endIndex == -1) text.length else endIndex + 1)
            s += " idx=$startIndex kids=${kids.size}"
            if(link != null) {
                s += " [$link]"
            }
            return s
        }
    }

    fun build(s: String) {
        buildUkkonen(s)
    }

    fun buildAndPrint(s: String) {
        val root = buildUkkonen(s)
        printTree(root, s)
    }

    private fun printTree(root: Node, s: String) {
        println()
        println(s)
        printNode(root, s, "")
    }

    private fun printNode(root: Node, s: String, prefix: String) {
        if(root.isRoot) {
            println("root")
        } else {
            val end = if(root.endIndex == -1) s.length - 1 else root.endIndex
            println(prefix + s.substring(root.startIndex, end + 1))
        }

        for (kid in root.kids.values) {
            printNode(kid, s, "$prefix--")
        }
    }

    //https://stackoverflow.com/questions/9452701/ukkonens-suffix-tree-algorithm-in-plain-english
    //http://brenden.github.io/ukkonen-animation/
}