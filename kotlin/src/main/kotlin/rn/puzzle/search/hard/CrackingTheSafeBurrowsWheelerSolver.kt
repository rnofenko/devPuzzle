package rn.puzzle.search.hard

import java.lang.StringBuilder

class CrackingTheSafeBurrowsWheelerSolver {
    fun solve(n: Int, k: Int): String {
        val alphabet = generateSequence(0) { it + 1 }.take(k).toList().toIntArray()
        if(k == 1) {
            return alphabet[0].toString().repeat(n)
        }
        val a = IntArray(n * k)
        val sequence = StringBuilder()

        fun build(t: Int, p: Int) {
            if(t > n) {
                if(n % p == 0) {
                    for(i in 1..p) {
                        sequence.append(alphabet[a[i]])
                    }
                }
            } else {
                a[t] = a[t - p]
                build(t + 1, p)
                for(j in a[t - p] + 1 until k) {
                    a[t] = j
                    build(t + 1, t)
                }
            }
        }

        build(1, 1)

        for (i in 0 until n - 1)
            sequence.append(alphabet[0])

        return sequence.toString()
    }
}