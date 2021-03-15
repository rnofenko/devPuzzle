package rn.puzzle.bit.easy;

import org.junit.Assert;
import org.junit.Test;

public class FourthBitTests {
    public int fourthBit(int dec) {
        String s = Integer.toBinaryString(dec);
        if (s.length() < 4) {
            return 0;
        }
        return s.charAt(s.length() - 4) - 48;
    }

    @Test
    public void test1() {
        Assert.assertEquals(1, fourthBit(255));
        Assert.assertEquals(0, fourthBit(256));
        Assert.assertEquals(0, fourthBit(1));
    }
}
