/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final boolean[] openCells;
    private final int topCoord;
    private final int bootomCoord;
    private final WeightedQuickUnionUF fullGraph;
    private final WeightedQuickUnionUF percGraph;
    private int fullTreeId;
    private final int size;
    private boolean isPercolated;
    private int openSitesCount = 0;

    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException();
        }

        size = n;
        openCells = new boolean[size * size];
        topCoord = size * size;
        bootomCoord = topCoord + 1;
        fullGraph = createGraph(1);
        percGraph = createGraph(2);
    }

    public boolean isOpen(int row, int col) {
        if (row < 1 || col < 1 || row > size || col > size) {
            throw new IllegalArgumentException();
        }

        return openCells[row * size - size + col - 1];
    }

    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }

        openSitesCount++;

        int r = row - 1;
        int c = col -1;

        int coord = r * size + c;
        openCells[coord] = true;

        if (r == 0) {
            fullGraph.union(coord, topCoord);
            percGraph.union(coord, topCoord);
        }
        if (row == size) {
            percGraph.union(coord, bootomCoord);
        }

        if (r < size - 1 && openCells[coord + size]) {
            fullGraph.union(coord, coord + size);
            percGraph.union(coord, coord + size);
        }
        if (r > 0 && openCells[coord - size]) {
            fullGraph.union(coord, coord - size);
            percGraph.union(coord, coord - size);
        }
        if (c < size - 1 && openCells[coord + 1]) {
            fullGraph.union(coord, coord + 1);
            percGraph.union(coord, coord + 1);
        }
        if (c > 0 && openCells[coord - 1]) {
            fullGraph.union(coord, coord - 1);
            percGraph.union(coord, coord - 1);
        }

        fullTreeId = fullGraph.find(topCoord);
        isPercolated = percGraph.find(topCoord) == percGraph.find(bootomCoord);
    }

    public boolean percolates() {
        return isPercolated;
    }

    public boolean isFull(int row, int col) {
        if (!isOpen(row, col)) {
            return false;
        }

        int r = row - 1;
        int c = col - 1;
        int coord = r * size + c;

        return fullGraph.find(coord) == fullTreeId;
    }

    public int numberOfOpenSites() {
        return openSitesCount;
    }

    private WeightedQuickUnionUF createGraph(int extra) {
        return new WeightedQuickUnionUF(size * size + extra);
    }

}
