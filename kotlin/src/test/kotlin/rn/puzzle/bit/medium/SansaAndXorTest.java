package rn.puzzle.bit.medium;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.StrConverter;

public class SansaAndXorTest {
    static int sansaXor(int[] arr) {
        int len = arr.length;
        if (len == 1) {
            return arr[0];
        }
        if (len == 2) {
            return 0;
        }

        int xor = 0;
        for (long i = 0; i < len; i++) {
            long left = i * (len - i);
            long right = len - i - 1;
            long total = left + right + 1;
            if (total % 2 == 1) {
                xor = xor ^ arr[(int)i];
            }
        }

        return xor;
    }

    @Test
    public void test1() {
        test("1 2 3", 2);
    }

    @Test
    public void test2() {
        test("4 5 7 5", 0);
    }

    @Test
    public void test3() {
        test("98 74 12", 110);
    }

    private void test(String arr, int expected) {
        int res = sansaXor(StrConverter.toIntArray(arr));
        Assert.assertEquals(expected, res);
    }
}
