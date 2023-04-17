package rn.coursera.algtool.w3;

import java.util.Scanner;

public class Change {
    private static int getChange(int m) {
        int count10 = m / 10;
        m = m - count10 * 10;

        int count5 = m / 5;
        m = m - count5 * 5;

        return count10 + count5 + m;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

