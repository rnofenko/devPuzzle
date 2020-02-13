package rn.puzzle.dynamic.hard.cherry.node;

import rn.puzzle.dynamic.hard.cherry.GraphPrinter;
import rn.puzzle.dynamic.hard.cherry.ICherryPickSolver;
import rn.tool.Stopwatch;

import java.util.*;

public class CherryPickupSolver implements ICherryPickSolver {

    private static final int SOURCE = 0;
    private static final int SINK = 1;
    private static final int EMPTY = 0;

    private List<CherryNode> graph;
    private List<Integer> sorted;

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

        GraphPrinter.printConnections(graph);
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

    private List<Integer> doTopologicalSort() {
        Stack<Integer> stack = new Stack<>();
        int[] visited = new int[graph.size()];

        doTopologicalSort(0, stack, visited);

        List<Integer> reverted = new ArrayList<>();
        while (!stack.isEmpty()) {
            reverted.add(stack.pop());
        }

        return reverted;
    }

    private void doTopologicalSort(int vertex, Stack<Integer> stack, int[] visited) {
        visited[vertex] = 1;

        for (CherryNode cherryNode : graph.get(vertex).kids) {
            if (visited[cherryNode.id] == 1) continue;
            doTopologicalSort(cherryNode.id, stack, visited);
        }

        stack.push(vertex);
    }

    private int depthFirst() {
        int[] visited = new int[graph.size()];
        CherryNode root = graph.get(0);
        return depthFirst(root, visited, 1, 0);
    }

    private int depthFirst(CherryNode node, int[] visited, int level, int maxPoints) {
        int id = node.id;
        visited[id] = CherryNode.VISITED;

        if (node.id == SINK) {
            int locPoints = calcLongestPath(visited);
            maxPoints = Math.max(locPoints + level, maxPoints);
        }

        for (CherryNode kid : node.kids) {
            int locPoints = depthFirst(kid, visited, level + 1, maxPoints);
            maxPoints = Math.max(locPoints, maxPoints);
        }

        visited[id] = CherryNode.EMPTY;

        return maxPoints;
    }

    private int calcLongestPath(int[] visited) {
        int[] path = new int[graph.size()];
        for (int vertex : sorted) {
            int startPoint = path[vertex];
            for (CherryNode kid : graph.get(vertex).kids) {
                int kidId = kid.id;
                path[kidId] = Math.max(path[kidId], startPoint + 1 - visited[kidId]);
            }
        }

        return path[SINK];
    }

    private void removeDoubleLinks() {
        removeDoubleLinks(graph.get(SOURCE));
    }

    private void removeDoubleLinks(CherryNode parent) {
        Set<Integer> kidsSet = new HashSet<>();
        for (CherryNode kid : parent.kids) {
            kidsSet.add(kid.id);
        }

        Set<Integer> forRemove = new HashSet<>();
        for (CherryNode kid : parent.kids) {

            if (kid.id !=SINK && kid.kids.isEmpty()) {
                forRemove.add(kid.id);
            }

            for (CherryNode grandKid : kid.kids) {
                if (kidsSet.contains(grandKid.id)) {
                    forRemove.add(grandKid.id);
                }
            }
        }

        parent.removeKids(forRemove);

        for (CherryNode kid : parent.kids) {
            removeDoubleLinks(kid);
        }
    }
}
