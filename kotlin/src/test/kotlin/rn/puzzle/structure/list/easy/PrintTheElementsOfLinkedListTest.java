package rn.puzzle.structure.list.easy;

import org.junit.Test;
import rn.tool.SinglyLinkedListNode;

public class PrintTheElementsOfLinkedListTest {
    private static void printLinkedList(SinglyLinkedListNode head) {
        while (head != null) {
            System.out.println(head.data);
            head = head.next;
        }
    }

    @Test
    public void test1() {
        printLinkedList(null);
    }
}