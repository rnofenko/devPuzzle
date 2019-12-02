package rn.puzzle.list.easy;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MergeTwoSortedLinkedListsTest {

    @Test
    public void test1() {
        SinglyLinkedListNode l1 = toList(Arrays.asList(1, 2, 3));
        SinglyLinkedListNode l2 = toList(Arrays.asList(3, 4));

        SinglyLinkedListNode r = mergeLists(l1, l2);

        assertEqual(Arrays.asList(1, 2, 3, 3, 4), r);
    }

    private SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        SinglyLinkedListNode root = null;
        SinglyLinkedListNode current = null;
        while (head1 != null || head2 != null) {
            SinglyLinkedListNode next;
            if(head2 == null || (head1 != null && head1.data < head2.data)) {
                next = head1;
                head1 = next.next;
            } else {
                next = head2;
                head2 = next.next;
            }

            if(current == null) {
                root = next;
            } else {
                current.next = next;
            }
            current = next;
        }

        return root;
    }

    private void assertEqual(List<Integer> expected, SinglyLinkedListNode node) {
        for (int v : expected) {
            Assert.assertNotNull(node);
            Assert.assertEquals(v, node.data);
            node = node.next;
        }
    }

    private SinglyLinkedListNode toList(List<Integer> numbers) {
        SinglyLinkedListNode root = null;
        SinglyLinkedListNode current = null;
        for (Integer number : numbers) {
            SinglyLinkedListNode next = new SinglyLinkedListNode();
            next.data = number;
            if(current == null) {
                root = next;
            } else {
                current.next = next;
            }
            current = next;
        }

        return root;
    }

    private static class SinglyLinkedListNode { int data; SinglyLinkedListNode next; }
}
