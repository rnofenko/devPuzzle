package rn.puzzle.structure.list.easy;

public class InsertingNodeIntoSortedDoublyLinkedListTest {
    private static class DoublyLinkedListNode {
        int data;
        DoublyLinkedListNode next;
        DoublyLinkedListNode prev;

        DoublyLinkedListNode(int data) {
            this.data = data;
        }
    }

    public static DoublyLinkedListNode sortedInsert(DoublyLinkedListNode root, int data)    {
        DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);

        if (root == null) {
            return newNode;
        }
        DoublyLinkedListNode next = root;
        DoublyLinkedListNode prev = null;

        while (next != null && next.data < data) {
            prev = next;
            next = next.next;
        }

        if (prev != null) {
            prev.next = newNode;
        }
        if (next != null) {
            next.prev = newNode;
        }
        newNode.prev = prev;
        newNode.next = next;

        if (newNode.data < root.data) {
            return newNode;
        }

        return root;
    }
}
