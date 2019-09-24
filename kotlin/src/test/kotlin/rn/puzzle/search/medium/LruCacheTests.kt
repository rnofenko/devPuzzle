package rn.puzzle.search.medium

import org.junit.Assert
import org.junit.Test

class LruCacheTests {
    private class LRUCache(val capacity: Int) {
        private val map = HashMap<Int, ListItem>()
        private var recentItem: ListItem? = null
        private var lastItem: ListItem? = null

        fun get(key: Int): Int {
            val item = map[key] ?: return -1
            updateToHighest(item)
            return item.value
        }

        fun put(key: Int, value: Int) {
            val item = map[key]
            if(item == null) {
                removeLast()

                val newItem = ListItem(null, recentItem, key, value)
                if (recentItem != null) {
                    recentItem!!.left = newItem
                }
                recentItem = newItem
                if (lastItem == null) {
                    lastItem = recentItem
                }
                map[key] = newItem
            } else {
                item.value = value
                updateToHighest(item)
            }
        }

        private fun updateToHighest(item: ListItem) {
            if(map.size == 1) {
                return
            }
            val left = item.left ?: return
            val right = item.right

            if(right == null) {
                lastItem = item.left
                lastItem!!.right = null
            } else {
                left.right = right
                right.left = left
            }

            recentItem!!.left = item
            item.left = null
            item.right = recentItem
            recentItem = item
        }

        private fun removeLast() {
            if(map.size < capacity) {
                return
            }

            map.remove(lastItem!!.key)
            val left = lastItem!!.left
            if(left == null) {
                recentItem = null
                lastItem = null
            } else {
                left.right = null
                lastItem = left
            }
        }
    }

    private class ListItem(var left: ListItem?, var right: ListItem?, val key: Int, var value: Int) {
        override fun toString(): String {
            return "$value"
        }
    }

    @Test
    fun test3() {
        val cache = LRUCache(2)
        cache.put(2, 1)
        cache.put(2, 2)
        Assert.assertEquals(2, cache.get(2))
    }

    @Test
    fun test2() {
        val cache = LRUCache(1)
        cache.put(2, 1)
        cache.get(2)
        cache.put(3, 2)
        cache.get(2)
        cache.get(3)
    }

    @Test
    fun test1() {
        val cache = LRUCache(2)
        cache.put(1, 1)
        cache.put(2, 2)
        cache.get(1)       // returns 1
        cache.put(3, 3)    // evicts key 2
        cache.get(2)       // returns -1 (not found)
        cache.put(4, 4)    // evicts key 1
        cache.get(1)       // returns -1 (not found)
        cache.get(3)       // returns 3
        cache.get(4)       // returns 4
    }
}