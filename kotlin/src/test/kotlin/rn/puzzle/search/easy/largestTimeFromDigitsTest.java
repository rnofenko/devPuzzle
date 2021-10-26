package rn.puzzle.search.easy;

import org.junit.Assert;
import org.junit.Test;

public class largestTimeFromDigitsTest {
    @Test
    public void test1() {
        String res = largestTimeFromDigits(new int[] {1,2,3,4});
        Assert.assertEquals("23:41", res);
    }

    @Test
    public void test2() {
        String res = largestTimeFromDigits(new int[] {2,0,6,6});
        Assert.assertEquals("06:26", res);
    }

    public String largestTimeFromDigits(int[] arr) {
        int h1Limit = 2;
        while (h1Limit != -1) {
            String res = largestTimeFromDigits(arr.clone(), h1Limit);
            if (!res.equals("")) return res;
            h1Limit--;
        }
        return "";
    }

    private String largestTimeFromDigits(int[] arr, int h1Limit) {
        int h1 = getMax(arr, h1Limit);
        if (h1 == -1) return "";
        int h2 = h1 == 2 ? getMax(arr, 3) : getMax(arr, 9);
        if (h2 == -1) return "";
        int h3 = getMax(arr, 5);
        if (h3 == -1) return "";
        int h4 = getMax(arr, 9);
        if (h4 == -1) return "";

        return "" + h1 + h2 + ":" + h3 + h4;
    }

    private int getMax(int[] arr, int limit) {
        int max = -1;
        int idx = -1;
        for (int i = 0; i < arr.length; i++) {
            int v = arr[i];
            if (v == -1 || v > limit) continue;
            if (max < v) {
                idx = i;
                max = v;
            }
        }
        if (max > -1) {
            arr[idx] = -1;
        }
        return max;
    }
}
