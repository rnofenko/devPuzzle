import java.util.ArrayList;
import java.util.Arrays;

public class Board {

    private static final int NONE = -1;
    private final int size;
    private final int lastIndex;
    private final int[][] tiles;
    private int hamming;
    private int manhattan;

    public Board(int[][] tiles) {
        if (tiles == null) {
            throw new IllegalArgumentException();
        }

        this.tiles = copyTiles(tiles);
        size = tiles.length;
        lastIndex = size - 1;
        hamming = NONE;
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

    public int hamming() {
        if (hamming == NONE) {
            hamming = calcHamming();
        }
        return hamming;
    }

    public int manhattan() {
        if (manhattan == NONE) {
            manhattan = calcManhattan();
        }
        return manhattan;
    }

    private int calcHamming() {
        int incorrectCount = 0;
        int i = 0;
        for (int r = 0; r < size; r++) {

            int[] row = tiles[r];
            for (int c = 0; c < size; c++) {
                i++;
                int v = row[c];
                if (v > 0 && v != i) {
                    incorrectCount++;
                }
            }
        }

        return incorrectCount;
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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Board board = (Board) obj;

        if (size != board.size) return false;
        if (manhattan() != board.manhattan()) return false;

        for (int r = 0; r < size; r++) {
            if (!Arrays.equals(tiles[r], board.tiles[r])) {
                return false;
            }
        }

        return true;
    }

    public Iterable<Board> neighbors() {
        Coord zero = getZero();
        ArrayList<Board> boards = new ArrayList<>();
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

    public Board twin() {
        Coord from = new Coord(0, tiles[0][0] == 0 ? 1 : 0);
        Coord to = from.getRight();
        if (to.col >= size || tiles[to.row][to.col] == 0) {
            to = from.getBottom();
        }

        return copyAndSwap(from, to);
    }

    private Board copyAndSwap(Coord c1, Coord c2) {
        Board copy = copy();
        copy.tiles[c1.row][c1.col] = tiles[c2.row][c2.col];
        copy.tiles[c2.row][c2.col] = tiles[c1.row][c1.col];
        return copy;
    }

    private Board copy() {
        int[][] newTiles = copyTiles(tiles);
        return new Board(newTiles);
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
