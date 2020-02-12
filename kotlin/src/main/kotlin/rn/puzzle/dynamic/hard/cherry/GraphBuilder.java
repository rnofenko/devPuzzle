package rn.puzzle.dynamic.hard.cherry;

import java.util.ArrayList;
import java.util.List;

public class GraphBuilder {

    private static final int WALL = -1;
    private static final int CHERRY = 1;
    private static final int EMPTY = 0;

    private final int width;
    private final int height;
    private final int[][] grid;
    private final int fullLen;
    private final int[] visited;
    private int[] coordinateToVertex;
    private List<CherryNode> graph = new ArrayList<>();

    public GraphBuilder(int[][] grid) {
        this.grid = grid;
        height = grid.length;
        width = grid[0].length;
        fullLen = width * height;
        visited = new int[fullLen];
        coordinateToVertex = new int[fullLen];
    }

    public void print() {
        build();
        GraphPrinter.printGrid(coordinateToVertex, width, height);
        GraphPrinter.printConnections(graph);
    }

    public List<CherryNode> build() {
        CherryNode source = new CherryNode(0, 0, 0);
        graph.add(source);

        CherryNode sink = new CherryNode(1, width - 1, height - 1);
        coordinateToVertex[fullLen - 1] = sink.id;
        graph.add(sink);

        build(source, 1);
        build(source, width);

        return graph;
    }

    private void build(CherryNode source, int nextIdx) {
        int y = nextIdx / width;
        int x = nextIdx % width;

        if (grid[y][x] == WALL || (visited[nextIdx] > 0 && visited[nextIdx] == source.id + 1)) {
            return;
        }
        visited[nextIdx] = source.id + 1;

        if (grid[y][x] == CHERRY || nextIdx == fullLen - 1) {
            int targetVertex = coordinateToVertex[nextIdx];
            if (targetVertex == EMPTY) {
                targetVertex = graph.size();
                coordinateToVertex[nextIdx] = targetVertex;

                CherryNode newNode = new CherryNode(targetVertex, x, y);

                source.addKid(newNode);
                graph.add(newNode);

                source = newNode;
            } else {
                source.addKid(graph.get(targetVertex));
                return;
            }
        }

        if (x + 1 < width) {
            build(source, nextIdx + 1);
        }
        if (y + 1 < height) {
            build(source, nextIdx + width);
        }
    }
}
