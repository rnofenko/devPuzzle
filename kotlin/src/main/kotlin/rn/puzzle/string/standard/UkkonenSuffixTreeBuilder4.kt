package rn.puzzle.string.standard

class UkkonenSuffixTreeBuilder4 {

    private fun buildUkkonen(text: String): Node {
        val root = Node()
        val cursor = Cursor(root, text, root)
        for (i in text.indices) {
            val c = text[i]
            if(cursor.needToSplit(c)) {
                splitProcess(cursor, i, c)
            } else {
                if(cursor.length == 0) {
                    if(cursor.node.kids.containsKey(c)) {
                        cursor.char = c
                        cursor.length = 1
                        cursor.remainder++
                    } else {
                        navigateAfterSplit(cursor, i, c, false)
                    }
                } else {
                    cursor.length++
                    cursor.remainder++
                }
                cursor.selectNextNodeIfEdgeIsShort()
            }
        }
        return root
    }

    private fun splitProcess(cursor: Cursor, i: Int, c: Char) {
        var prevInsertedNode = cursor.root
        var isSuffix = false
        while (cursor.needToSplit(c)) {
            val insertedNode = split(cursor, i)
            if(!prevInsertedNode.isRoot) {
                prevInsertedNode.link = insertedNode
                prevInsertedNode.isSuffixLink = true
                isSuffix = true
            }
            prevInsertedNode = insertedNode

            cursor.remainder--
            navigateAfterSplit(cursor, i, c, isSuffix)
        }
    }

    private fun navigateAfterSplit(cursor: Cursor, i: Int, c: Char, isSuffix: Boolean) {
        val active = cursor.node
        if(cursor.length == 1) {
            if (active.kids.containsKey(c)) {
                cursor.selectNextNodeIfEdgeIsShort()
                return
            } else {
                val newActive = cursor.node.link ?: cursor.root
                if(cursor.node != newActive) {
                    cursor.node = newActive
                    return
                }

                active.setKid(Node(i, cursor.text), cursor.text)
                cursor.length--
            }
        } else if(cursor.length == 0) {
            if (!active.kids.containsKey(c)) {
                active.setKid(Node(i, cursor.text), cursor.text)
            }
        } else {
            cursor.length--
            cursor.char = cursor.text[cursor.getEdge().startIndex + 1]
            return
        }
        val newActive = cursor.node.link ?: cursor.root
        if(cursor.node != newActive) {
            cursor.node = newActive
            navigateAfterSplit(cursor, i, c, false)
        }
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

    class Cursor(var node: Node, val text: String, val root: Node) {
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

    fun buildAndPrint(s: String): List<String> {
        val root = buildUkkonen(s)

        val printLines = convertToPrintLines(root, s)
        println(s)
        printLines.forEach{ println(it) }
        return printLines
    }

    private fun convertToPrintLines(root: Node, s: String): List<String> {
        val lines = ArrayList<String>()
        printNode(root, s, "", lines)
        return lines
    }

    private fun printNode(root: Node, s: String, prefix: String, lines: ArrayList<String>) {
        if(!root.isRoot) {
            val end = if(root.endIndex == -1) s.length - 1 else root.endIndex
            val str = prefix + s.substring(root.startIndex, end + 1)
            lines.add(str)
        }

        for (kid in root.kids.values) {
            printNode(kid, s, "$prefix-", lines)
        }
    }

    //https://stackoverflow.com/questions/9452701/ukkonens-suffix-tree-algorithm-in-plain-english
    //http://brenden.github.io/ukkonen-animation/
}

