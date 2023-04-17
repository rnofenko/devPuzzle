package rn.puzzle.wip;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class JohnDeereTest {
    @Test
    public void test1() {
        List<Integer> res = findCompletePrefixes(Arrays.asList("123", "1234", "23456"), Arrays.asList("1", "12", "123", "1234"));
        Assert.assertEquals(res.get(0), Integer.valueOf(2));
        Assert.assertEquals(res.get(1), Integer.valueOf(2));
        Assert.assertEquals(res.get(2), Integer.valueOf(1));
        Assert.assertEquals(res.get(3), Integer.valueOf(0));
    }

    private List<Integer> findCompletePrefixes(List<String> names, List<String> queries) {
        List<Integer> result = new ArrayList<>();
        Node root = buildTrie(names);
        for (String query : queries) {
            result.add(find(root, query));
        }
        return result;
    }

    private int find(Node node, String query) {
        for (int i = 0; i < query.length(); i++) {
            node = node.get(query.charAt(i));
            if (node == null) {
                return 0;
            }
        }
        return node.wordsCount;
    }

    private Node buildTrie(List<String> names) {
        Node root = new Node(' ');

        for (String name : names) {
            addToTrie(root, name);
        }

        return root;
    }

    private void addToTrie(Node node, String name) {
        node.wordsCount++;
        for (int i = 0; i < name.length(); i++) {
            node = node.add(name.charAt(i));
            if (i + 1 < name.length()) {
                node.wordsCount++;
            }
        }
    }

    private static class Node {
        char c;
        int wordsCount;
        private final Map<Character, Node> kids = new HashMap<>();

        Node(char c) {
            this.c = c;
        }

        Node add(char c) {
            Node kid = kids.get(c);
            if (kid == null) {
                kid = new Node(c);
                this.kids.put(c, kid);
            }

            return kid;
        }

        Node get(char c) {
            return kids.get(c);
        }
    }
}
