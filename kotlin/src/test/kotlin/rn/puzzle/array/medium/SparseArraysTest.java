package rn.puzzle.array.medium;

import org.junit.Test;
import rn.tool.Ass;

public class SparseArraysTest {

    @Test
    public void test1() {
        int[] res = matchingStrings(new String[]{"ab", "abc", "bd"}, new String[]{"a", "b", "c"});
        Ass.INSTANCE.assertIntArrayEquals(new int[] {0,0,0}, res);
    }

    @Test
    public void test2() {
        int[] res = matchingStrings(new String[]{"ab", "abc", "bd"}, new String[]{"ab", "bd", "c", "abd"});
        Ass.INSTANCE.assertIntArrayEquals(new int[] {1,1,0, 0}, res);
    }

    @Test
    public void test3() {
        int[] res = matchingStrings(new String[]{"ab", "abc", "bd"}, new String[]{"abd"});
        Ass.INSTANCE.assertIntArrayEquals(new int[] {0}, res);
    }

    @Test
    public void test4() {
        int[] res = matchingStrings(new String[]{"ab", "abc", "bd"}, new String[]{"abcd"});
        Ass.INSTANCE.assertIntArrayEquals(new int[] {0}, res);
    }

    private int[] matchingStrings(String[] strings, String[] queries) {
        Node root = buildTernarySearchTrie(strings);
        int[] result = new int[queries.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = findCount(root, queries[i], 0);
        }
        return result;
    }

    private int findCount(Node root, String query, int index) {
        char c = query.charAt(index);
        if (root == null) {
            return 0;
        }
        if (c < root.c) {
            return findCount(root.left, query, index);
        } else if (c > root.c) {
            return findCount(root.right, query, index);
        } else {
            if (query.length() > index + 1) {
                return findCount(root.middle, query, index + 1);
            }
            return root.count;
        }
    }

    private Node buildTernarySearchTrie(String[] strings) {
        Node root = null;
        for (String word : strings) {
            root = addLetter(root, word, 0);
        }
        return root;
    }

    private Node addLetter(Node root, String word, int index) {
        char c = word.charAt(index);
        if (root == null) {
            root = new Node(c);
        }
        if (c < root.c) {
            root.left = addLetter(root.left, word, index);
        } else if (c > root.c) {
            root.right = addLetter(root.right, word, index);
        } else {
            if (word.length() > index + 1) {
                root.middle = addLetter(root.middle, word, index + 1);
            } else {
                root.count++;
            }
        }
        return root;
    }

    private static class Node {
        char c;
        Node left;
        Node right;
        Node middle;
        int count;

        public Node(char c) {
            this.c = c;
        }
    }
}
