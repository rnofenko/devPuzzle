package rn.puzzle.bit.easy;

import org.junit.Assert;
import org.junit.Test;

public class PowerOfFourTest {
    public boolean isPowerOfFour(int num) {
        String s = Integer.toBinaryString(num);
        int len = s.length() - 1;
        return s.startsWith("1") && len % 2 == 0 && s.substring(1).replaceAll("0", "").length() == 0;
    }

    @Test
    public void test1() {
        Assert.assertFalse(isPowerOfFour(0));
        Assert.assertTrue(isPowerOfFour(1));
        Assert.assertTrue(isPowerOfFour(4));
    }
}
