package rn.coursera.algtool.w4;

import java.util.*;

public class InversionsCount {
    public static long getNumberOfInversions(int[] a) {
        return getNumberOfInversions(a, 0, a.length);
    }

    private static long getNumberOfInversions(int[] a, int l, int r) {
        if (r - l < 2) {
            return 0;
        }
        long count = 0;
        int m = l + (r - l) / 2;
        count += getNumberOfInversions(a, l, m);
        count += getNumberOfInversions(a, m, r);
        count += mergeCount(a, l, m, r);

        return count;
    }

    private static long mergeCount(int[] a, int l, int m, int r) {
        int[] leftA = Arrays.copyOfRange(a, l, m);
        int[] rightA = Arrays.copyOfRange(a, m, r);

        int li = 0;
        int ri = 0;
        int i = l;
        long count = 0;
        while (li < leftA.length && ri < rightA.length) {
            if (leftA[li] <= rightA[ri]) {
                a[i++] = leftA[li++];
            } else {
                a[i++] = rightA[ri++];
                count += leftA.length - li;
            }
        }

        while (li < leftA.length) a[i++] = leftA[li++];
        while (ri < rightA.length) a[i++] = rightA[ri++];

        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        System.out.println(getNumberOfInversions(a));
    }
}

