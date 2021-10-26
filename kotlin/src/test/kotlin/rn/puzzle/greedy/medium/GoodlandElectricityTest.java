package rn.puzzle.greedy.medium;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.StrConverter;

public class GoodlandElectricityTest {

    @Test
    public void test1() {
        test(2, "0 1 1 1 1 0", 2);
    }

    @Test
    public void test2() {
        test(3, "0 1 1 1 0 0 0", -1);
    }

    static int pylons(int k, int[] arr) {
        int total = 0;
        int optimalIdx = k - 1;
        int lastAvailable = -1;
        int lastPlant = -1;
        for (int i = 0; i < arr.length; i++) {
            int v = arr[i];
            if (v == 1) {
                lastAvailable = i;
            }
            if (optimalIdx == i) {
                if (lastAvailable == -1) {
                    return -1;
                }
                total++;
                lastPlant = lastAvailable;
                optimalIdx = lastPlant + k * 2 - 1;
                lastAvailable = -1;
            }
        }
        if (lastPlant + k >= arr.length) {
            return total;
        }
        if (lastAvailable >= 0 && lastAvailable + k >= arr.length) {
            return total + 1;
        }
        return -1;
    }

    private void test(int k, String numbers, int expected) {
        int res = pylons(k, StrConverter.toIntArray(numbers));
        Assert.assertEquals(expected, res);
    }
}
