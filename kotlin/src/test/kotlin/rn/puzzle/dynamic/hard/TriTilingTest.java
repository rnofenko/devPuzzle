package rn.puzzle.dynamic.hard;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

//https://open.kattis.com/problems/tritiling
public class TriTilingTest {

    @Test
    public void test2() {
        Assert.assertEquals(3, calc(2));
        Assert.assertEquals(0, calc(3));
        Assert.assertEquals(11, calc(4));
        Assert.assertEquals(0, calc(5));
        Assert.assertEquals(41, calc(6));
        Assert.assertEquals(0, calc(7));
        Assert.assertEquals(153, calc(8));
        Assert.assertEquals(2131, calc(12));
        Assert.assertEquals(29681, calc(16));
        Assert.assertEquals(413403, calc(20));
        Assert.assertEquals(1542841, calc(22));
        Assert.assertEquals(5757961, calc(24));
        Assert.assertEquals(21489003, calc(26));
    }

    @Test
    public void test10() {
        Assert.assertEquals(299303201, calc(30));
    }

    public static long calc(int n) {
        Long[] memo = new Long[n + 1];
        return calc(n, memo);
    }

    public static long calc(int n, Long[] memo) {
        if (memo[n] != null) {
            return memo[n];
        }

        long total = 0;
        Queue<Tile> queue = new LinkedList<>();
        queue.add(new Tile(0, 0));
        while (!queue.isEmpty()) {
            Tile tile = queue.poll();

            if (tile.len > n) {
                continue;
            }
            if (tile.conf == 0) {
                if (tile.len == n) {
                    total++;
                    continue;
                } else if (tile.len > 0) {
                    total += calc(n - tile.len, memo);
                    continue;
                }
            }

            for (Tile newTile : tile.next()) {
                queue.add(newTile);
            }
        }

        memo[n] = total;
        return total;
    }

    private static class Tile {
        int len;
        int conf;

        Tile(int len, int conf) {
            this.len = len;
            this.conf = conf;
        }

        Tile[] next() {
            if (conf == 0) {
                return new Tile[] { new Tile(len, 1), new Tile(len, 2) };
            }
            if (conf == 1) {
                return new Tile[] { new Tile(len + 1, 3) };
            }
            if (conf == 2) {
                return new Tile[] { new Tile(len + 1, 4), new Tile(len, 5) };
            }
            if (conf == 3) {
                return new Tile[] { new Tile(len + 1, 0), new Tile(len, 6) };
            }
            if (conf == 4) {
                return new Tile[] { new Tile(len + 1, 0), new Tile(len, 7) };
            }
            if (conf == 5) {
                return new Tile[] { new Tile(len + 2, 0) };
            }
            if (conf == 6) {
                return new Tile[] { new Tile(len + 1, 8) };
            }
            if (conf == 7) {
                return new Tile[] { new Tile(len + 1, 9) };
            }
            if (conf == 8) {
                return new Tile[] { new Tile(len + 1, 3) };
            }
            if (conf == 9) {
                return new Tile[] { new Tile(len + 1, 4) };
            }
            throw new RuntimeException("Not implemented");
        }

        @Override
        public String toString() {
            return len + " " + conf;
        }
    }

    public static void runFromConsole() {
        Long[] memo = new Long[31];
        Scanner scan = new Scanner(System.in);
        while (true) {
            int c = scan.nextInt();
            if (c == -1) {
                break;
            }
            System.out.println(calc(c, memo));
        }
    }
}
