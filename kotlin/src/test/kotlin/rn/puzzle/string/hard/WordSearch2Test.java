package rn.puzzle.string.hard;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

//https://leetcode.com/problems/word-search-ii/
public class WordSearch2Test {

    @Test
    public void test5() {
        char[][] board = new char[3][];
        board[0] = new char[] { 'a','b','c' };
        board[1] = new char[] { 'a','e','d' };
        board[2] = new char[] { 'a','f','g' };

        String[] words = new String[]{"abcdefg"};

        List<String> res = findWords(board, words);
        Assert.assertEquals(1, res.size());
    }

    @Test
    public void test4() {
        char[][] board = new char[4][];
        board[0] = new char[] { 'o','a','a','n' };
        board[1] = new char[] { 'e','t','a','e' };
        board[2] = new char[] { 'i','h','k','r' };
        board[3] = new char[] { 'i','f','l','v' };

        String[] words = new String[]{"oath", "pea", "eat", "rain", "hklf", "hf"};

        List<String> res = findWords(board, words);
        Assert.assertEquals(4, res.size());
    }

    @Test
    public void test3() {
        char[][] board = new char[1][];
        board[0] = new char[] { 'a','a' };

        String[] words = new String[]{"aaa"};

        List<String> res = findWords(board, words);
        Assert.assertEquals(0, res.size());
    }

    @Test
    public void test2() {
        char[][] board = new char[4][];
        board[0] = new char[] { 'o','a','b','n' };
        board[1] = new char[] { 'o','t','a','e' };
        board[2] = new char[] { 'a','h','k','r' };
        board[3] = new char[] { 'a','f','l','v' };

        String[] words = new String[]{"oa", "oaa"};

        List<String> res = findWords(board, words);
        Assert.assertEquals(2, res.size());
    }

    @Test
    public void test1() {
        char[][] board = new char[4][];
        board[0] = new char[] { 'o','a','a','n' };
        board[1] = new char[] { 'e','t','a','e' };
        board[2] = new char[] { 'i','h','k','r' };
        board[3] = new char[] { 'i','f','l','v' };

        String[] words = new String[]{"oath", "pea", "eat", "rain"};

        List<String> res = findWords(board, words);
        Assert.assertEquals(2, res.size());
    }

    public List<String> findWords(char[][] board, String[] words) {
        Node root = new Node('!', null);
        for (String word : words) {
            insertWord(word, root);
        }

        List<String> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        for (int y = 0; y < board.length; y++) {
            char[] line = board[y];
            for (int x = 0; x < line.length; x++) {
                while (true) {
                    List<String> newResult = tryNextNode(board, x, y, root, visited);
                    if (newResult.isEmpty()) {
                        break;
                    }
                    result.addAll(newResult);
                }
            }
        }

        return result;
    }

    private List<String> dfs(char[][] board, int x, int y, Node node, Set<Integer> visited) {
        List<String> result = new ArrayList<>();

        result.addAll(tryNextNode(board, x + 1, y, node, visited));
        result.addAll(tryNextNode(board, x, y + 1, node, visited));
        result.addAll(tryNextNode(board, x - 1, y, node, visited));
        result.addAll(tryNextNode(board, x, y - 1, node, visited));

        return result;
    }

    private List<String> tryNextNode(char[][] board, int x, int y, Node node, Set<Integer> visited) {
        List<String> result = new ArrayList<>();

        int height = board.length;
        int width = board[0].length;

        int coord = y * width + x;

        if (x < 0 || y < 0 || x >= width || y >= height || visited.contains(coord)) {
            return result;
        }

        Node newNode = node.find(board[y][x]);
        if (newNode != null) {
            visited.add(coord);
            if (newNode.isEnd) {
                result.add(removeWord(newNode));
            }
            result.addAll(dfs(board, x, y, newNode, visited));
        }
        visited.remove(coord);
        return result;
    }

    private String removeWord(Node node) {
        StringBuilder builder = new StringBuilder();
        node.isEnd = false;
        boolean stopDestroy = false;
        while (node.c != '!') {
            builder.append(node.c);

            if (node.isEnd) {
                stopDestroy = true;
            }
            if (!stopDestroy) {
                node.destroyIfEmpty();
            }
            node = node.parent;
        }
        return builder.reverse().toString();
    }

    private void insertWord(String word, Node root) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            node = node.add(word.charAt(i));
        }
        node.isEnd = true;
    }

    private static class Node {
        Node(char c, Node parent) {
            this.c = c;
            this.parent = parent;
        }

        boolean isEnd;
        char c;
        Node parent;
        Map<Character, Node> children = new HashMap<>();

        Node add(char charAt) {
            return children.merge(charAt, new Node(charAt, this), (n1, n2) -> n1);
        }

        Node find(char charAt) {
            return children.get(charAt);
        }

        void destroyIfEmpty() {
            if (children.isEmpty()) {
                parent.children.remove(c);
            }
        }
    }
}
