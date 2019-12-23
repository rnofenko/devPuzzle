package rn.standard.sorting;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class QuicksortSorterTest {
    private QuicksortSorter sorter = new QuicksortSorter();

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

    private void test(int[] values) {
        int[] cloned = values.clone();
        Arrays.sort(cloned);

        sorter.sort(values);

        Assert.assertArrayEquals(cloned, values);
    }
}
