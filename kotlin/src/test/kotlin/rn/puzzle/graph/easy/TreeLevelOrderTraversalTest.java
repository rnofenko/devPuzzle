package rn.puzzle.graph.easy;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class TreeLevelOrderTraversalTest {

    @Test
    public void test() {
        Node root = new Node(5);
        root.left = new Node(3);
        root.right = new Node(9);
        root.left.right = new Node(4);

        Assert.assertEquals("5 3 9 4", bfs(root));
    }

    private static String bfs(Node root) {
        StringBuilder str = new StringBuilder();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
            str.append(" ").append(node.data);
        }

        return str.toString().trim();
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
