package rn.puzzle.string.standard

class SuffixArrayNaiveBuilder {
    fun build(s: String): IntArray {
        val startIndexes = generateSequence(0, { it + 1 }).take(s.length).toList()
        return startIndexes.sortedWith(SubStringComparator(s)).toIntArray()
    }

    class SubStringComparator(private val text: String) : Comparator<Int> {
        override fun compare(o1: Int?, o2: Int?): Int {
            val s1 = text.substring(o1 ?: 0)
            val s2 = text.substring(o2 ?: 0)
            return s1.compareTo(s2)
        }
    }

    //DC-3 algorithm
}