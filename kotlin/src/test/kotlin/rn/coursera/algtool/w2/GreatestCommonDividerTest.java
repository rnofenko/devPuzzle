package rn.coursera.algtool.w2;

import org.junit.Assert;
import org.junit.Test;

public class GreatestCommonDividerTest {
    @Test
    public void test1() {
        Assert.assertEquals(3, GreatestCommonDivider.findGcd(234, 411));
    }

    @Test
    public void test2() {
        Assert.assertEquals(1, GreatestCommonDivider.findGcd(7, 2));
    }

    @Test
    public void test3() {
        Assert.assertEquals(2, GreatestCommonDivider.findGcd(1000000002, 4));
    }
}
