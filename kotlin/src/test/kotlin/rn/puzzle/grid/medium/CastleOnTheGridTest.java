package rn.puzzle.grid.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class CastleOnTheGridTest {

    @Test
    public void test1() {

        int res = minimumMoves(new String[]{
                "......",
                "......",
                "......",
                "......",
                "......",
                "......"},0, 0, 5, 5);
        Assert.assertEquals(2, res);
    }

    @Test
    public void test2() {

        int res = minimumMoves(new String[]{
                ".X.",
                ".X.",
                "..."},0, 0, 2, 0);
        Assert.assertEquals(3, res);
    }

    @Test
    public void test3() {

        int res = minimumMoves(new String[]{
                "......",
                ".XXX.X",
                "..X...",
                "X.X.XX",
                "X...X.",
                "..X..."},0, 0, 5, 5);
        Assert.assertEquals(5, res);
    }

    static int minimumMoves(String[] grid, int startX, int startY, int goalX, int goalY) {
        int rowsCount = grid.length;
        int colsCount = grid[0].length();
        int[][] map = new int[grid.length][grid[0].length()];
        Queue<Coord> queue = new LinkedList<>();
        queue.add(new Coord(startY, startX));
        map[startY][startX] = 1;
        while (!queue.isEmpty()) {
            Coord now = queue.poll();
            int points = map[now.r][now.c] + 1;
            for (int r = now.r + 1; r < rowsCount; r++) {
                if (!updateMap(grid, map, queue, new Coord(r, now.c), points)) break;
            }
            for (int r = now.r - 1; r >= 0; r--) {
                if (!updateMap(grid, map, queue, new Coord(r, now.c), points)) break;
            }
            for (int c = now.c + 1; c < colsCount; c++) {
                if (!updateMap(grid, map, queue, new Coord(now.r, c), points)) break;
            }
            for (int c = now.c - 1; c >= 0; c--) {
                if (!updateMap(grid, map, queue, new Coord(now.r, c), points)) break;
            }
        }
        return map[goalY][goalX] - 1;
    }

    private static boolean updateMap(String[] grid, int[][] map, Queue<Coord> queue, Coord coord, int points) {
        int r = coord.r;
        int c = coord.c;
        if (grid[r].charAt(c) != '.') {
            return false;
        }
        int currentPoints = map[r][c];
        if (currentPoints > 0 && currentPoints <= points) {
            return true;
        }
        map[r][c] = points;
        queue.add(coord);
        return true;
    }

    private static class Coord {
        int r;
        int c;

        public Coord(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
