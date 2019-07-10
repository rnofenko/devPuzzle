package rn.puzzle.string.medium

class MaximumPalindromesFermatSolver {
    fun initialize(s: String) {
        factorialMap.add(1)
        buckets = splitByBuckets(s, bucketSize)
    }

    private fun splitByBuckets(s: String, bucketSize: Int): Array<Bucket> {
        val a = s.map { it - 'a' }.toIntArray()
        val buckets = ArrayList<Bucket>()
        var index = 0
        while (index < a.size) {
            val startIndex = index
            val endIndex = Math.min(a.size - 1, startIndex + bucketSize - 1)

            val counts = IntArray(countArraySize)
            for (i in startIndex..endIndex) {
                counts[a[i]]++
            }

            val bucket = Bucket(a, counts, startIndex, endIndex)
            buckets.add(bucket)

            index = endIndex + 1
        }
        return buckets.toTypedArray()
    }

    fun answerQuery(l: Int, r: Int): Int {
        val totalCounts = getCounts(l, r)
        val singleCount = totalCounts.count { it % 2 == 1 }
        val pairCounts = totalCounts.map { it / 2 }.filter { it > 0 }.toIntArray()
        var facSize = 0
        val facDivs = ArrayList<Int>()
        for (i in pairCounts) {
            facSize += i
            if(i > 1) {
                facDivs.add(i)
            }
        }

        var t = 1L
        if(facSize > 0) {
            t = calcModFactorial(facSize)
            val b = calcBottom(facDivs)
            if (b != 0L) {
                t = t * b % m
            }
        }
        if(singleCount != 0) {
            t *= singleCount
        }
        val final = t % m
        return final.toInt()
    }

    private fun calcBottom(list: List<Int>): Long {
        var b = 1L
        for (i in list) {
            val f = calcModFactorial(i)
            b = b * f % m
        }
        return pow(b, m - 2)
    }

    fun pow(x: Long, n: Int): Long {
        if(n == 0) {
            return 1
        }
        if(n == 1) {
            return x
        }
        val x2 = x * x % m
        var r = pow(x2, n / 2)
        if(n % 2 == 1) {
            r *= x
        }
        return r % m
    }

    private fun calcModFactorial(k: Int): Long {
        var b = factorialMap.last()
        for (i in (factorialMap.size + 1)..k) {
            val mult = b * i
            b = mult % m
            factorialMap.add(b)
        }
        return factorialMap[k - 1]
    }

    private fun getCounts(l: Int, r: Int): IntArray {
        val counts = IntArray(countArraySize)
        var index = l - 1
        while (index < r) {
            val bucket = buckets[index / bucketSize]
            bucket.incCounts(counts, index, r - 1)
            index = bucket.endIndex + 1
        }
        return counts.filter { it > 0 }.toIntArray()
    }

    private val bucketSize = 3000
    private val countArraySize = 'z' - 'a' + 1
    private lateinit var buckets: Array<Bucket>
    private var factorialMap = ArrayList<Long>()
    private val m = 1000_000_007

    class Bucket(val s: IntArray, private val counts: IntArray, private val startIndex: Int, val endIndex: Int) {
        fun incCounts(counts: IntArray, incStart: Int, incEnd: Int) {
            if(startIndex >= incStart && endIndex <= incEnd) {
                for (i in counts.indices) {
                    counts[i] += this.counts[i]
                }
            } else {
                val locStart = Math.max(startIndex, incStart)
                val locEnd = Math.min(endIndex, incEnd)
                for(i in locStart..locEnd) {
                    counts[s[i]]++
                }
            }
        }
    }
}