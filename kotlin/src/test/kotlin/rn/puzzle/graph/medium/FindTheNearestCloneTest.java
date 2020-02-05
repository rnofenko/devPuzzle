package rn.puzzle.graph.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FindTheNearestCloneTest {

    @Test
    public void test1() {
        int res = findShortest(5, new int[] {1,1,2,3}, new int[] {2,3,4,5}, new long[] {1,2,3,3,2}, 2);
        assert (res == 3) :  res + " instead of 3";
    }

    @Test
    public void test2() {
        int res = findShortest(5, new int[] {1,1,2,3}, new int[] {2,3,4,5}, new long[] {1,2,3,3,2}, 1);
        assert (res == -1);
    }

    private int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, long[] colorIds, int colorId) {
        List<Integer>[] g = createAdjList(graphNodes, graphFrom, graphTo);
        int[] visited = new int[graphNodes];

        List<Integer> vertices = new ArrayList<>();
        for (int i = 0; i < colorIds.length; i++) {
            if (colorId == colorIds[i]) {
                vertices.add(i);
                visited[i] = i + 1;
            }
        }

        int round = 0;
        while (!vertices.isEmpty()) {
            BfsResult result = bfsRound(vertices, g, visited);
            if (result.meet) {
                return round * 2 + (result.earlyVisiting ? 1 : 2);
            }

            vertices = result.vertices;
            round++;
        }

        return -1;
    }

    private BfsResult bfsRound(List<Integer> vertices, List<Integer>[] g, int[] visited) {
        BfsResult result = new BfsResult();

        List<Integer> nextVertices = new ArrayList<>();
        boolean[] visitedPerRound = new boolean[visited.length];

        for (int vertex : vertices) {
            int rootVertex = visited[vertex];
            for (int child : g[vertex]) {
                if (rootVertex == visited[child]) continue;

                if (visited[child] > 0) {
                    if (!visitedPerRound[child]) {
                        result.earlyVisiting = true;
                    }
                    result.meet = true;
                } else {
                    nextVertices.add(child);
                }

                visitedPerRound[child] = true;
                visited[child] = rootVertex;
            }
        }

        result.vertices = nextVertices;
        return result;
    }

    private static class BfsResult {
        List<Integer> vertices;
        boolean earlyVisiting;
        boolean meet;
    }

    private List<Integer>[] createAdjList(int graphNodes, int[] graphFrom, int[] graphTo) {
        List<Integer>[] g = new List[graphNodes];
        for (int i = 0; i < graphFrom.length; i++) {
            int from = graphFrom[i] - 1;
            int to = graphTo[i] - 1;
            if (g[from] == null) g[from] = new ArrayList<>();
            if (g[to] == null) g[to] = new ArrayList<>();
            g[from].add(to);
            g[to].add(from);
        }
        return g;
    }
}
