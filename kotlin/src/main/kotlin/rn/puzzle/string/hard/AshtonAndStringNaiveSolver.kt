package rn.puzzle.string.hard

class AshtonAndStringNaiveSolver {
    fun ashtonString(s: String, k: Int): Char {
        val joined = buildString(s)
        val len = joined.length
        println("total length $len")
        return joined[k - 1]
    }

    fun buildString(s: String): String {
        val set = HashSet<String>()
        for (i in 0 until s.length) {
            for (j in i+1..s.length) {
                set.add(s.substring(i, j))
            }
        }
        val sorted = set.toList().sorted()
        return sorted.joinToString("")
    }
}