package rn.coursera.algtool.w3;

import org.junit.Assert;
import org.junit.Test;

public class CarFuelingTest {
    @Test
    public void test1() {
        Assert.assertEquals(2, CarFueling.computeMinRefills(950, 400, new int[] {200, 375, 550, 750}));
    }

    @Test
    public void test2() {
        Assert.assertEquals(0, CarFueling.computeMinRefills(330, 400, new int[] {200, 375, 550, 750}));
    }

    @Test
    public void test3() {
        Assert.assertEquals(0, CarFueling.computeMinRefills(330, 400, new int[] {}));
    }

    @Test
    public void test4() {
        Assert.assertEquals(0, CarFueling.computeMinRefills(400, 400, new int[] {}));
    }

    @Test
    public void test5() {
        Assert.assertEquals(-1, CarFueling.computeMinRefills(6, 2, new int[] {2,2}));
    }
}
