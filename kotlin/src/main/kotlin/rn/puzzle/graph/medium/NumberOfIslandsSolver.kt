package rn.puzzle.graph.medium

class NumberOfIslandsSolver {
    fun numIslands(grid: Array<CharArray>): Int {
        if(grid.isEmpty()) {
            return 0
        }
        val width = grid[0].size
        if(width == 0) {
            return 0
        }
        val height = grid.size
        var leftTreeNo = 0
        val trees = ArrayList<Int>()
        trees.add(0)
        val topTreeGrid = IntArray(width)

        fun getRootNo(no: Int): Int {
            val parentNo = trees[no]
            if(parentNo == 0) {
                return no
            }
            val rootNo = getRootNo(parentNo)
            trees[no] = rootNo
            return rootNo
        }

        for(r in 0 until height) {
            leftTreeNo = 0
            val row = grid[r]
            for(c in 0 until width) {
                if(row[c] == '0') {
                    leftTreeNo = 0
                    topTreeGrid[c] = 0
                    continue
                }
                if(topTreeGrid[c] > 0) {
                    val topTreeNo = topTreeGrid[c]
                    if(leftTreeNo == 0) {
                        leftTreeNo = topTreeNo
                    } else if(leftTreeNo != topTreeNo) {
                        val leftRoot = getRootNo(leftTreeNo)
                        val topRoot = getRootNo(topTreeNo)
                        leftTreeNo = Math.min(leftRoot, topRoot)
                        if(leftRoot != topRoot) {
                            trees[Math.max(leftRoot, topRoot)] = leftTreeNo
                        }
                    }
                }
                if(leftTreeNo == 0) {
                    leftTreeNo = trees.size
                    trees.add(0)
                }
                topTreeGrid[c] = leftTreeNo
            }
        }

        var treesCount = 0
        for(i in 1..trees.lastIndex) {
            if(i == getRootNo(i)) {
                treesCount++
            }
        }

        return treesCount
    }
}