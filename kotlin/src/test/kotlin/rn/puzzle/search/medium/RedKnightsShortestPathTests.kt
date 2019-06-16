package rn.puzzle.search.medium

import org.junit.Assert
import org.junit.Test

class RedKnightsShortestPathTests {
    @Test
    fun sample1() {
        Assert.assertEquals("UL UL UL L", getShortestPath(7, Coord(6, 6), Coord(0, 1)).second)
    }

    @Test
    fun sample2() {
        Assert.assertEquals("x", getShortestPath(6, Coord(5, 1), Coord(5, 0)).second)
    }

    @Test
    fun sample3() {
        Assert.assertEquals("LR LL", getShortestPath(7, Coord(0, 3), Coord(4, 3)).second)
    }

    @Test
    fun sample4() {
        val expRes = "LR LR LR LR LR LR LR LR LR LR LR LR LR LR LR LR LR LR LR LR LR LR LR LR LR LR LR LR LR LR LR LR LR LL LL LL LL LL LL LL LL LL LL LL LL"
        Assert.assertEquals(expRes, getShortestPath(100, Coord(2, 24), Coord(92, 45)).second)
        printShortestPath(1,1,1,1,1)
    }

    private fun printShortestPath(n: Int, i1: Int, i2: Int, i3: Int, i4: Int) {
        val pair = getShortestPath(n, Coord(i1, i2), Coord(i3, i4))
        if(pair.first == 0) {
            System.out.println("Impossible")
        } else {
            System.out.println(pair.first)
            System.out.println(pair.second)
        }
    }

    private fun getShortestPath(n: Int, start: Coord, end: Coord): Pair<Int,String> {
        val startRoute = Route(start, "")
        val path = find(listOf(startRoute), end, n)
        return Pair(path.length / 3, path.trim().replace("_", ""))
    }

    private fun find(routes: List<Route>, end: Coord, n: Int): String {
        val nextRoutes = ArrayList<Route>()

        for (route in routes) {
            for (possibleMove in possibleMoves) {
                val nextPos = route.pos.next(possibleMove, n) ?: continue
                val newPath = route.path + possibleMove.code
                if(nextPos == end) {
                    return newPath
                }
                val prevBest = best[nextPos] ?: Int.MAX_VALUE
                if(prevBest <= newPath.length) {
                    continue
                }
                best[nextPos] = newPath.length
                nextRoutes.add(Route(nextPos, newPath))
            }
        }

        if(nextRoutes.isEmpty()) {
            return "x"
        }

        return find(nextRoutes, end, n)
    }

    class Route(val pos: Coord, val path: String)

    data class PossibleMove(val r:Int, val c: Int, val code: String)

    data class Coord(val r:Int, val c: Int) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true

            other as Coord

            if (r != other.r) return false
            if (c != other.c) return false

            return true
        }

        override fun hashCode(): Int {
            return 1000 * r + c
        }

        private fun isValid(n: Int): Boolean {
            return r >=0 && c >= 0 && r < n && c < n
        }

        fun next(shift: PossibleMove, n: Int): Coord? {
            val newCoord = Coord(r + shift.r, c + shift.c)
            if(newCoord.isValid(n)) {
                return newCoord
            }
            return null
        }
    }

    private val best = HashMap<Coord, Int>()

    private val possibleMoves = arrayOf(
            PossibleMove(-2, -1, "UL "),
            PossibleMove(-2, 1, "UR "),
            PossibleMove(0, 2, "R_ "),
            PossibleMove(2, 1, "LR "),
            PossibleMove(2, -1, "LL "),
            PossibleMove(0, -2, "L_ "))
}