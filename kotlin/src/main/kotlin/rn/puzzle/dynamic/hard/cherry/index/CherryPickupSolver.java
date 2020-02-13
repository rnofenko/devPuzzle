package rn.puzzle.dynamic.hard.cherry.index;

import rn.puzzle.dynamic.hard.cherry.GraphPrinter;
import rn.puzzle.dynamic.hard.cherry.ICherryPickSolver;
import rn.tool.Stopwatch;

import java.util.*;

public class CherryPickupSolver implements ICherryPickSolver {
    private static final int SINK = 1;
    private static final int VISITED = 1;
    private static final int EMPTY = 0;
    private static final int SOURCE = 0;

    private List<List<Integer>> graph;
    private int[] sorted;

    public int cherryPickup(int[][] grid) {
        Stopwatch w = Stopwatch.Companion.start();

        if (grid.length == 1) {
            return grid[0][0];
        }

        w.show("start");
        GraphBuilder builder = new GraphBuilder(grid);
        graph = builder.build();
        w.show("graph build");

        removeDoubleLinks();
        w.show("graph pruned");

        GraphPrinter.printIndexedConnections(graph);
        w.show("graph printed");

        sorted = doTopologicalSort();
        w.show("graph sorted");

        int rootPoints = depthFirst();
        w.show("depthFirst");

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
}
