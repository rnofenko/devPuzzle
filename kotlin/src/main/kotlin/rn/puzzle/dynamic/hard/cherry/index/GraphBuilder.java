package rn.puzzle.dynamic.hard.cherry.index;

import rn.puzzle.dynamic.hard.cherry.GraphPrinter;

import java.util.ArrayList;
import java.util.List;

public class GraphBuilder {
    private static final int WALL = -1;
    private static final int CHERRY = 1;
    private static final int EMPTY = 0;
    private static final int SOURCE = 0;
    private static final int SINK = 1;

    private final int width;
    private final int height;
    private final int[][] grid;
    private final int fullLen;
    private final int[] visited;
    private int[] coordinateToVertex;

    public GraphBuilder(int[][] grid) {
        this.grid = grid;
        height = grid.length;
        width = grid[0].length;
        fullLen = width * height;
        visited = new int[fullLen];
        coordinateToVertex = new int[fullLen];

    }

    public void print() {
        List<List<Integer>> graph = build();
        GraphPrinter.printGrid(coordinateToVertex, width, height);
        GraphPrinter.printIndexedConnections(graph);
    }

    public List<List<Integer>> build() {
        List<List<Integer>> graph = new ArrayList<>();

        graph.add(new ArrayList<>());
        graph.add(new ArrayList<>());

        coordinateToVertex[fullLen - 1] = SINK;

        build(SOURCE, 1, graph);
        build(SOURCE, width, graph);

        return graph;
    }

    private void build(int source, int nextIdx, List<List<Integer>> graph) {
        int y = nextIdx / width;
        int x = nextIdx % width;

        if (grid[y][x] == WALL || (visited[nextIdx] > 0 && visited[nextIdx] == source + 1)) {
            return;
        }
        visited[nextIdx] = source + 1;

        int nextSource = source;

        if (grid[y][x] == CHERRY || nextIdx == fullLen - 1) {
            int targetVertex = coordinateToVertex[nextIdx];
            if (targetVertex == EMPTY) {
                targetVertex = graph.size();
                coordinateToVertex[nextIdx] = targetVertex;

                graph.get(source).add(targetVertex);
                graph.add(new ArrayList<>());

                nextSource = targetVertex;
            } else {
                graph.get(source).add(targetVertex);
                return;
            }
        }

        if (x + 1 < width) {
            build(nextSource, nextIdx + 1, graph);
        }
        if (y + 1 < height) {
            build(nextSource, nextIdx + width, graph);
        }
    }
}
