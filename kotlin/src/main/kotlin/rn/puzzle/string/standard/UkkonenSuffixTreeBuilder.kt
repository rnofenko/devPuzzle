package rn.puzzle.string.standard

class UkkonenSuffixTreeBuilder {
    fun build(s: String) {
        buildUkkonen(s)
    }

    private fun buildUkkonen(s: String): Node {
        val root = Node()
        val activePoint = ActivePoint(root, 'a',0, s)

        for(i in s.indices) {
            val c = s[i]
            var prevEdge = root
            while(activePoint.needToSplit(i)) {
                split(activePoint, i)

                val edge = activePoint.getEdge()
                if(prevEdge != root) {
                    prevEdge.link = edge
                }
                prevEdge = edge

                if(activePoint.length > 1) {
                    activePoint.edge = s[edge.startIndex + 1]
                    activePoint.length--
                } else {
                    val link = activePoint.node.link
                    if(link == null) {
                        activePoint.length = 0
                        continue
                    }
                    activePoint.node = link
                }
            }
            activePoint.next(c, i)
        }

        return root
    }

    private fun split(active: ActivePoint, index: Int) {
        val parent = active.node
        val edge = active.getEdge()
        val newEdge = Node(edge.startIndex, edge.startIndex + active.length - 1, active.text)
        parent.setKid(newEdge, active.text)

        edge.startIndex = newEdge.endIndex + 1
        val kidNewChar = Node(index, active.text)

        newEdge.setKid(edge, active.text)
        newEdge.setKid(kidNewChar, active.text)
    }

    class ActivePoint(var node: Node, var edge: Char, var length: Int, val text: String) {
        fun needToSplit(index: Int): Boolean {
            if(length == 0) {
                return false
            }
            val c = text[index]
            val edge = getEdge()
            val pos = edge.startIndex + length
            return text[pos] != c
        }

        fun getEdge(): Node {
            return node.kids[edge] ?: throw UnknownError()
        }

        fun next(c: Char, i: Int) {
            if(length == 0) {
                setKid(c, i)
            } else {
                incLength()
            }
        }

        private fun setKid(c: Char, i: Int) {
            if(node.kids.containsKey(c)) {
                edge = c
                length = 0
                incLength()
            } else {
                node.setKid(Node(i, text), text)
            }
        }

        private fun incLength() {
            length++
            val edge = getEdge()
            if(edge.endIndex > -1 && edge.endIndex - edge.startIndex + 1 == length) {
                node = edge
                length = 0
            }
        }

        override fun toString(): String {
            return "p=$node edge=$edge len=$length"
        }
    }

    class Node(var startIndex: Int, var endIndex: Int, private val text: String) {
        constructor() : this(0, -1, "")
        constructor(startIndex: Int, text: String) : this(startIndex, -1, text)

        val kids = HashMap<Char, Node>()
        var link: Node? = null

        fun setKid(kid: Node, text: String) {
            kids[text[kid.startIndex]] = kid
        }

        override fun toString(): String {
            if(text.isEmpty()) {
                return "root kids=${kids.size}"
            }
            val s = text.substring(startIndex, if(endIndex == -1) text.length else endIndex + 1)
            return "$s idx=$startIndex kids=${kids.size}"
        }
    }

    //https://stackoverflow.com/questions/9452701/ukkonens-suffix-tree-algorithm-in-plain-english
    //http://brenden.github.io/ukkonen-animation/
}