package rn.puzzle.search.hard

import kotlin.collections.ArrayList

class MaximizingMissionPointsSolver(private val xDiff: Int, private val yDiff: Int) {
    private val maxIndex = 199999
    private val boxSize = 5000
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

        return boxes.map { it.totalMax }.max() ?: 0
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
            val box = Box(IntRange(start, end))
            list.add(box)

            start = end + 1
        }

        return list
    }

    class Box(val range: IntRange) {
        var totalMax = 0L
        private val items = ArrayList<City>()

        fun add(city: City) {
            items.add(city)
            if(city.points > totalMax) {
                totalMax = city.points
            }
        }

        fun getMax(xRange: IntRange, yRange: IntRange): Long {
            var max = 0L
            for (item in items) {
                if(item.points > max && item.y in yRange && item.x in xRange) {
                    max = item.points
                }
            }
            return max
        }
    }

    data class City(val x: Int, val y: Int, val h: Int, var points: Long)
}