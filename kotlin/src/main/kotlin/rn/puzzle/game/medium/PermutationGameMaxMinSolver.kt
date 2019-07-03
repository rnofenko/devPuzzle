package rn.puzzle.game.medium

class PermutationGameMaxMinSolver {
    fun permutationGame(arr: Array<Int>): String {
        val isAlice = arr.size % 2 == 0
        val asc = movesToFix(arr, true)
        val desc = movesToFix(arr, false)
        val total = asc + desc
        if(asc == 1) {
            return "Alice"
        }
        if(desc < asc) {
            if(isAlice) {
                return "Alice"
            }
            return if(total % 2 == 1) "Alice" else "Bob"
        }
        if(desc > asc) {
            if(isAlice) {
                return "Alice"
            }
            return if(total % 2 == 1) "Alice" else "Bob"
        }
        if(isAlice) {
            return "Alice"
        }
        return if(total % 2 == 1) "Alice" else "Bob"
    }

    private fun movesToFix(arr: Array<Int>, isAsc: Boolean): Int {
        val a = arr.toMutableList()
        var movesCount = 0

        while (a.size > 1) {
            var maxIndex = 0
            var minIndex = 0
            val lastIndex = a.indices.last
            for (i in 1..lastIndex) {
                if (a[i] > a[maxIndex]) {
                    maxIndex = i
                } else if (a[i] < a[minIndex]) {
                    minIndex = i
                }
            }
            if(!isAsc) {
                maxIndex = minIndex.also { minIndex = maxIndex }
            }

            if(minIndex == 0) {
                a.removeAt(0)
                continue
            } else if (maxIndex == lastIndex) {
                a.removeAt(lastIndex)
                continue
            }
            if(lastIndex - maxIndex > minIndex) {
                a.removeAt(maxIndex)
            } else {
                a.removeAt(minIndex)
            }
            movesCount++
        }
        return movesCount
    }
}