package rn.puzzle.string.low

class WeightedUniformStringsSolver {
    fun weightedUniformStrings(s: String, queries: Array<Int>): Array<String> {
        val map = createMap(s)
        val res = queries.map { map[it] ?: false }.map { if(it) "Yes" else "No" }
        return res.toTypedArray()
    }

    private fun createMap(s: String): Map<Int, Boolean> {
        var current = '0'
        val map = HashMap<Int, Boolean>()
        val zero = 'a'.toByte() - 1
        var sum = 0
        for (c in s) {
            if(current != c) {
                sum = 0
                current = c
            }

            sum += c.toByte() - zero
            map[sum] = true
        }

        return map
    }
}