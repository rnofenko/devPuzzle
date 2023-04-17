package rn.coursera.algtool.w4;

import org.junit.Test;
import rn.tool.Ass;
import rn.tool.StrConverter;

import java.util.Arrays;

public class QuickSortTest {
    @Test
    public void test1() {
        run("4 5 2 8 3 1");
    }

    @Test
    public void test2() {
        run("2 1 3 3 3 2 1 3 1 1 2 3 1 2 3");
    }

    private void run(String input) {
        int[] res = StrConverter.toIntArray(input);
        QuickSort.quickSort(res);
        int[] cloned = res.clone();
        Arrays.sort(cloned);
        Ass.assertIntArrayEquals(cloned, res);
    }
}
