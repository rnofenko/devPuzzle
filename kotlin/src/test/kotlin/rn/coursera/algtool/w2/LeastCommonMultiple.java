package rn.coursera.algtool.w2;

import java.util.*;

public class LeastCommonMultiple {
  public static long findLcm(long a, long b) {
    long gcd = findGcd(a, b);
    return  (a / gcd) * b;
  }

  private static long findGcd(long a, long b) {
    while (true) {
      long m = Math.max(a, b);
      b = Math.min(a, b);
      a = m;

      long d = a / b;
      long bb = b * d;

      long delta = a - bb;
      if (delta == 0) {
        return b;
      }
      a = delta;
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    System.out.println(findLcm(a, b));
  }
}
