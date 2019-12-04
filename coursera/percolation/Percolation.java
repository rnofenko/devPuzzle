/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.HashSet;
import java.util.Set;

public class Percolation {
    private static final int OPEN = 1;

    private final int[][] grid;
    private WeightedQuickUnionUF graph;
    private final int size;
    private boolean isPercolated;
    private Set<Integer> fullTreeIds;

    public Percolation(int n) {
        size = n;
        grid = new int[n][n];
    }

    public boolean isOpen(int row, int col) {
        return grid[row - 1][col - 1] == OPEN;
    }

    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            grid[row - 1][col - 1] = OPEN;
            graph = null;
        }
    }

    public boolean percolates() {
        buildGraph();
        return isPercolated;
    }

    public boolean isFull(int row, int col) {
        buildGraph();
        int id = graph.find((row - 1) * size + col - 1);
        return fullTreeIds.contains(id);
    }

    private void buildGraph() {
        if (graph != null) {
            return;
        }

        graph = new WeightedQuickUnionUF(size * size);
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (grid[r][c] == OPEN) {
                    if (r < size - 1 && grid[r + 1][c] == OPEN) {
                        graph.union(r * size + c, (r + 1) * size + c);
                    }
                    if (c < size - 1 && grid[r][c + 1] == OPEN) {
                        graph.union(r * size + c, r * size + c + 1);
                    }
                }
            }
        }

        int[] firstRow = grid[0];
        fullTreeIds = new HashSet<>();
        for (int c = 0; c < size; c++) {
            if (firstRow[c] == OPEN) {
                fullTreeIds.add(graph.find(c));
            }
        }

        isPercolated = false;
        int lastRowShift = (size - 1) * size;
        int[] lastRow = grid[size - 1];
        for (int c = 0; c < size; c++) {
            if (lastRow[c] == OPEN) {
                if (fullTreeIds.contains(graph.find(lastRowShift + c))) {
                    isPercolated = true;
                    break;
                }
            }
        }
    }

}
