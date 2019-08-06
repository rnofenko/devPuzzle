package rn.puzzle.string.hard

class StringFunctionCalculationSolver {
    fun solve(s: String): Int {
        return solveUsingSuffixTree(s)
    }

    private fun solveUsingSuffixTree(s: String): Int {
        val root = buildUkkonen("$s$")
        root.calcLeafs()
        root.calcPoints()
        return root.findMax()
    }

    private fun buildUkkonen(text: String): Node {
        val root = Node()
        val cursor = Cursor(root, text, root)
        for (i in text.indices) {
            handleCharacter(cursor, i)
        }
        return root
    }

    private fun handleCharacter(cursor: Cursor, i: Int) {
        val c = cursor.text[i]
        cursor.remainder++
        while (true) {
            if(cursor.length == 0) {
                if(cursor.node.kids.containsKey(c)) {
                    cursor.char = c
                    cursor.length = 1
                    cursor.selectNextNodeIfEdgeIsShort()
                    return
                } else {
                    cursor.node.setKid(Node(i, cursor.text), cursor.text)
                    cursor.remainder--
                    if(cursor.remainder == 0) {
                        return
                    }

                    cursor.navigateFromRoot(i)
                }
            } else {
                if(cursor.needToSplit(c)) {
                    split(cursor, i)
                    cursor.remainder--
                    cursor.navigateFromRoot(i)
                } else {
                    cursor.length++
                    cursor.selectNextNodeIfEdgeIsShort()
                    return
                }
            }
        }
    }

    private fun split(cursor: Cursor, index: Int) {
        val parent = cursor.node
        val updatedEdge = cursor.getEdge()
        val insertedEdge = Node(updatedEdge.startIndex, updatedEdge.startIndex + cursor.length - 1, cursor.text)
        updatedEdge.startIndex = insertedEdge.endIndex + 1

        parent.setKid(insertedEdge, cursor.text)

        val kidNewChar = Node(index, cursor.text)

        insertedEdge.setKid(updatedEdge, cursor.text)
        insertedEdge.setKid(kidNewChar, cursor.text)
    }

    class Cursor(var node: Node, val text: String, val root: Node) {
        var char: Char = '-'
        var length: Int = 0
        var remainder: Int = 0

        fun getEdge(): Node {
            return node.kids[char] ?: throw UnknownError()
        }

        fun selectNextNodeIfEdgeIsShort() {
            if(length == 0) {
                return
            }

            val edge = getEdge()
            if (edge.endIndex > -1) {
                val edgeLen = edge.endIndex - edge.startIndex + 1
                if(edgeLen <= length) {
                    node = edge
                    length -= edgeLen
                }
            }
        }

        fun navigateFromRoot(currentPos: Int) {
            node = root
            length = 0
            var r = remainder - 1
            while(r > 0) {
                if(length == 0) {
                    char = text[currentPos - r]
                    length = 1
                    r--
                } else {
                    val edge = getEdge()
                    if(edge.endIndex == -1) {
                        length += r
                        return
                    } else {
                        val edgeLen = edge.endIndex - edge.startIndex - length + 1
                        val changeLen = Math.min(edgeLen, r)

                        length += changeLen
                        r -= changeLen
                    }
                }
                selectNextNodeIfEdgeIsShort()
            }
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
        private var leafsCount: Int = 0
        private var points: Int = 0

        private val isRoot: Boolean
            get() = text.isEmpty()

        fun setKid(kid: Node, text: String) {
            kids[text[kid.startIndex]] = kid
        }

        fun calcLeafs() {
            if(endIndex == -1) {
                leafsCount = 1
            }
            for (kid in kids.values) {
                kid.calcLeafs()
                leafsCount += kid.leafsCount
            }
        }

        fun calcPoints() {
            for (kid in kids.values) {
                kid.calcPoints(0)
            }
        }

        private fun calcPoints(initialLen: Int) {
            if(endIndex == -1) {
                endIndex = text.length - 2
            }
            val len = initialLen + endIndex - startIndex + 1
            points = len * leafsCount
            for (kid in kids.values) {
                kid.calcPoints(len)
            }
        }

        fun findMax(): Int {
            var max = points
            for (kid in kids.values) {
                max = Math.max(max, kid.findMax())
            }
            return max
        }

        override fun toString(): String {
            if(isRoot) {
                return "root kids=${kids.size}"
            }
            var s = text.substring(startIndex, if(endIndex == -1) text.length else endIndex + 1)
            s += " idx=$startIndex kids=${kids.size}"
            return s
        }
    }
}