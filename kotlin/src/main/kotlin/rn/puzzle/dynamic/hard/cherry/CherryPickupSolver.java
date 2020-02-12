package rn.puzzle.dynamic.hard.cherry;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CherryPickupSolver {

    private static final int SOURCE = 0;
    private static final int SINK = 1;
    private static final int EMPTY = 0;

    private List<CherryNode> graph;

    public int cherryPickup(int[][] grid) {
        if (grid.length == 1) {
            return grid[0][0];
        }

        GraphBuilder builder = new GraphBuilder(grid);
        graph = builder.build();

        removeDoubleLinks();
        GraphPrinter.printConnections(graph);

        int rootPoints = depthFirst();
        if (grid[0][0] == EMPTY) {
            rootPoints--;
        }
        if (grid[grid.length - 1][grid[0].length - 1] == EMPTY) {
            rootPoints--;
        }

        return Math.max(0, rootPoints);
    }

    private int depthFirst() {
        int[] visited = new int[graph.size()];
        CherryNode root = graph.get(0);
        return depthFirst(root, visited, 1, 0);
    }

    private int depthFirst(CherryNode node, int[] visited, int level, int maxPoints) {
        int id = node.id;
        visited[id] = 1;

        if (node.id == SINK) {
            CherryNode root = graph.get(0);
            int locPoints = root.calcPoints(visited);
            maxPoints = Math.max(locPoints + level, maxPoints);
        }

        for (CherryNode kid : node.kids) {
            int locPoints = depthFirst(kid, visited, level + 1, maxPoints);
            maxPoints = Math.max(locPoints, maxPoints);
        }

        visited[id] = 0;

        return maxPoints;
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
