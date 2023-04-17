package rn.coursera.algtool.w2;

import java.util.*;

public class GreatestCommonDivider {
  public static int findGcd(int a, int b) {
    while (true) {
      int m = Math.max(a, b);
      b = Math.min(a, b);
      a = m;

      int d = a / b;
      int bb = b * d;

      int delta = a - bb;
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

    System.out.println(findGcd(a, b));
  }
}
