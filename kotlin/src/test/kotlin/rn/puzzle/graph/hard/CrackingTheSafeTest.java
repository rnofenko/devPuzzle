package rn.puzzle.graph.hard;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//https://leetcode.com/problems/cracking-the-safe/
public class CrackingTheSafeTest {
    public String crackSafe(int n, int k) {
        if (n == 1) {
            return IntStream.range(0, k).mapToObj(String::valueOf).collect(Collectors.joining());
        }
        String root = Stream.generate(() -> "0").limit(n - 1).collect(Collectors.joining());
        String result = dfs(root, new HashMap<>(), k);
        while (!result.endsWith(root + "0")) {
            result = result + "0";
        }
        return result;
    }

    public String crackSafeInt(int n, int k) {
        if (n == 1) {
            return IntStream.range(0, k).mapToObj(String::valueOf).collect(Collectors.joining());
        }
        String root = Stream.generate(() -> "0").limit(n - 1).collect(Collectors.joining());
        String result = dfsInt(0, new HashMap<>(), k, n);
        while (!result.endsWith(root + "0")) {
            result = result + "0";
        }
        return result;
    }

    private String dfsInt(int root, HashMap<Integer, LinkedList<Integer>> nodesMap, int k, int n) {
        LinkedList<Integer> nodes = nodesMap.get(root);
        if (nodes == null) {
            nodes = new LinkedList<>();
            int prefix = (root % (int)(Math.pow(10, n - 2))) * 10;
            for (int i = 0; i < k; i++) {
                nodes.add(prefix + i);
            }
            nodesMap.put(root, nodes);
        }

        String total = "";
        while (!nodes.isEmpty()) {
            int node = nodes.pop();
            String res = dfsInt(node, nodesMap, k, n);
            total =  total + res;
        }

        return total + (root % 10);
    }

    private String dfs(String root, HashMap<String, List<String>> nodesMap, int k) {
        List<String> nodes = nodesMap.get(root);
        if (nodes == null) {
            nodes = new ArrayList<>();
            String prefix = root.substring(1);
            for (int i = 0; i < k; i++) {
                nodes.add(prefix + i);
            }
            nodesMap.put(root, nodes);
        }

        String total = "";
        while (!nodes.isEmpty()) {
            String node = nodes.get(0);
            nodes.remove(0);
            String res = dfs(node, nodesMap, k);
            total =  total + res;
        }

        return total + root.charAt(root.length() - 1);
    }

    @Test
    public void test1() {
        Assert.assertEquals("01100", crackSafe(2, 2));
    }

    @Test
    public void test2() {
        Assert.assertEquals("0011101000", crackSafe(3, 2));
    }

    @Test
    public void test3() {
        Assert.assertEquals("01", crackSafe(1, 2));
    }

    @Test
    public void test3_1() {
        Assert.assertEquals("000", crackSafe(3, 1));
    }

    @Test
    public void test4_2() {
        Assert.assertEquals("0001111010110010000", crackSafe(4, 2));
    }

    @Test
    public void test4_2_int() {
        Assert.assertEquals("0001111010110010000", crackSafeInt(4, 2));
    }
}
