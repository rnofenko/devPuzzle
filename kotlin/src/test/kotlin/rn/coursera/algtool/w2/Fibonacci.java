package rn.coursera.algtool.w2;

import java.util.Scanner;

public class Fibonacci {
  public static long calc_fib(int n) {
    if (n <= 1) {
      return n;
    }

    long prev = 0;
    long last = 1;
    for (int i = 1; i < n; i++) {
      long newLast = last + prev;
      prev = last;
      last = newLast;
    }

    return last;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();

    System.out.println(calc_fib(n));
  }
}
