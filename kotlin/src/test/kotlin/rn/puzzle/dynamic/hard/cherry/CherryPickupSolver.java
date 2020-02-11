package rn.puzzle.dynamic.hard.cherry;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CherryPickupSolver {
    public static int cherryPickup(int[][] grid) {
        CherryPickupSolver solver = new CherryPickupSolver(grid);
        return solver.solve();
    }

    private static final int SOURCE = 0;
    private final List<GraphBuilder.Node> graph;
    private final int[][] grid;

    public CherryPickupSolver(int[][] grid) {
        this.grid = grid;
        GraphBuilder builder = new GraphBuilder(grid);
        graph = builder.build();
    }

    private int solve() {
        removeDoubleLinks();
        GraphBuilder.printConnections(graph);
        int rootPoints = graph.get(0).calcPoints() - 1;
        return rootPoints + grid[0][0] + grid[grid.length - 1][grid[0].length - 1];
    }

    private void removeDoubleLinks() {
        removeDoubleLinks(graph.get(SOURCE));
    }

    private void removeDoubleLinks(GraphBuilder.Node parent) {
        Set<Integer> kidsSet = new HashSet<>();
        for (GraphBuilder.Node kid : parent.kids) {
            kidsSet.add(kid.vertex);
        }

        Set<Integer> forRemove = new HashSet<>();
        for (GraphBuilder.Node kid : parent.kids) {
            for (GraphBuilder.Node grandKid : kid.kids) {
                if (kidsSet.contains(grandKid.vertex)) {
                    forRemove.add(grandKid.vertex);
                }
            }
        }

        parent.removeKids(forRemove);

        for (GraphBuilder.Node kid : parent.kids) {
            removeDoubleLinks(kid);
        }
    }

    private void removeLongEdges() {
        for (GraphBuilder.Node node : graph) {
            node.removeLongEdges();
        }
    }

    private void setVertexLevel() {
        Set<GraphBuilder.Node> nodes = new HashSet<>();
        nodes.add(graph.get(0));
        int level = 0;

        while (!nodes.isEmpty()) {
            Set<GraphBuilder.Node> newKids = new HashSet<>();
            for (GraphBuilder.Node node : nodes) {
                node.level = level;

                for (GraphBuilder.Node kid : node.kids) {
                    if (!nodes.contains(kid)) {
                        newKids.add(kid);
                    }
                }
            }

            level++;
            nodes = newKids;
        }
    }
}
