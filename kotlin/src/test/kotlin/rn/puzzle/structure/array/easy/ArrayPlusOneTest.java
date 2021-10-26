package rn.puzzle.structure.array.easy;

import org.junit.Test;
import rn.tool.Ass;

public class ArrayPlusOneTest {
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        int i = len - 1;
        digits[i]++;

        while (digits[i] > 9) {
            digits[i] = 0;
            i--;
            if (i >= 0) {
                digits[i]++;
            } else {
                int[] a = new int[len + 1];
                System.arraycopy(digits, 0, a, 1, len);
                a[0] = 1;
                return a;
            }
        }

        return digits;
    }

    @Test
    public void test1() {
        Ass.assertIntArrayEquals(new int[] {1,2,4}, plusOne(new int[] {1,2,3}));
    }

    @Test
    public void test2() {
        Ass.assertIntArrayEquals(new int[] {1,3,0}, plusOne(new int[] {1,2,9}));
    }

    @Test
    public void test3() {
        Ass.assertIntArrayEquals(new int[] {1,0,0}, plusOne(new int[] {9,9}));
    }
}
