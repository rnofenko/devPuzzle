package rn.standard.sorting;

import org.junit.Test;

public class CountSorterTest {
    private final CountSorter sorter = new CountSorter();

    @Test
    public void test() {
        int[] a = new int[] { 3,6,5,1,4,3,5,2,7,2,5,4 };
        sorter.sort(a);
    }
}
