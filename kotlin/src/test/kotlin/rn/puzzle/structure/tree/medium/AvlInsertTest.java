package rn.puzzle.structure.tree.medium;

import org.junit.Assert;
import org.junit.Test;

public class AvlInsertTest {

    static Node insert(Node root, int val) {
        if (root == null) {
            root = new Node();
            root.val = val;
            return root;
        }
        if (root.val > val) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }

        if (isLeftLonger(root)) {
            root = rotateRight(root);
        } else if (isRightLonger(root)) {
            root = rotateLeft(root);
        }
        updateHeight(root);
        return root;
    }

    private static boolean isLeftLonger(Node root) {
        int leftHt = root.left == null ? -1 : root.left.ht;
        int rightHt = root.right == null ? -1 : root.right.ht;
        return leftHt - rightHt > 1;
    }

    private static boolean isRightLonger(Node root) {
        int leftHt = root.left == null ? -1 : root.left.ht;
        int rightHt = root.right == null ? -1 : root.right.ht;
        return rightHt - leftHt > 1;
    }

    private static void updateHeight(Node root) {
        if (root == null) {
            return;
        }
        int leftHt = root.left == null ? -1 : root.left.ht;
        int rightHt = root.right == null ? -1 : root.right.ht;
        root.ht = Math.max(leftHt, rightHt) + 1;
    }

    private static Node rotateRight(Node root) {
        if (root.left.left == null && root.left.right != null) {
            Node left = root.left;
            root.left = left.right;
            root.left.left = left;
            left.right = null;
            updateHeight(left);
        }

        Node left = root.left;
        Node leftRight = left.right;
        left.right = root;
        root.left = leftRight;
        updateHeight(left.left);
        updateHeight(left.right);
        return left;
    }

    private static Node rotateLeft(Node root) {
        if (root.right.right == null && root.right.left != null) {
            Node right = root.right;
            root.right = right.left;
            root.right.right = right;
            right.left = null;
            updateHeight(right);
        }

        Node newRoot = root.right;
        Node rightLeft = newRoot.left;
        newRoot.left = root;
        root.right = rightLeft;
        updateHeight(newRoot.left);
        updateHeight(newRoot.right);
        return newRoot;
    }

    @Test
    public void test8() {
        Node root = insertMany(3, 1, 2);
        Assert.assertEquals(2, root.val);
        Assert.assertEquals(1, root.ht);
    }

    @Test
    public void test7() {
        Node root = insertMany(1, 3, 2);
        Assert.assertEquals(2, root.val);
        Assert.assertEquals(1, root.ht);
        Assert.assertNotNull(root.left);
    }

    @Test
    public void test6() {
        Node root = insertMany(1, 2, 3);
        Assert.assertEquals(2, root.val);
        Assert.assertEquals(1, root.ht);
        Assert.assertNotNull(root.left);
    }

    @Test
    public void test5() {
        Node root = insertMany(3, 2, 1);
        Assert.assertEquals(2, root.val);
        Assert.assertEquals(1, root.ht);
    }

    @Test
    public void test4() {
        Node root = insertMany(3, 2, 4, 5);
        Assert.assertEquals(root.ht, 2);
    }

    @Test
    public void test3() {
        Node root = insertMany(3, 2, 4);
        Assert.assertEquals(root.ht, 1);
        Assert.assertNotNull(root.right);
        Assert.assertEquals(root.right.val, 4);
    }

    @Test
    public void test2() {
        Node root = insertMany(3, 2);
        Assert.assertEquals(root.ht, 1);
        Assert.assertNotNull(root.left);
        Assert.assertEquals(root.left.val, 2);
    }

    @Test
    public void test1() {
        Node root = insert(null, 3);
        Assert.assertNotNull(root);
        Assert.assertEquals(root.val, 3);
        Assert.assertEquals(root.ht, 0);
    }

    private Node insertMany(int ...vals) {
        Node root = null;
        for (int val : vals) {
            root = insert(root, val);
        }
        return root;
    }

    private static class Node {
        public int val;
        public int ht;
        public Node left;
        public Node right;

        @Override
        public String toString() {
            return val + " " + ht;
        }
    }
}
