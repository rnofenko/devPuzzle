package rn.puzzle.structure.list.easy;

import org.junit.Test;
import rn.tool.SinglyLinkedListNode;

import java.util.Arrays;

public class InsertNodeAtSpecificPositionLinkedListTest {

    @Test
    public void test1() {
        SinglyLinkedListNode head = SinglyLinkedListNode.toList(Arrays.asList(1, 2, 3));
        SinglyLinkedListNode result = insertNodeAtPosition(head, 4, 0);
        SinglyLinkedListNode.assertEqual(Arrays.asList(4, 1, 2, 3), result);
    }

    @Test
    public void test2() {
        SinglyLinkedListNode head = SinglyLinkedListNode.toList(Arrays.asList(1, 2, 3));
        SinglyLinkedListNode result = insertNodeAtPosition(head, 4, 1);
        SinglyLinkedListNode.assertEqual(Arrays.asList(1, 4, 2, 3), result);
    }

    @Test
    public void test3() {
        SinglyLinkedListNode head = SinglyLinkedListNode.toList(Arrays.asList(1, 2, 3));
        SinglyLinkedListNode result = insertNodeAtPosition(head, 4, 3);
        SinglyLinkedListNode.assertEqual(Arrays.asList(1, 2, 3, 4), result);
    }

    private SinglyLinkedListNode insertNodeAtPosition(SinglyLinkedListNode head, int data, int position) {
        SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);

        if(head == null) {
            return newNode;
        }

        int index = 0;
        SinglyLinkedListNode current = head;
        SinglyLinkedListNode prev = null;
        while (index < position) {
            prev = current;
            current = current.next;
            index++;
        }
        if(prev != null) {
            prev.next = newNode;
        } else {
            head = newNode;
        }
        newNode.next = current;

        return head;
    }
}
