package rn.puzzle.structure.trie.easy;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ImplementTrieTest {

    static class Trie {
        private static class Node {
            Node(char c) {
                this.c = c;
            }

            boolean isEnd;
            char c;
            Map<Character, Node> children = new HashMap<>();

            Node add(char charAt) {
                return children.merge(charAt, new Node(charAt), (n1, n2) -> n1);
            }

            Node find(char charAt) {
                return children.get(charAt);
            }
        }

        private final Node _root = new Node('!');

        public void insert(String word) {
            Node node = _root;
            for (int i = 0; i < word.length(); i++) {
                node = node.add(word.charAt(i));
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            Node node = findNode(word);
            return node != null && node.isEnd;
        }

        public boolean startsWith(String prefix) {
            Node node = findNode(prefix);
            return node != null;
        }

        private Node findNode(String word) {
            Node node = _root;
            for (int i = 0; i < word.length(); i++) {
                node = node.find(word.charAt(i));
                if (node == null) {
                    return null;
                }
            }
            return node;
        }
    }

    @Test
    public void test1() {
        Trie trie = new Trie();
        Assert.assertFalse(trie.search("hi"));
        trie.insert("hello");
        Assert.assertFalse(trie.search("hi"));
        Assert.assertFalse(trie.search("he"));
        Assert.assertTrue(trie.search("hello"));
        Assert.assertFalse(trie.startsWith("hello2"));
        Assert.assertTrue(trie.startsWith("hello"));
        Assert.assertTrue(trie.startsWith("he"));
    }
}
