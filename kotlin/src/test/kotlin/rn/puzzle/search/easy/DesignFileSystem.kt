package rn.puzzle.search.easy

import org.junit.Test

class DesignFileSystem {
    val map = HashMap<String, Int>()

    private fun create(path: String, value: Int): Boolean {
        val lastIndex = path.indexOfLast { it == '/' }
        if(lastIndex > 0) {
            val parent = path.substring(0, lastIndex)
            if(!map.containsKey(parent)) {
                return false
            }
        }
        map[path] = value
        return true
    }

    fun get(path: String): Int {
        return map[path] ?: return -1
    }

    @Test
    fun test1() {
        create("/leet", 1)
        create("/leet/code", 2)
        get("/leet/code")
        create("/c/d", 2)
        get("/c")
    }
}