package rn.puzzle.search.hard

import java.util.*
import kotlin.collections.ArrayList

class MaximizingMissionPointsSolver(private val xDiff: Int, private val yDiff: Int) {
    private val maxIndex = 199999
    private val boxSize: Int = if(xDiff > 1000) 1500 else 100
    private val boxes: List<Box>
    private val all = ArrayList<City>()

    fun add(x: Int, y: Int, h: Int, points: Int) {
        val city = City(x-1,y-1,h,points.toLong())
        all.add(city)
    }

    fun solve(): Long {
        val all = all.sortedBy { it.h }
        for (city in all) {
            val max = findMax(city)
            city.points += max

            val box = boxes[city.x / boxSize]
            box.add(city)
        }

        return boxes.map { it.totalMax }.maxOrNull() ?: 0
    }

    private fun findMax(center: City): Long {
        val xRange = IntRange(Math.max(center.x - xDiff, 0), Math.min(center.x + xDiff, maxIndex))
        val yRange = IntRange(Math.max(center.y - yDiff, 0), Math.min(center.y + yDiff, maxIndex))
        val startBoxIndex = xRange.start / boxSize
        val endBoxIndex = xRange.endInclusive / boxSize

        var max = Long.MIN_VALUE
        for(i in startBoxIndex..endBoxIndex) {
            val box = boxes[i]
            if(max > box.totalMax) {
                continue
            }
            val localMax = box.getMax(xRange, yRange)
            if(localMax > max) {
                max = localMax
            }
        }

        return max
    }

    init {
        boxes = buildBoxes(0, maxIndex, boxSize)
    }

    private fun buildBoxes(startIndex: Int, endIndex: Int, boxSize: Int) : List<Box> {
        var start = startIndex
        val list = ArrayList<Box>()
        while (start < endIndex) {
            val end = Math.min(start + boxSize - 1, endIndex)
            val box = Box()
            list.add(box)

            start = end + 1
        }

        return list
    }

    inner class Box {
        private val maxIndex = 199999
        private val innerBoxSize: Int = if(xDiff > 1000) 10000 else 1000
        var totalMax = 0L
        private val innerBoxes: List<InnerBox>

        fun add(city: City) {
            val innerBox = innerBoxes[city.y / innerBoxSize]
            innerBox.add(city)

            totalMax = Math.max(totalMax, innerBox.totalMax)
        }

        fun getMax(xRange: IntRange, yRange: IntRange): Long {
            val startInnerIndex = yRange.start / innerBoxSize
            val endInnerIndex = yRange.endInclusive / innerBoxSize

            var max = 0L
            for(i in startInnerIndex..endInnerIndex) {
                val box = innerBoxes[i]
                if(max > box.totalMax) {
                    continue
                }
                val localMax = box.getMax(xRange, yRange)
                if(localMax > max) {
                    max = localMax
                }
            }

            return max
        }

        init {
            innerBoxes = buildInnerBoxes(0, maxIndex, innerBoxSize)
        }

        private fun buildInnerBoxes(startIndex: Int, endIndex: Int, boxSize: Int) : List<InnerBox> {
            var start = startIndex
            val list = ArrayList<InnerBox>()
            while (start < endIndex) {
                val end = Math.min(start + boxSize - 1, endIndex)
                val box = InnerBox()
                list.add(box)

                start = end + 1
            }

            return list
        }

        override fun toString(): String {
            return "max=$totalMax"
        }
    }

    class InnerBox {
        var totalMax = 0L
        private val items = LinkedList<City>()

        fun add(city: City) {
            for(i in 0 until items.size) {
                val p = items[i].points
                if(p <= city.points) {
                    items.add(i, city)
                    totalMax = items.first.points
                    return
                }
            }

            items.add(city)
            totalMax = items.first.points
        }

        fun getMax(xRange: IntRange, yRange: IntRange): Long {
            for (item in items) {
                if(item.y in yRange && item.x in xRange) {
                    return item.points
                }
            }
            return 0
        }
    }

    data class City(val x: Int, val y: Int, val h: Int, var points: Long)
}