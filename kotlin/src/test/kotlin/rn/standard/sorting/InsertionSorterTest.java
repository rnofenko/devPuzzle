package rn.standard.sorting;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.Rand;
import rn.tool.Stopwatch;

import java.util.Arrays;

public class InsertionSorterTest {
    private InsertionSorter sorter = new InsertionSorter();

    @Test
    public void test1() {
        int[] values = new int[] { 6,8,3,1,2,5 };
        test(values);
    }

    @Test
    public void test2() {
        int[] values = new int[] { 6,8,3,8,2,3,1,2,5,2,5 };
        test(values);
    }

    @Test
    public void test3() {
        int[] values = new int[] { 4,1,2,7,6,4,4,5,4,3,4,3,8,9 };
        test(values);
    }

    @Test
    public void test4() {
        int[] values = new int[] { 2,1,2,3,1,1,3,3,2,3,1,3,2,3 };
        test(values);
    }

    @Test
    public void test5() {
        int[] values = new int[] { 6,5,3,1,7,8,4,9,2,3 };
        test(values);
    }

    @Test
    public void performance1() {
        int count = 1000 * 50;
        performanceTest(count, 1);
        performanceTest(count, 2);
        performanceTest(count, 10);
        performanceTest(count, 100);
        performanceTest(count, 1000);
        performanceTest(count, 10000);
        performanceTest(count, 1000000);
    }

    private void test(int[] values) {
        int[] cloned = values.clone();
        Arrays.sort(cloned);

        sorter.sort(values);

        Assert.assertArrayEquals(cloned, values);
    }

    private void performanceTest(int count, int max) {
        int[] values = Rand.INSTANCE.newArray(count, max);
        performanceTest(values, String.valueOf(max));
    }

    private void performanceTest(int[] values, String name) {
        int[] cloned = values.clone();

        System.out.println();
        System.out.println(name);

        Stopwatch w = Stopwatch.Companion.start();
        Arrays.sort(cloned);
        w.show("STANDARD");

        w.start();
        sorter.sort(values);
        w.show("INSERTION");

        Assert.assertArrayEquals(cloned, values);
    }
}
