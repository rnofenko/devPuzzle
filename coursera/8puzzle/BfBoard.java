import java.util.ArrayList;
import java.util.Arrays;

public class BfBoard {
    private static final int NONE = -1;
    private final int size;
    private final int lastIndex;
    private final int[][] tiles;
    private int manhattan;
    private static int globalId;
    public int id;
    public BfBoard parent;

    public BfBoard(int[][] tiles, BfBoard parent) {
        this.parent = parent;

        id = ++globalId;

        this.tiles = copyTiles(tiles);
        size = tiles.length;
        lastIndex = size - 1;
        manhattan = NONE;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(size).append("\n");
        for (int[] tile : tiles) {
            for (int i : tile) {
                builder.append(String.format("%2s ", i));
            }

            builder.append("\n");
        }
        return builder.toString();
    }

    public int dimension() {
        return size;
    }

    public int manhattan() {
        if (manhattan == NONE) {
            manhattan = calcManhattan();
        }
        return manhattan;
    }

    private int calcManhattan() {
        int distance = 0;

        for (int r = 0; r < size; r++) {

            int[] row = tiles[r];
            for (int c = 0; c < size; c++) {

                int v = row[c] - 1;
                if (v < 0) {
                    continue;
                }

                int vr = v / size;
                int vc = v - vr * size;

                distance += Math.abs(r - vr);
                distance += Math.abs(c - vc);
            }
        }

        return distance;
    }

    public boolean isGoal() {
        return manhattan() == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BfBoard bfBoard = (BfBoard) o;

        return Arrays.deepEquals(tiles, bfBoard.tiles);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tiles);
    }

    public Iterable<BfBoard> neighbors() {
        Coord zero = getZero();
        ArrayList<BfBoard> boards = new ArrayList<>();
        if (zero.row > 0) {
            boards.add(copyAndSwap(zero, zero.getTop()));
        }
        if (zero.col > 0) {
            boards.add(copyAndSwap(zero, zero.getLeft()));
        }
        if (zero.row < lastIndex) {
            boards.add(copyAndSwap(zero, zero.getBottom()));
        }
        if (zero.col < lastIndex) {
            boards.add(copyAndSwap(zero, zero.getRight()));
        }

        return boards;
    }

    private BfBoard copyAndSwap(Coord c1, Coord c2) {
        BfBoard copy = copy();
        copy.tiles[c1.row][c1.col] = tiles[c2.row][c2.col];
        copy.tiles[c2.row][c2.col] = tiles[c1.row][c1.col];
        return copy;
    }

    private BfBoard copy() {
        int[][] newTiles = copyTiles(tiles);
        return new BfBoard(newTiles, this);
    }

    private int[][] copyTiles(int[][] source) {

        int[][] target = new int[source.length][];
        for (int i = 0; i < source.length; i++) {
            target[i] = source[i].clone();
        }

        return target;
    }

    private Coord getZero() {
        for (int r = 0; r < size; r++) {
            int[] row = tiles[r];
            for (int c = 0; c < size; c++) {
                if (row[c] == 0) {
                    return new Coord(r, c);
                }
            }
        }
        return null;
    }

    private static class Coord {
        private final int row;
        private final int col;

        public Coord(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Coord getTop() {
            return new Coord(row - 1, col);
        }

        public Coord getBottom() {
            return new Coord(row + 1, col);
        }

        public Coord getLeft() {
            return new Coord(row, col - 1);
        }

        public Coord getRight() {
            return new Coord(row, col + 1);
        }
    }
}
