package rn.puzzle.structure.list.easy;

import org.junit.Test;
import rn.tool.SinglyLinkedListNode;

import java.util.Arrays;

public class MergeTwoSortedLinkedListsTest {

    @Test
    public void test1() {
        SinglyLinkedListNode l1 = SinglyLinkedListNode.toList(Arrays.asList(1, 2, 3));
        SinglyLinkedListNode l2 = SinglyLinkedListNode.toList(Arrays.asList(3, 4));

        SinglyLinkedListNode r = mergeLists(l1, l2);

        SinglyLinkedListNode.assertEqual(Arrays.asList(1, 2, 3, 3, 4), r);
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
}
