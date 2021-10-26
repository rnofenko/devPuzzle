package rn.puzzle.dynamic.medium;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.Stopwatch;

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
        Long[][] memo = new Long[2][];
        memo[0] = new Long[n + 1];
        memo[1] = new Long[n + 1];
        for (int i = 1; i < n; i += 100) {
            calc(i, 0, memo);
        }
        return (int)(calc(n, 0, memo) % 1000000007);
    }

    public static long calc(int n, int conf, Long[][] memo) {
        if (n < 1) {
            return 0;
        }
        if (memo[conf][n] != null) {
            return memo[conf][n];
        }

        long total = 0;
        Tile[] queue = new Tile(0, conf).next();
        for (Tile tile : queue) {
            if (tile.conf == 0 && tile.len == n) {
                total++;
                continue;
            }
            total += calc(n - tile.len, tile.conf, memo);
            total = total % 1000000007;
        }

        memo[conf][n] = total;
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
                    new Tile(len + 1, 1)
            };
        }

        @Override
        public String toString() {
            return len + " " + conf;
        }
    }
}
