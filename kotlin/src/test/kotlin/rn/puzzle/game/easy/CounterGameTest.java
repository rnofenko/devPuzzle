package rn.puzzle.game.easy;

import org.junit.Assert;
import org.junit.Test;

public class CounterGameTest {
    public static String counterGame(long n) {
        long cursor = 1L << 62;

        int turns = 0;
        while (n > 1) {
            if (n > cursor) {
                n -= cursor;
                turns++;
            } else if (n == cursor) {
                n = n >> 1;
                turns++;
            }

            cursor = cursor >> 1;
        }

        return turns % 2 == 1 ? "Louise" : "Richard";
    }

    @Test
    public void test1560834904() {
        Assert.assertEquals("Richard", counterGame(1560834904));
    }

    @Test
    public void test1768820483() {
        Assert.assertEquals("Richard", counterGame(1768820483));
    }

    @Test
    public void test1533726144() {
        Assert.assertEquals("Louise", counterGame(1533726144));
    }

    @Test
    public void test1620434450() {
        Assert.assertEquals("Richard", counterGame(1620434450));
    }

    @Test
    public void test132() {
        Assert.assertEquals("Louise", counterGame(132));
    }

    @Test
    public void test6() {
        Assert.assertEquals("Richard", counterGame(6));
    }
}
