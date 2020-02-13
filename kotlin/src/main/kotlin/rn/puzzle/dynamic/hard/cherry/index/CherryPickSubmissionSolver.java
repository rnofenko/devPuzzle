package rn.puzzle.dynamic.hard.cherry.index;

import java.util.*;

public class CherryPickSubmissionSolver {
    private static final int SINK = 1;
    private static final int VISITED = 1;
    private static final int EMPTY = 0;
    private static final int SOURCE = 0;
    private static final int WALL = -1;
    private static final int CHERRY = 1;

    private List<List<Integer>> graph;
    private int[] sorted;

    public int cherryPickup(int[][] grid) {
        if (grid.length == 1) {
            return grid[0][0];
        }

        GraphBuilder builder = new GraphBuilder(grid);
        graph = builder.build();

        removeDoubleLinks();

        sorted = doTopologicalSort();

        int rootPoints = depthFirst();

        if (grid[0][0] == EMPTY) {
            rootPoints--;
        }
        if (grid[grid.length - 1][grid[0].length - 1] == EMPTY) {
            rootPoints--;
        }

        return Math.max(0, rootPoints);
    }

    private int[] doTopologicalSort() {
        Stack<Integer> stack = new Stack<>();
        int[] visited = new int[graph.size()];

        doTopologicalSort(0, stack, visited);

        int[] reverted = new int[graph.size()];
        int i = 0;
        while (!stack.isEmpty()) {
            reverted[i++] = stack.pop();
        }

        return reverted;
    }

    private void doTopologicalSort(int vertex, Stack<Integer> stack, int[] visited) {
        visited[vertex] = 1;

        for (int kid : graph.get(vertex)) {
            if (visited[kid] == 1) continue;
            doTopologicalSort(kid, stack, visited);
        }

        stack.push(vertex);
    }

    private int depthFirst() {
        int[] visited = new int[graph.size()];
        return depthFirst(SOURCE, visited, 1, 0);
    }

    private int depthFirst(int vertex, int[] visited, int level, int maxPoints) {
        visited[vertex] = VISITED;

        if (vertex == SINK) {
            int locPoints = calcLongestPath(visited);
            maxPoints = Math.max(locPoints + level, maxPoints);
        }

        for (int kid : graph.get(vertex)) {
            int locPoints = depthFirst(kid, visited, level + 1, maxPoints);
            maxPoints = Math.max(locPoints, maxPoints);
        }

        visited[vertex] = EMPTY;

        return maxPoints;
    }

    private int calcLongestPath(int[] visited) {
        int[] path = new int[graph.size()];
        for (int vertex : sorted) {
            int startPoint = path[vertex];
            for (int kid : graph.get(vertex)) {
                path[kid] = Math.max(path[kid], startPoint + 1 - visited[kid]);
            }
        }

        return path[SINK];
    }

    private void removeDoubleLinks() {
        removeDoubleLinks(SOURCE);
    }

    private void removeDoubleLinks(int parent) {
        List<Integer> kids = graph.get(parent);

        Set<Integer> forRemove = new HashSet<>();
        for (int kid : kids) {

            List<Integer> grandKids = graph.get(kid);
            if (kid != SINK && grandKids.isEmpty()) {
                forRemove.add(kid);
            }

            for (int grandKid : grandKids) {
                if (kids.contains(grandKid)) {
                    forRemove.add(grandKid);
                }
            }
        }

        for (Integer kid : forRemove) {
            kids.remove(kid);
        }

        for (int kid : kids) {
            removeDoubleLinks(kid);
        }
    }

    private static class GraphBuilder {
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

            if (grid[y][x] == CHERRY || nextIdx == fullLen - 1) {
                int targetVertex = coordinateToVertex[nextIdx];
                if (targetVertex == EMPTY) {
                    targetVertex = graph.size();
                    coordinateToVertex[nextIdx] = targetVertex;

                    graph.get(source).add(targetVertex);
                    graph.add(new ArrayList<>());

                    source = targetVertex;
                } else {
                    graph.get(source).add(targetVertex);
                    return;
                }
            }

            if (x + 1 < width) {
                build(source, nextIdx + 1, graph);
            }
            if (y + 1 < height) {
                build(source, nextIdx + width, graph);
            }
        }
    }
}
