package rn.puzzle.dynamic.medium;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.StrConverter;

//https://www.hackerrank.com/challenges/equal/problem
public class EqualCandiesTest {
    static int equal(int[] arr) {
        int min = arr[0];
        for (int n : arr) {
            min = min < n ? min : n;
        }

        int[] rest = new int[5];
        int total = 0;
        for (int v : arr) {
            v -= min;
            rest[v % 5]++;
            int operationsNo = v / 5;
            total += operationsNo;
        }

        int optimal = Integer.MAX_VALUE;
        int increase = 0;
        for(int i = 0; i < 4;i++) {
            optimal = Math.min(rest[1] + rest[2] + rest[3] * 2 + rest[4] * 2 + increase, optimal);
            shiftOne(rest);
            increase += rest[0];
        }

        return total + optimal;
    }

    private static void shiftOne(int[] a) {
        int last = a[a.length - 1];
        for (int i = a.length - 1; i > 0; i--) {
            a[i] = a[i - 1];
        }
        a[0] = last;
    }

    @Test
    public void test11() {
        test("1 5 5", 3);
    }

    @Test
    public void test13() {
        test("2 5 5 5 5 5", 6);
    }

    @Test
    public void test12() {
        test("1 5 5 10 10", 7);
    }

    @Test
    public void test12a() {
        test("1 5 5 5 5", 5);
    }

    @Test
    public void test3() {
        test("512 125 928 381 890 90 512 789 469 473 908 990 195 763 102 643 458 366 684 857 126 534 974 875 459 892 686 373 127 297 576 991 774 856 372 664 946 237 806 767 62 714 758 258 477 860 253 287 579 289 496", 5104);
    }

    @Test
    public void test1() {
        test("10 7 12", 3);
    }

    @Test
    public void test2() {
        test("2 2 3 7", 2);
    }

    private void test(String inputArr, int expected) {
        int res = equal(StrConverter.toIntArray(inputArr));
        Assert.assertEquals(expected, res);
    }
}
