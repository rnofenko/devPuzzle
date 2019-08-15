package rn.puzzle.linkedlist.easy;

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
}