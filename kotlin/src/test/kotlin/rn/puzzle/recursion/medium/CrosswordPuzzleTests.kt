package rn.puzzle.recursion.medium

import org.junit.Assert
import org.junit.Test

class CrosswordPuzzleTests {
    @Test
    fun sample1() {
        val grid = arrayOf(
                "+-++++++++",
                "+-++++++++",
                "+-++++++++",
                "+-----++++",
                "+-+++-++++",
                "+-+++-++++",
                "+++++-++++",
                "++------++",
                "+++++-++++",
                "+++++-++++")
        val res = crosswordPuzzle(grid, "LONDON;DELHI;ICELAND;ANKARA")
        val expected = arrayOf(
                "+L++++++++",
                "+O++++++++",
                "+N++++++++",
                "+DELHI++++",
                "+O+++C++++",
                "+N+++E++++",
                "+++++L++++",
                "++ANKARA++",
                "+++++N++++",
                "+++++D++++")
        Assert.assertArrayEquals(expected, res)
    }

    private fun crosswordPuzzle(crossword: Array<String>, wordsLine: String): Array<String> {
        val grid = crossword.map { it.map { c -> c }.toTypedArray() }.toTypedArray()
        val spaces = findSpaces(grid)
        var prevSpace = spaces[0]
        prevSpace.calcLength(grid)
        for (i in 1 until spaces.size) {
            val current = spaces[i]
            current.prev = prevSpace
            prevSpace.next = current
            current.calcLength(grid)

            prevSpace = current
        }

        findCrossing(spaces[0])
        solve(spaces[0], wordsLine.split(';'))
        spaces[0].putWord(grid)

        return grid.map { it.joinToString("") }.toTypedArray()
    }

    private fun solve(s: Space?, words: List<String>): Boolean {
        if(s == null) {
            return true
        }

        for (word in words) {
            if(word.length != s.length) continue
            s.word = word

            if(!s.doesCrossingFit()) {
                continue
            }

            if(solve(s.next, words)) {
                return true
            }
        }
        return false
    }

    private fun findCrossing(s: Space) {
        var current = s
        while (true) {
            var prev = current.prev
            while (prev != null) {
                current.addCrossing(prev)

                prev = prev.prev ?: break
            }

            current = current.next ?: break
        }
    }

    private fun findSpaces(grid: Array<Array<Char>>): List<Space> {
        val spaces = ArrayList<Space>()
        for (r in 0 until size) {
            for(c in 0 until size) {
                val row = grid[r]
                if(row[c] != '-') {
                    continue
                }
                if(r < size - 1 && grid[r + 1][c] == '-' && (r == 0 || grid[r - 1][c] != '-')) {
                    spaces.add(Space(Pos(r, c), true))
                }
                if(c < size - 1 && row[c + 1] == '-' && (c == 0 || row[c - 1] != '-')) {
                    spaces.add(Space(Pos(r, c), false))
                }
            }
        }
        return spaces
    }

    class Space(private val pos: Pos, private val vertical: Boolean) {
        var next: Space? = null
        var prev: Space? = null
        var length = 0
        var word: String = ""
        private val crossings = ArrayList<Pair<Pos, Space>>()

        fun putWord(grid: Array<Array<Char>>) {
            var r = pos.r
            var c = pos.c
            for (i in 0 until length) {
                grid[r][c] = word[i]
                if(vertical) {
                    r++
                } else {
                    c++
                }
            }

            next?.putWord(grid)
        }

        fun doesCrossingFit(): Boolean {
            for (crossing in crossings) {
                val p = crossing.first
                if(getLetter(p) != crossing.second.getLetter(p)) {
                    return false
                }
            }
            return true
        }

        fun calcLength(grid: Array<Array<Char>>) {
            var r = pos.r
            var c = pos.c
            while (c < 10 && r < 10 && grid[r][c] == '-') {
                if(vertical) {
                    r++
                } else {
                    c++
                }
                length++
            }
        }

        fun addCrossing(prev: Space) {
            if(prev.vertical == vertical) {
                return
            }
            if(vertical) {
                if(pos.r > prev.pos.r) {
                    return
                }
                if(pos.c > prev.pos.c + prev.length - 1) {
                    return
                }
                crossings.add(Pair(Pos(prev.pos.r, pos.c), prev))
                return
            }
            if(pos.c > prev.pos.c) {
                return
            }
            if(pos.r > prev.pos.r + prev.length - 1) {
                return
            }
            crossings.add(Pair(Pos(pos.r, prev.pos.c), prev))
        }

        private fun getLetter(p: Pos): Char {
            if(vertical) {
                return word[p.r - pos.r]
            }
            return word[p.c - pos.c]
        }

        override fun toString(): String {
            return "$pos next=$next"
        }
    }

    class Pos(val r: Int, val c: Int) {
        override fun toString(): String {
            return "$r $c"
        }
    }

    private val size = 10
}