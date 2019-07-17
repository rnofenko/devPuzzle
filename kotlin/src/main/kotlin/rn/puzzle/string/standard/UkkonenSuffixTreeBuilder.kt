package rn.puzzle.string.standard

class UkkonenSuffixTreeBuilder {
    fun build(s: String) {
        buildImpl(s)
    }

    private fun buildImpl(s: String): Node {
        val root = Node()
        val activePoint = ActivePoint(root, 'a',0, s, root)

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
            if(activePoint.length == 0) {
                activePoint.setKid(c, i)
            } else {
                activePoint.incLength()
            }
        }

        return root
    }

    private fun split(active: ActivePoint, index: Int) {
        val text = active.text
        val edge = active.getEdge()
        edge.endIndex = edge.startIndex + active.length - 1

        val restKid = Node(edge.endIndex + 1, text, active.root)
        val oneCharKid = Node(index, text, active.root)
        edge.kids[restKid.startChar] = restKid
        edge.kids[oneCharKid.startChar] = oneCharKid
    }

    class ActivePoint(var node: Node, var edge: Char, var length: Int, val text: String, val root: Node, var remainder: Int = 1) {
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

        fun incLength() {
            length++
            val edge = getEdge()
            if(edge.endIndex == -1) {
                return
            }
            if(edge.endIndex - edge.startIndex + 1 == length) {
                node = edge
                length = 0
            }
        }

        fun setKid(c: Char, i: Int) {
            if(node.kids.containsKey(c)) {
                edge = c
                length = 1
                remainder++
            } else {
                val kid = Node(i, text, root)
                node.kids[c] = kid
                remainder++
            }
        }

        override fun toString(): String {
            return "p=$node edge=$edge len=$length remainde=$remainder"
        }
    }

    class Node(val startIndex: Int, private val text: String, var link: Node?) {
        constructor() : this(0, "", null)

        val kids = HashMap<Char, Node>()
        var endIndex: Int = -1

        val startChar: Char
            get() = text[startIndex]

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