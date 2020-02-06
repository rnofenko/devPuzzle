package rn.puzzle.dynamic.hard.cherry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GraphBuilder {

    private static final int WALL = -1;
    private static final int CHERRY = 1;

    private final int width;
    private final int height;
    private final int[][] grid;
    private final int fullLen;
    private final int[] visited;
    private int[] coordinateToVertex;
    private List<Node> graph = new ArrayList<>();

    public GraphBuilder(int[][] grid) {
        this.grid = grid;
        height = grid.length;
        width = grid[0].length;
        fullLen = width * height;
        visited = new int[fullLen];
        coordinateToVertex = new int[fullLen];
    }

    public List<Node> build() {
        Node source = new Node(0, 0, 0);
        graph.add(source);

        Node sink = new Node(1, width - 1, height - 1);
        coordinateToVertex[fullLen - 1] = sink.vertex;
        graph.add(sink);

        build(source, 1);
        build(source, width);

        return graph;
    }

    private void build(Node source, int nextIdx) {
        int y = nextIdx / width;
        int x = nextIdx % width;

        if (grid[y][x] == WALL || (visited[nextIdx] > 0 && visited[nextIdx] == source.vertex)) {
            return;
        }
        visited[nextIdx] = source.vertex;

        if (grid[y][x] == CHERRY || nextIdx == fullLen - 1) {
            int targetVertex = coordinateToVertex[nextIdx];
            if (targetVertex == 0) {
                targetVertex = graph.size();
                coordinateToVertex[nextIdx] = targetVertex;

                Node newNode = new Node(targetVertex, x, y);

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

    public static class Node {
        private final int vertex;
        private final int y;
        private final int x;
        public List<Node> kids = new ArrayList<>();
        public int level;

        public Node(int vertex, int x, int y) {
            this.vertex = vertex;
            this.y = y;
            this.x = x;
        }

        public void addKid(Node kid) {
            kids.add(kid);
        }

        @Override
        public String toString() {
            String kidsStr = kids.stream().map(k -> String.valueOf(k.vertex)).collect(Collectors.joining(","));
            return String.format("#%s %sx%s level=%s kids=%s", vertex, x, y, level, kidsStr);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return vertex == node.vertex;
        }

        @Override
        public int hashCode() {
            return vertex;
        }

        public void removeLongEdges() {
            List<Node> newKids = new ArrayList<>();
            for (Node kid : kids) {
                if (kid.level <= level + 1) {
                    newKids.add(kid);
                }
            }
            kids = newKids;
        }
    }
}
