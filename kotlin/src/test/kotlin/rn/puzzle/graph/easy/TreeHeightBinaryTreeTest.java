package rn.puzzle.graph.easy;

import java.util.ArrayList;

public class TreeHeightBinaryTreeTest {

    public static class Node {
        int data;
        Node left;
        Node right;
    }

    public static int height(Node root) {
        if (root == null) {
            return 0;
        }

        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(root);
        int level = -1;
        while(!nodes.isEmpty()) {
            level++;
            ArrayList<Node> children = new ArrayList<>();
            for (Node node : nodes) {
                if (node.left != null) children.add(node.left);
                if (node.right != null) children.add(node.right);
            }

            nodes = children;
        }

        return level;
    }
}
