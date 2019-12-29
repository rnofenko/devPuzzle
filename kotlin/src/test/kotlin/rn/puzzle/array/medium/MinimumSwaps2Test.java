package rn.puzzle.array.medium;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.StringToArrayConverter;

public class MinimumSwaps2Test {

    @Test
    public void test0() {
        solve("4 3 1 2", 3);
        //4 3 1 2
        //1 3 4 2
        //1 2 4 3
        //1 2 3 4
    }

    @Test
    public void test1() {
        solve("2 3 4 1 5", 3);
        //2 3 4 1 5
        //1 3 4 2 5
        //1 2 4 3 5
        //1 2 3 4 5
    }

    @Test
    public void test2() {
        solve("1 3 5 2 4 6 7", 3);
        //1 3 5 2 4 6 7
        //1 2 5 3 4 6 7
        //1 2 3 5 4 6 7
        //1 2 3 4 5 6 7
    }

    @Test
    public void test3() {
        solve("7 1 3 2 4 5 6", 5);
        //7 1 3 2 4 5 6
        //1 7 3 2 4 5 6
        //1 2 3 7 4 5 6
        //1 2 3 4 7 5 6
        //1 2 3 4 5 7 6
        //1 2 3 4 5 6 7
    }

    private int minimumSwaps(int[] arr) {
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0 && arr[i] != i + 1) {
                total += countGraphNodes(arr, i);
            }
        }
        return total;
    }

    private int countGraphNodes(int[] arr, int i) {
        int count = 0;
        while (arr[i] > 0 && arr[i] != i + 1) {
            int next = arr[i] - 1;
            arr[i] = 0;
            count++;

            i = next;
        }

        return count - 1;
    }

    private void solve(String inputStr, int expected) {
        int[] a = StringToArrayConverter.INSTANCE.stringToIntArray(inputStr);
        int res = minimumSwaps(a);
        Assert.assertEquals(expected, res);
    }
}
