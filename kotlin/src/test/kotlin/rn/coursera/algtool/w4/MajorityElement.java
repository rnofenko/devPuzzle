package rn.coursera.algtool.w4;

import java.util.*;
import java.io.*;

public class MajorityElement {
    private static int getMajorityElement(int[] a, int left, int right) {
        int x = majorityElement(a);
        int count = 0;
        int goal = a.length / 2 + 1;
        for (int j : a) {
            if (j == x) count++;
            if (count == goal) {
                return x;
            }
        }
        return -1;
    }

    public static  int majorityElement(int[] nums) {
        int x = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (x == nums[i]) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                count = 1;
                x = nums[i];
            }
        }

        return x;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        if (getMajorityElement(a, 0, a.length) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
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

