package rn.puzzle.game.medium.roadaandlibraries

class RoadsAndLibrariesListSolver {
    fun roadsAndLibraries(n: Int, libCost: Int, roadCost: Int, graph: Array<IntArray>): Long {
        var total = n.toLong() * libCost
        val diff = libCost.toLong() - roadCost
        if(roadCost >= libCost) {
            return total
        }

        val trees = Array<ArrayList<Int>?>(n + 1) { null }
        for (p in graph) {
            val i0 = p[0]
            val i1 = p[1]
            val t0 = trees[i0]
            val t1 = trees[i1]
            if(t0 == null && t1 == null) {
                val newTree = ArrayList<Int>()
                newTree.add(i0)
                newTree.add(i1)
                trees[i0] = newTree
                trees[i1] = newTree
                total -= diff
            } else if(t0 != null && t1 != null) {
                if(t0 == t1) {
                    continue
                }
                val small = if(t0.size > t1.size) t1 else t0
                val big = if(small == t0) t1 else t0
                for (smallNode in small) {
                    trees[smallNode] = big
                }
                big.addAll(small)
                total -= diff
            } else {
                if(t0 != null) {
                    t0.add(i1)
                    trees[i1] = t0
                } else {
                    t1?.add(i0)
                    trees[i0] = t1
                }
                total -= diff
            }
        }

        return total
    }
}