package rn.puzzle.search.low

class IceCreamParlorSolver {
    fun iceCreamParlor(m: Int, arr: Array<Int>): Array<Int> {
        val map = HashMap<Int, Int>()
        for (i in 0 until arr.size) {
            val first = arr[i]
            val second = m - first
            if(map.containsKey(second)) {
                val firstIndex = map[second] ?: 0
                return arrayOf(firstIndex + 1, i + 1)
            }
            map[first] = i
        }

        return arrayOf(0, 0)
    }
}