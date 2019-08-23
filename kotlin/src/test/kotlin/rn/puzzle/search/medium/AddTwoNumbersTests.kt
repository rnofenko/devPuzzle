package rn.puzzle.search.medium

import org.junit.Test

class AddTwoNumbersTests {
    @Test
    fun test1() {
        solve("342", "465", "807")
    }

    private fun createList(v: String): ListNode {
        var prev : ListNode? = null
        for (c in v) {
            val n = ListNode(c.toString().toInt())
            n.next = prev
            prev = n
        }

        return prev ?: ListNode(0)
    }

    private fun solve(v1: String, v2: String, res: String) {
        val l1 = createList(v1)
        val l2 = createList(v2)
        val r = addTwoNumbers(l1, l2)
    }

    private fun addTwoNumbers(initL1: ListNode?, initL2: ListNode?): ListNode? {
        var l1 = initL1
        var l2 = initL2
        var buffer = 0
        var prev : ListNode? = null
        var root : ListNode? = null
        while (l1 != null || l2 != null || buffer > 0) {
            var sum = buffer
            if(l1 != null) {
                sum += l1.`val`
                l1 = l1.next
            }
            if(l2 != null) {
                sum += l2.`val`
                l2 = l2.next
            }
            val nevValue = sum % 10
            buffer = sum / 10

            val n = ListNode(nevValue)
            if(prev != null) {
                prev.next = n
            }
            prev = n

            root = root ?: n
        }
        return root
    }

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }
}