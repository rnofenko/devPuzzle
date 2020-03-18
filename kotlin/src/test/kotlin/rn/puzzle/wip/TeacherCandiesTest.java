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

    @Test
    public void test6() {
        test("[1,2,8,4,2]", 5);
        //1 2 8 4  2 3  2  1
        //0 1 2 0 -1 0 -1 -2
        //           3  2  1
    }

    private long candies(int n, int[] arr) {
        long total = 0;
        int startIndex = 0;
        int min = 0;

        int[] changes = new int[n];
        int prev = arr[0];
        for (int i = 1; i < n; i++) {
            int current = arr[i];
            if (current != prev) {
                changes[i] = changes[i - 1] + (current > prev ? 1 : -1);
                min = Math.min(min, changes[i]);
            } else {
                total += calc(changes, startIndex, i - 1, min);
                min = 0;
                startIndex = i;
            }

            prev = current;
        }

        total += calc(changes, startIndex, n - 1, min);

        return total;
    }

    private long calc(int[] changes, int startIndex, int endIndex, int min) {
        long sum = 0;

        for (int i = startIndex; i <= endIndex; i++) {
            int candies = changes[i] - min + 1;
            sum += candies;
        }

        return sum;
    }

    private void test(String str, long expected) {
        int[] a = StringToArrayConverter.INSTANCE.stringToIntArray(str);
        long res = candies(a.length, a);
        Assert.assertEquals(expected, res);
    }
}
