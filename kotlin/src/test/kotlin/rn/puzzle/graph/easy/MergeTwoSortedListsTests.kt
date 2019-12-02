package rn.puzzle.graph.easy

import org.junit.Test

class MergeTwoSortedListsTests {
    @Test
    fun test1() {
        val n1 = toNode(intArrayOf(1,4,4,5))
        val n2 = toNode(intArrayOf(2,3,4))
        val r = mergeTwoLists(n1, n2)
    }

    private fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        return mergeTwoListsKeepNodes(l1, l2)
    }

    private fun mergeTwoListsKeepNodes(l1: ListNode?, l2: ListNode?): ListNode? {
        if(l1 === null || l2 == null) {
            return l1 ?: l2
        }
        var n1 = l1
        var n2 = l2
        var root : ListNode? = null
        var r = root
        while (n1 != null && n2 != null) {
            val minNode = if(n1.`val` > n2.`val`) n2 else n1
            if(root == null) {
                r = minNode
                root = r
            } else {
                r!!.next = minNode
                r = minNode
            }
            if(n1 == minNode) {
                n1 = n1.next
            } else {
                n2 = n2.next
            }
        }
        r!!.next = n1 ?: n2
        return root
    }

    private fun toNode(a: IntArray): ListNode? {
        var old: ListNode? = null
        for(i in a.lastIndex downTo 0) {
            val new = ListNode(a[i])
            new.next = old
            old = new
        }
        return old
    }

    class ListNode(var `val`: Int) {
     var next: ListNode? = null
    }
}