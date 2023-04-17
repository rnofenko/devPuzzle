package rn.coursera.algtool.w2;

import org.junit.Assert;
import org.junit.Test;

public class LeastCommonMultipleTest {
    @Test
    public void test1() {
        Assert.assertEquals(24, LeastCommonMultiple.findLcm(12,8));
    }
}
