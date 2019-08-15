package rn.standard.sorting

/**
 * Radix sorting. Least significant digit variation.
 */
class RadixLsdSorter {
    fun sort(data: IntArray): IntArray {
        var source = listOf(ArrayList(data.toList()))
        var truncatePart = 1
        val granularity = 10
        for (l in 1..9) {
            truncatePart *= granularity
            val divisor = truncatePart / granularity

            val buckets = generateSequence { ArrayList<Int>() }.take(granularity).toList()
            for (sourceList in source) {
                for (d in sourceList) {
                    val i = (d % truncatePart) / divisor
                    buckets[i].add(d)
                }
            }
            source = buckets
        }

        val buckets = generateSequence { ArrayList<Int>() }.take(granularity).toList()
        for (sourceList in source) {
            for (d in sourceList) {
                val i = d / truncatePart
                buckets[i].add(d)
            }
        }
        source = buckets

        val resList = source[0]
        for (i in 1 until source.size) {
            resList.addAll(source[i])
        }
        return resList.toIntArray()
    }
}