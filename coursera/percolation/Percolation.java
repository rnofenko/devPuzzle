/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final boolean[][] openCells;
    private final boolean[][] fullCells;
    private int[] fullTreeIds;
    private int fullTreeIdsSize = 0;
    private final WeightedQuickUnionUF graph;
    private final int size;
    private boolean isPercolated;
    private int openSitesCount = 0;

    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException();
        }

        size = n;
        openCells = new boolean[n][n];
        fullCells = new boolean[n][n];
        fullTreeIds = new int[size];
        graph = createGraph();
    }

    public boolean isOpen(int row, int col) {
        if (row < 1 || col < 1 || row > size || col > size) {
            throw new IllegalArgumentException();
        }

        return openCells[row - 1][col - 1];
    }

    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }

        openSitesCount++;

        int r = row - 1;
        int c = col -1;

        openCells[r][c] = true;

        int center = r * size + c;
        if (r < size - 1 && openCells[r + 1][c]) {
            graph.union(center, center + size);
        }
        if (r > 0 && openCells[r - 1][c]) {
            graph.union(center, center - size);
        }
        if (c < size - 1 && openCells[r][c + 1]) {
            graph.union(center, center + 1);
        }
        if (c > 0 && openCells[r][c - 1]) {
            graph.union(center, center - 1);
        }

        updateCache();
    }

    public boolean percolates() {
        return isPercolated;
    }

    public boolean isFull(int row, int col) {
        int r = row - 1;
        int c = col - 1;
        if (!isOpen(row, col)) {
            return false;
        }
        if (fullCells[r][c]) {
            return true;
        }

        int id = graph.find(r * size + c);

        for (int i = 0; i < fullTreeIdsSize; i++) {
            if (fullTreeIds[i] == id) {
                fullCells[r][c] = true;
                return true;
            }
        }

        return false;
    }

    public int numberOfOpenSites() {
        return openSitesCount;
    }

    private WeightedQuickUnionUF createGraph() {
        return new WeightedQuickUnionUF(size * size);
    }

    private void updateCache() {
        fullTreeIdsSize = 0;
        int prevId = -1;
        boolean[] firstRow = openCells[0];
        for (int c = 0; c < size; c++) {
            if (!firstRow[c]) {
                continue;
            }
            int id = graph.find(c);
            if (prevId != id) {
                prevId = id;
                fullTreeIds[fullTreeIdsSize] = id;
                fullTreeIdsSize++;
            }
        }

        isPercolated = false;
        boolean[] lastRow = openCells[size - 1];
        for (int c = 0; c < size; c++) {
            if (lastRow[c]) {
                if (isFull(size, c + 1)) {
                    isPercolated = true;
                    break;
                }
            }
        }
    }

}
