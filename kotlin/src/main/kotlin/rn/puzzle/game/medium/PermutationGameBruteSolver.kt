package rn.puzzle.game.medium

class PermutationGameBruteSolver {
    fun permutationGame(arr: Array<Int>): String {
        for (i in arr.indices) {
            tryDelete(i, arr)
        }
        return "Alice"
    }

    private fun tryDelete(index: Int, arr: Array<Int>) {
        val a = arr.toMutableList()
        a.removeAt(index)


    }
}