package rn.puzzle.array.medium

import org.junit.Assert
import org.junit.Test
import java.util.*
import kotlin.collections.HashMap

class SplitArrayIntoConsecutiveSubsequencesTests {
    private fun isPossible(nums: IntArray): Boolean {
        val map = SmartMap()
        for (num in nums) {
            val prev = num - 1
            if(!map.clean(prev - 1)) {
                return false
            }

            val parent = map.pop(prev)
            val node = Node(num, parent, if (parent == null) 1 else parent.level + 1)
            map.push(node)
        }

        return map.cleanAll()
    }

    private class Node(val value: Int, val parent: Node?, val level: Int) {
        override fun toString(): String {
            return "v=$value l=$level parent=$parent"
        }
    }

    private class SmartMap {
        val smallMap = HashMap<Int, LinkedList<Node>>()
        val bigMap = HashMap<Int, LinkedList<Node>>()

        fun clean(no: Int): Boolean {
            if(smallMap.containsKey(no)) {
                return false
            }
            bigMap.remove(no)
            return true
        }

        fun cleanAll(): Boolean {
            return smallMap.isEmpty()
        }

        fun pop(no: Int): Node? {
            var list = smallMap[no]
            if(list != null) {
                val node = list.pop()
                if(list.isEmpty()) {
                    smallMap.remove(no)
                }
                return node
            }
            list = bigMap[no]
            if(list != null) {
                val node = list.pop()
                if(list.isEmpty()) {
                    bigMap.remove(no)
                }
                return node
            }
            return null
        }

        fun push(node: Node) {
            val map = if(node.level >= 3) bigMap else smallMap
            val list = map.getOrPut(node.value) { LinkedList() }
            list.push(node)
        }
    }

    @Test
    fun test6() {
        Assert.assertEquals(true, isPossible(intArrayOf(4,5,6,7,7,8,8,9,10,11)))
    }

    @Test
    fun test1() {
        Assert.assertEquals(true, isPossible(intArrayOf(1,2,3,3,4,4,5,5)))
    }

    @Test
    fun test4() {
        Assert.assertEquals(true, isPossible(intArrayOf(1,2,3,3,4,4,5,5,6,6)))
    }

    @Test
    fun test5() {
        Assert.assertEquals(true, isPossible(intArrayOf(1,1,1,2,2,2,3,3,3,4,4,5)))
    }

    @Test
    fun test2() {
        Assert.assertEquals(false, isPossible(intArrayOf(1,2,3,4,4,5)))
    }

    @Test
    fun test3() {
        Assert.assertEquals(true, isPossible(intArrayOf(1,2,3,3,4,5)))
    }
}