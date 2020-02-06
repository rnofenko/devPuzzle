package rn.puzzle.dynamic.hard.cherry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CherryPickupSolver {
    public static int cherryPickup(int[][] grid) {
        CherryPickupSolver solver = new CherryPickupSolver(grid);
        return solver.solve();
    }

    private final List<GraphBuilder.Node> graph;

    private CherryPickupSolver(int[][] grid) {
        GraphBuilder builder = new GraphBuilder(grid);
        graph = builder.build();
    }

    private int solve() {
        setVertexLevel();
        removeLongEdges();
        return 0;
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
