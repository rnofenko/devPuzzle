package rn.puzzle.sorting.low

class QuickSortPartitionSolver {
    fun quickSort(arr: Array<Int>): Array<Int> {
        val pivot = arr[0]
        val left = ArrayList<Int>()
        val right = ArrayList<Int>()
        var equalCount = 0

        for (element in arr) {
            when {
                element == pivot -> equalCount++
                element < pivot -> left.add(element)
                else -> right.add(element)
            }
        }

        for (i in 0 until equalCount) {
            left.add(pivot)
        }

        for (i in right) {
            left.add(i)
        }

        return left.toTypedArray()
    }
}