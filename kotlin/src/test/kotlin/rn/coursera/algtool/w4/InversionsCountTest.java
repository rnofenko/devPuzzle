package rn.coursera.algtool.w4;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.StrConverter;

public class InversionsCountTest {
    @Test
    public void test1() {
        run("2 3 9 2 9", 2);
    }

    @Test
    public void test2() {
        run(" 9 8 7 3 2 1", 15);
    }

    private void run(String input, int expected) {
        int[] a = StrConverter.toIntArray(input);
        Assert.assertEquals(expected, InversionsCount.getNumberOfInversions(a));
    }
}
