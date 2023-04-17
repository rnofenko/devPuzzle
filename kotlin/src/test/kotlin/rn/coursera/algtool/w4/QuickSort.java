package rn.coursera.algtool.w4;

import java.io.*;
import java.util.*;

public class QuickSort {
    public static void quickSort(int[] a) {
        quickSort(a, 0, a.length - 1);
    }

    private static void quickSort(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }

        int pivot = getPivot(a, start, end);
        int i = start;
        int j = end;
        int count = 0;
        int cursor = start;

        while (cursor <= j) {
            int v = a[cursor];
            if (v == pivot) {
                count++;
                cursor++;
            } else if (v < pivot) {
                a[i] = v;
                i++;
                cursor++;
            } else {
                a[cursor] = a[j];
                a[j] = v;
                j--;
            }
        }

        quickSort(a, start, i - 1);
        while (count > 0) {
            a[i] = pivot;
            count--;
            i++;
        }
        quickSort(a, i, end);
    }

    private static int getPivot(int[] array, int l, int r) {
        int a = array[l];
        int b = array[r];
        int c = array[l + (r - l) / 2];
        if (a > b) {
            if (c > a) {
                return a;
            }
            return Math.max(c, b);
        } else {
            if (c > b) {
                return b;
            }
            return Math.max(a, c);
        }
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        quickSort(a);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

