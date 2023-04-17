package rn.coursera.algtool.w3;

import java.util.*;
import java.util.stream.Collectors;

public class MaximumSalary {
    public static String findMax(String[] a) {
        List<String> sorted = Arrays.stream(a).sorted(MaximumSalary::numberCompare).collect(Collectors.toList());
        return String.join("", sorted);
    }

    public static int numberCompare(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len = Math.max(len1, len2);
        for (int i = 0; i <len; i++) {
            char c1 = i < len1 ? s1.charAt(i) : s2.charAt(i - len1);
            char c2 = i < len2 ? s2.charAt(i) : s1.charAt(i - len2);
            if (c1 < c2) {
                return 1;
            } else if (c1 > c2) {
                return -1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        System.out.println(findMax(a));
    }
}

