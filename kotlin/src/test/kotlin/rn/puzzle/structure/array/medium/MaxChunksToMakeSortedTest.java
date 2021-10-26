package rn.puzzle.structure.array.medium;

import org.junit.Assert;
import org.junit.Test;

public class MaxChunksToMakeSortedTest {

    @Test
    public void test1() {
        int res = maxChunksToSorted(new int[]{4, 3, 2, 1, 0});
        Assert.assertEquals(1, res);
    }

    @Test
    public void test2() {
        int res = maxChunksToSorted(new int[]{1,0,2,3,4});
        Assert.assertEquals(4, res);
    }

    @Test
    public void test3() {
        int res = maxChunksToSorted(new int[]{2,1,0,3,4});
        Assert.assertEquals(3, res);
    }

    @Test
    public void test4() {
        int res = maxChunksToSorted(new int[]{2,0,1});
        Assert.assertEquals(1, res);
    }

    public int maxChunksToSorted(int[] arr) {
        int max = 0;
        int chunks = 0;

        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
            if (max == i) {
                chunks++;
            }
        }

        return chunks;
    }
}
