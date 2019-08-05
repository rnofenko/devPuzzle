package rn.puzzle.string.standard

class UkkonenSuffixTreeBuilder2 {
    fun build(s: String) {
        buildUkkonen(s)
    }

    private fun buildUkkonen(text: String): Node {
        val root = Node()
        val cursor = Cursor(root, text)

        for(i in text.indices) {
            val c = text[i]
            var prevInsertedNode = root
            while(cursor.next(c, i)) {
                val insertedNode = split(cursor, i)
                if(prevInsertedNode != root) {
                    prevInsertedNode.link = insertedNode
                }
                prevInsertedNode = insertedNode

                if(cursor.node == root) {
                    cursor.length--
                    if(cursor.length == 0) {
                        cursor.pointer = '-'
                    } else {
                        cursor.pointer = text[cursor.node.startIndex + cursor.length]
                    }
                } else {
//                    if(cursor.length > 0) {
//                        cursor.length--
//                        if(cursor.length == 0) {
//                            cursor.pointer = '-'
//                        } else {
//                            cursor.pointer = text[cursor.node.startIndex + cursor.length]
//                        }
//                    }
//                    if(cursor.length > 0 && cursor.getEdge().endIndex > -1) {
//                        val edge = cursor.getEdge()
//                        val edgeLen = edge.endIndex - edge.startIndex + 1
//                        if(edgeLen <= cursor.length) {
//                            cursor.node = edge
//                            cursor.length -= edgeLen
//                            if(cursor.length == 0) {
//                                cursor.pointer = '-'
//                                break
//                            }
//                        }
//                    } else {
                        cursor.node = cursor.node.link ?: root
//                    }
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
        private val none: Char = '-'
        var pointer: Char = none
        var length: Int = 0

        fun getEdge(): Node {
            return node.kids[pointer] ?: throw UnknownError()
        }

        fun next(c: Char, i: Int): Boolean {
            if(pointer == none) {
                if(node.kids.containsKey(c)) {
                    pointer = c
                    length = 1
                } else {
                    node.setKid(Node(i, text), text)
                }
            } else {
                val edge = getEdge()
                if(text[edge.startIndex + length] == c) {
                    length++
                } else {
                    return true
                }
            }
            if(length > 0) {
                val edge = getEdge()
                if (edge.endIndex > -1) {
                    val edgeLen = edge.endIndex - edge.startIndex + 1
                    if(edgeLen <= length) {
                        node = edge
                        length -= edgeLen
                        if(length == 0) {
                            pointer = none
                        }
                    }
                }
            }
            return false
        }

        override fun toString(): String {
            return "p=$node pointer=$pointer len=$length"
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
            var s = text.substring(startIndex, if(endIndex == -1) text.length else endIndex + 1)
            s += " idx=$startIndex kids=${kids.size}"
            if(link != null) {
                s += " [$link]"
            }
            return s
        }
    }

    //https://stackoverflow.com/questions/9452701/ukkonens-suffix-tree-algorithm-in-plain-english
    //http://brenden.github.io/ukkonen-animation/
}