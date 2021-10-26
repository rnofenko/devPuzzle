package rn.puzzle.graph.medium;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.FileHelper;
import rn.tool.Stopwatch;
import rn.tool.StrConverter;

import java.util.*;

public class NumberOfRestrictedPathsFromFirstToLastNodeTest {
    @Test
    public void test4() {
        List<String> res = FileHelper.load("C:\\temp\\nodes.txt");
        runTest(Integer.parseInt(res.get(0)), res.get(1), 1);
    }

    @Test
    public void test5() {
        List<String> res = FileHelper.load("C:\\temp\\nodes2.txt");
        runTest(Integer.parseInt(res.get(0)), res.get(1), 13944179);
    }

    private static final long M = 1000000007;

    public int countRestrictedPaths(int n, int[][] edges) {
        Stopwatch w = Stopwatch.Companion.start();
        List<List<int[]>> graph = buildGraph(edges, n);
        w.show("buildGraph");

        w.start();
        int[] weights = buildWeights(n, graph);
        w.show("buildWeights");
        Long[] counts = new Long[n + 1];
        counts[n] = 1L;

        w.start();
        long res = calcCounts(1, graph, weights, counts);
        w.show("calcCounts");

        return (int)res;
    }

    private List<List<int[]>> buildGraph(int[][] edges, int n) {
        List<List<int[]>> graph = new ArrayList<>();
        while (graph.size() < n + 1) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int source = edge[0];
            int target = edge[1];
            int weight = edge[2];
            graph.get(source).add(new int[]{ target, weight });
            graph.get(target).add(new int[]{ source, weight });
        }

        return graph;
    }

    private long calcCounts(int source, List<List<int[]>> graph, int[] weights, Long[] counts) {
        if (counts[source] != null) {
            return counts[source];
        }

        List<int[]> edgesTo = graph.get(source);

        long sum = 0;
        int sourceWeight = weights[source];
        for (int[] edge : edgesTo) {
            int target = edge[0];
            if (sourceWeight > weights[target]) {
                long newCount = calcCounts(target, graph, weights, counts);
                sum = (sum + newCount) % M;
            }
        }

        counts[source] = sum;

        return sum;
    }

    private int[] buildWeights(int n, List<List<int[]>> graph) {
        boolean[] visited = new boolean[n + 1];
        int[] weights = new int[n + 1];
        Arrays.fill(weights, Integer.MAX_VALUE);

        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(x -> x[1]));
        weights[n] = 0;
        heap.add(new int[]{n, 0});

        while (!heap.isEmpty()) {
            int[] top = heap.poll();
            int source = top[0];
            if (visited[source]) {
                continue;
            }

            visited[source] = true;

            for (int[] edge : graph.get(source)) {
                int target = edge[0];
                if (visited[target]) {
                    continue;
                }

                int newWeight = weights[source] + edge[1];

                int oldWeight = weights[target];
                if (newWeight < oldWeight) {
                    weights[target] = newWeight;
                    heap.add(new int[]{ target, newWeight });
                }
            }
        }

        return weights;
    }

    @Test
    public void test3() {
        runTest(9, "[[6,2,35129],[3,4,99499],[2,7,43547],[8,1,78671],[2,1,66308],[9,6,33462],[5,1,48249],[2,3,44414],[6,7,44602],[1,7,14931],[8,9,38171],[4,5,30827],[3,9,79166],[4,8,93731],[5,9,64068],[7,5,17741],[6,3,76017],[9,4,72244]]", 6);
    }

    @Test
    public void test1() {
        runTest(5, "[[1,2,3],[1,3,3],[2,3,1],[1,4,2],[5,2,2],[3,5,1],[5,4,10]]", 3);
    }

    @Test
    public void test2() {
        runTest(7, "[[1,3,1],[4,1,2],[7,3,4],[2,5,3],[5,6,1],[6,7,2],[7,5,3],[2,6,4]]", 1);
    }

    private void runTest(int n, String arrayStr, int expected) {
        int[][] a = StrConverter.to2dIntArray(arrayStr);
        int res = countRestrictedPaths(n, a);
        Assert.assertEquals(expected, res);
    }
}
