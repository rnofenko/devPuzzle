package rn.standard.sorting;

import org.junit.Test;

public class RadixMsdStringSorterTest {
    private final RadixMsdStringSorter sorter = new RadixMsdStringSorter();

    @Test
    public void test1() {
        String[] a = new String[] { "hello", "cat", "hell", "help", "hat", "dog", "height", "ham" };
        sorter.sort(a);
    }
}
