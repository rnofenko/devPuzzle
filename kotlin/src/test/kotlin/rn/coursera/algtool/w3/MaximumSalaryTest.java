package rn.coursera.algtool.w3;

import org.junit.Assert;
import org.junit.Test;

public class MaximumSalaryTest {
    @Test
    public void test1() {
        String res = MaximumSalary.findMax(new String[]{"32", "3", "22", "35"});
        Assert.assertEquals("3533222", res);
    }

    @Test
    public void test2() {
        String res = MaximumSalary.findMax(new String[]{"32", "3", "9", "91", "22", "35"});
        Assert.assertEquals("9913533222", res);
    }

    @Test
    public void test3() {
        String res = MaximumSalary.findMax(new String[]{"32", "3", "9", "91", "33", "22", "35"});
        Assert.assertEquals("991353333222", res);
    }

    @Test
    public void test4() {
        String res = MaximumSalary.findMax(new String[]{"1000", "100", "10"});
        Assert.assertEquals("101001000", res);
    }

    @Test
    public void test5() {
        String res = MaximumSalary.findMax(new String[]{"5", "0", "10"});
        Assert.assertEquals("5100", res);
    }

    @Test
    public void test6() {
        String res = MaximumSalary.findMax(new String[]{"0"});
        Assert.assertEquals("0", res);
    }

    @Test
    public void test7() {
        String res = MaximumSalary.findMax(new String[]{"1", "11", "12", "112"});
        Assert.assertEquals("12112111", res);
    }

    @Test
    public void test8() {
        String res = MaximumSalary.findMax(new String[]{"100", "10"});
        Assert.assertEquals("10100", res);
    }

    @Test
    public void test9() {
        String res = MaximumSalary.findMax(new String[]{"112", "11113", "1110"});
        Assert.assertEquals("112111131110", res);
    }

    @Test
    public void compareTest1() {
        Assert.assertEquals(1, MaximumSalary.numberCompare("123", "134"));
        Assert.assertEquals(0, MaximumSalary.numberCompare("123", "123"));
        Assert.assertEquals(-1, MaximumSalary.numberCompare("134", "123"));
    }
}
