package rn.puzzle.graph.easy;

import org.junit.Assert;
import org.junit.Test;

public class BinarySearchTreeInsertionTest {

    @Test
    public void test1() {
        Node root = insert(null, 4);
        root = insert(root, 2);
        root = insert(root, 3);
        root = insert(root, 1);
        root = insert(root, 7);
        root = insert(root, 6);

        Assert.assertEquals(2, root.left.data);
    }

    private static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }
        if (data > root.data) {
            root.right = insert(root.right, data);
        } else {
            root.left = insert(root.left, data);
        }
        return root;
    }

    private static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}
