package rn.puzzle.search.medium;

import java.util.*;

public class LargestTripleProductsTest {
    int[] findMaxProduct(int[] arr) {
        int[] res = new int[arr.length];
        res[0] = -1;
        res[1] = -1;
        if (arr.length < 3) {
            return res;
        }
        int[] max = new int[4];
        max[0] = Math.max(arr[0], arr[1]);
        max[1] = Math.min(arr[0], arr[1]);

        for(int i=2;i<arr.length;i++) {
            max[3] = arr[i];
            sort(max);
            res[i] = max[0] * max[1] * max[2];
        }

        return res;
    }

    private void sort(int[] a) {
        if (a[3] <= a[2]) return;
        int t = a[3];
        a[3] = a[2];
        a[2] = t;
        if (a[2] <= a[1]) return;
        a[2] = a[1];
        a[1] = t;
        if (a[1] <= a[0]) return;
        a[1] = a[0];
        a[0] = t;
    }
}
