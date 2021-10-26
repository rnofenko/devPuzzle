package rn.puzzle.structure.list.medium;

public class CycleDetectionTest {
    static boolean hasCycle(Node head) {
        Node slow = head;
        Node fast = head;
        while (true) {
            if (fast == null) {
                return false;
            }
            fast = fast.next;
            if (fast == null) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
    }

    private static class Node {
        int data;
        Node next;
    }
}
