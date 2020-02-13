package rn.puzzle.wip;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.StringToArrayConverter;

public class TeacherCandiesTest {

    @Test
    public void test1() {
        test("[4,6,4,5,6,2]", 10);
    }

    @Test
    public void test2() {
        test("[2,4,2,6,1,7,8,9,2,1]", 19);
    }

    @Test
    public void test3() {
        test("[2,4,3,5,2,6,4,5]", 12);
    }

    @Test
    public void test4() {
        test("[1,2,2]", 4);
    }

    @Test
    public void test5() {
        test("[1,1,1,1,1]", 5);
    }

    private long candies(int n, int[] arr) {
        return 0;
    }

    private void test(String str, long expected) {
        int[] a = StringToArrayConverter.INSTANCE.stringToIntArray(str);
        long res = candies(a.length, a);
        Assert.assertEquals(expected, res);
    }
}
