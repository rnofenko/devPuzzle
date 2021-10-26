package rn.puzzle.graph.medium;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.StrConverter;

import java.util.*;

//https://leetcode.com/problems/network-delay-time/
public class NetworkDelayTimeTest {
    private final static int SOURCE = 0;
    private final static int TARGET = 1;
    private final static int TIME = 2;

    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : times) {
            graph.compute(edge[SOURCE], (key, value) -> {
                if (value == null) {
                    value = new ArrayList<>();
                }
                value.add(edge);
                return value;
            });
        }

        Integer[] nodeTimes = new Integer[n + 1];
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> nodeTimes[o1] - nodeTimes[o2]);
        nodeTimes[k] = 0;
        heap.add(k);
        boolean[] visited = new boolean[n + 1];

        int visitedCount = 0;

        while (!heap.isEmpty()) {
            visitedCount++;
            int source = heap.poll();
            visited[source] = true;
            int sourceTime = nodeTimes[source];
            List<int[]> edges = graph.get(source);
            if (edges == null) {
                continue;
            }
            for (int[] edge : edges) {
                int target = edge[TARGET];
                if (visited[target]) {
                    continue;
                }

                int newTime = sourceTime + edge[TIME];
                Integer old = nodeTimes[target];
                nodeTimes[target] = (old == null || old > newTime) ? newTime : old;


                heap.remove(target);
                heap.add(target);
            }
        }

        if (visitedCount != n) {
            return -1;
        }
        int max = nodeTimes[k];
        for (int i = 1; i < nodeTimes.length; i++) {
            max = Math.max(max, nodeTimes[i]);
        }
        return max;
    }

    @Test
    public void test6() {
        runTest("[[4,2,76],[1,3,79],[3,1,81],[4,3,30],[2,1,47],[1,5,61],[1,4,99],[3,4,68],[3,5,46],[4,1,6],[5,4,7],[5,3,44],[4,5,19],[2,3,13],[3,2,18],[1,2,0],[5,1,25],[2,5,58],[2,4,77],[5,2,74]]", 5, 3, 59);
    }

    @Test
    public void test5() {
        runTest("[[1,2,1],[2,3,2],[1,3,4]]", 3, 1, 3);
    }

    @Test
    public void test1() {
        runTest("[[2,1,1],[2,3,1],[3,4,1]]", 4, 2, 2);
    }

    @Test
    public void test2() {
        runTest("[[1,2,1]]", 2, 1, 1);
    }

    @Test
    public void test3() {
        runTest("[[1,2,1]]", 2, 2, -1);
    }

    @Test
    public void test4() {
        runTest("[[1,2,1],[2,1,3]]", 2, 2, 3);
    }

    private void runTest(String a2, int n, int k, int expected) {
        int[][] a = StrConverter.to2dIntArray(a2);
        Assert.assertEquals(expected, networkDelayTime(a, n, k));
    }
}
