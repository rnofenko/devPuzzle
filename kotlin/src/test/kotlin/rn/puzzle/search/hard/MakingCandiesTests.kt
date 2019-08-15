package rn.puzzle.search.hard

import org.junit.Assert
import org.junit.Test
import kotlin.collections.ArrayList
import kotlin.math.*

class MakingCandiesTests {
    private fun minimumPasses(initMachines: Long, initWorkers: Long, price: Long, goal: Long): Long {
        val list = greedyCalc(initMachines, initWorkers, price, goal)
        return minimizeList(list, goal)
    }

    private fun minimizeList(list: List<State>, goal: Long): Long {
        var minPasses = list.last().passes
        for(i in list.size - 2 downTo 0) {
            val passes = calcSimplePasses(list[i], goal)
            if(passes > minPasses) {
                return minPasses
            }
            minPasses = passes
        }

        return minPasses
    }

    private fun calcSimplePasses(state: State, goal: Long): Long {
        val candies = state.m * state.w
        if(candies <= 0) {
            return state.passes + 1
        }
        val diff = goal - state.total
        var passes = diff / candies
        if(passes * candies < diff) {
            passes++
        }
        return state.passes + passes
    }

    private fun greedyCalc(initMachines: Long, initWorkers: Long, price: Long, goal: Long): List<State> {
        var state = State(initMachines, initWorkers, 0, 0)
        val list = ArrayList<State>()
        list.add(state)
        while (state.total in 0 until goal) {
            state = greedyCalcNext(state, price, goal)
            list.add(state)
        }
        return list
    }

    private fun greedyCalcNext(prev: State, price: Long, goal: Long): State {
        val m = prev.m
        val w = prev.w

        if(areTooBig(m,w)) {
            return State(m, w, -1, prev.passes + 1)
        }
        val candies = m * w
        var total = candies + prev.total
        if(candies <= 0 || total <= 0) {
            return State(m, w, -1, prev.passes + 1)
        }
        if(total > goal || total <= 0) {
            return State(m, w, total, prev.passes + 1)
        }

        var improvesCount = total / price
        if(improvesCount == 0L) {
            var emptySteps = (min(price, goal) - total) / candies
            if(emptySteps > 0) {
                if (emptySteps * candies == price - total) {
                    emptySteps--
                }
                return State(m, w, total + emptySteps * candies, prev.passes + emptySteps + 1)
            }
            return State(m, w, total, prev.passes + 1)
        }

        total -= improvesCount * price
        var diff = abs(m - w)
        diff = min(diff, improvesCount)
        improvesCount -= diff
        val improveBig = diff + improvesCount / 2 + (if(improvesCount % 2L == 0L) 0 else 1)
        val improveSmall = improvesCount / 2

        return if(m > w) {
            State(m + improveSmall, w + improveBig, total, prev.passes + 1)
        } else {
            State(m + improveBig, w + improveSmall, total, prev.passes + 1)
        }
    }

    private fun areTooBig(m: Long, w: Long): Boolean {
        val maxDouble = Long.MAX_VALUE * 2.0
        val value = m * 1.0 * w
        return value > maxDouble
    }

    private class State(val m: Long, val w: Long, val total: Long, val passes: Long) {
        override fun toString(): String {
            return "m $m w $w  total $total passes $passes"
        }
    }

    @Test
    fun test38() {
        Assert.assertEquals(1, minimumPasses(4294967297,4294967297,1000000000000,1000000000000))
    }

    @Test
    fun test6() {
        Assert.assertEquals(1, minimumPasses(4294967296,4294967296,10,10000))
    }

    @Test
    fun test5() {
        Assert.assertEquals(16, minimumPasses(1,1,6,45))
    }

    @Test
    fun test1() {
        Assert.assertEquals(3, minimumPasses(3,1,2,12))
    }

    @Test
    fun test2() {
        Assert.assertEquals(4, minimumPasses(1,2,1,60))
    }

    @Test
    fun test4() {
        Assert.assertEquals(26348839006, minimumPasses(1,1,10_000_000_000,1_000_000_000_000))
    }
}