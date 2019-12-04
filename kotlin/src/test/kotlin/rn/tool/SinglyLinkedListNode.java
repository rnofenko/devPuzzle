package rn.tool;

import org.junit.Assert;

import java.util.List;

public class SinglyLinkedListNode {
    public int data;
    public SinglyLinkedListNode next;

    public SinglyLinkedListNode() { }

    public SinglyLinkedListNode(int data) { this.data = data; }

    @Override
    public String toString() {
        return Integer.toString(data);
    }

    public static SinglyLinkedListNode toList(List<Integer> numbers) {
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

    public static void assertEqual(List<Integer> expected, SinglyLinkedListNode node) {
        for (int v : expected) {
            org.junit.Assert.assertNotNull(node);
            Assert.assertEquals(v, node.data);
            node = node.next;
        }
    }
}
