package rn.puzzle.dynamic.hard.cherry;

import java.util.List;

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
        return 0;
    }
}
