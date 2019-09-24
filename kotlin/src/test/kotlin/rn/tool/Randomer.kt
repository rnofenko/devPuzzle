package rn.tool

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

object Rand {
    private val r = Random()

    fun newArray(size: Int, maxSize: Int = 100): IntArray {
        return generateSequence { abs(r.nextInt()) % maxSize }.take(size).asIterable().toList().toIntArray()
    }

    fun graphEdges(count: Int, startEdgeNo: Int, endEdgeNo: Int, valueMaxSize: Int = 100): Array<IntArray> {
        val res = ArrayList<IntArray>()
        val edgeRange = endEdgeNo - startEdgeNo + 1
        val existing = HashSet<String>()
        while (res.size < count) {
            val v1 = abs(r.nextInt()) % edgeRange
            val v2 = abs(r.nextInt()) % edgeRange
            if(v1 == v2) {
                continue
            }
            val id = "${min(v1, v2)}_${max(v1, v2)}"
            if(existing.contains(id)) {
                continue
            }
            existing.add(id)
            val value = abs(r.nextInt()) % valueMaxSize
            res.add(intArrayOf(v1, v2, value))
        }
        return res.toTypedArray()
    }
}