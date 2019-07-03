package rn.puzzle.game.medium.roadaandlibraries

class RoadsAndLibrariesDependencySolver {
    fun roadsAndLibraries(n: Int, libCost: Int, roadCost: Int, graph: Array<IntArray>): Long {
        var total = n.toLong() * libCost
        val diff = libCost.toLong() - roadCost
        if(roadCost >= libCost) {
            return total
        }

        val dependencyMap = DependencyMap(n)
        val trees = IntArray(n + 1)
        var treeNo = 0
        for (p in graph) {
            val node0 = p[0]
            val node1 = p[1]
            val t0 = trees[node0]
            val t1 = trees[node1]
            if(t0 == 0 && t1 == 0) {
                treeNo++
                trees[node0] = treeNo
                trees[node1] = treeNo
                total -= diff
            } else if(t0 != 0 && t1 != 0) {
                if(dependencyMap.areLinked(t0, t1)) {
                    continue
                }
                total -= diff
                dependencyMap.link(t0, t1)
            } else {
                if(t0 != 0) {
                    trees[node1] = t0
                } else {
                    trees[node0] = t1
                }
                total -= diff
            }
        }

        return total
    }

    class DependencyMap(n: Int) {
        private val links : IntArray = IntArray(n + 1)

        fun areLinked(t0: Int, t1: Int): Boolean {
            if(t0 == t1) {
                return true
            }
            var r0 = t0
            while(links[r0] != 0) {
                r0 = links[r0]
            }
            var r1 = t1
            while(links[r1] != 0) {
                r1 = links[r1]
            }

            return r0 == r1
        }

        fun link(t0: Int, t1: Int) {
            var r0 = t0
            while(links[r0] != 0) {
                r0 = links[r0]
            }
            var r1 = t1
            while(links[r1] != 0) {
                r1 = links[r1]
            }

            links[r1] = r0
        }
    }
}