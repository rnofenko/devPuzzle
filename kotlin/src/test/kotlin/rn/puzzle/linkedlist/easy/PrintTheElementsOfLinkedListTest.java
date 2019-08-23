package rn.puzzle.linkedlist.easy;

import org.junit.Test;

public class PrintTheElementsOfLinkedListTest {
    static void printLinkedList(SinglyLinkedListNode head) {
        while (head != null) {
            System.out.println(head.data);
            head = head.next;
        }
    }

    public class SinglyLinkedListNode {
        int data;
        SinglyLinkedListNode next;
    }

    @Test
    public void test1() {
        printLinkedList(null);
    }
}