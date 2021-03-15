package rn.puzzle.wip.dynamic;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.Stopwatch;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/domino-and-tromino-tiling/
public class DominoTrominoTilingTest {

    @Test
    public void test3() {
        Assert.assertEquals(5, numTilings(3));
    }

    @Test
    public void test4() {
        Assert.assertEquals(11, numTilings(4));
    }

    @Test
    public void test5() {
        Assert.assertEquals(24, numTilings(5));
    }

    @Test
    public void test100() {
        Assert.assertEquals(190242381, numTilings(100));
    }

    @Test
    public void test100000() {
        Assert.assertEquals(423425328, numTilings(20000));
    }

    @Test
    public void testAll() {
        Assert.assertEquals(3418626, numTilings(20));
        Assert.assertEquals(178424817, numTilings(25));
        Stopwatch w = Stopwatch.Companion.start();
        Assert.assertEquals(979232805, numTilings(1000));
        Assert.assertEquals(199751765, numTilings(2000));
        Assert.assertEquals(270082739, numTilings(5000));
        w.show("1000");
    }

    public int numTilings(int n) {
        Long[] memo = new Long[n + 1];
        for (int i = 0; i < n; i++) {
            calc(i, memo);
        }
        return (int)(calc(n, memo) % 1000000007);
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
            if (tile.conf == 0 && tile.len == n) {
                total++;
                continue;
            }

            if (tile.conf == 0 && tile.len > 0) {
                total += calc(n - tile.len, memo);
                total = total % 1000000007;
                continue;
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
                return new Tile[] {
                    new Tile(len + 1, 0),
                    new Tile(len + 2, 0),
                    new Tile(len + 1, 1),
                    new Tile(len + 1, 1)
                };
            }
            return new Tile[] {
                    new Tile(len + 2, 0),
                    new Tile(len + 2, 1),
                    new Tile(len + 3, 0)
            };
        }

        @Override
        public String toString() {
            return len + " " + conf;
        }
    }
}
