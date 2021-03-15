package rn.puzzle.tree.medium;

public class IsThisBinarySearchTreeTest {
    boolean checkBST(Node root) {
        return checkBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    boolean checkBST(Node root, int min, int max) {
        if (root == null) {
            return true;
        }
        int data = root.data;
        if (data <= min || data >= max) {
            return false;
        }
        Node left = root.left;
        Node right = root.right;
        return checkBST(left, min, data) && checkBST(right, data, max);
    }

    private static class Node {
        int data;
        Node left;
        Node right;
    }
}
