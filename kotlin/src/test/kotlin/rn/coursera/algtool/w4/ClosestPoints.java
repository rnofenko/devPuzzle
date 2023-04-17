package rn.coursera.algtool.w4;

import java.io.*;
import java.util.*;

public class ClosestPoints {
    private static Distance findMin(Point[] points, boolean isX) {
        if (points.length < 6) {
            return bruteForce(points);
        }

        Arrays.sort(points, isX ? comparatorX : comparatorY);
        int m = points.length / 2;
        Point[] left = Arrays.copyOfRange(points, 0, m);
        Point[] right = Arrays.copyOfRange(points, m, points.length);
        Distance d1 = findMin(left, !isX);
        Distance d2 = findMin(right, !isX);
        Distance minD = d1.calc() > d2.calc() ? d2 : d1;

        return mergeFind(minD, left, right, isX);
    }

    private static Distance mergeFind(Distance min, Point[] fullLeft, Point[] fullRight, boolean isX) {
        long stripLen = (long)Math.ceil(min.calcSqrt());
        Point[] left = getStrip(stripLen, fullLeft, isX, true);
        Point[] right = getStrip(stripLen, fullRight, isX, false);
        min = new Distance(left[0], right[0]);
        for (int i = 0; i < left.length; i++) {
            for (int j = 0; j < right.length; j++) {
                Distance d = new Distance(left[i], right[j]);
                if (min.calc() > d.calc()) {
                    min = d;
                }
            }
        }
        return min;
    }

    private static Point[] getStrip(long stripLen, Point[] points, boolean isX, boolean isLeft) {
        int start = 0;
        int end = points.length - 1;
        while (start < end) {
            Point pS = points[start];
            Point pE = points[end];
            long d = Math.abs(isX ? pS.x - pE.x : pS.y - pE.y);
            if (d <= stripLen) {
                break;
            }
            if (isLeft) {
                start++;
            } else {
                end--;
            }
        }
        return Arrays.copyOfRange(points, start, end + 1);
    }

    public static double findClosestPoints(int[] x, int[] y) {
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        Distance d = findMin(points, true);
        return Math.sqrt(d.calc());
    }

    public static double findClosestPoints(int[][] xy) {
        Point[] points = new Point[xy.length];
        for (int i = 0; i < xy.length; i++) {
            points[i] = new Point(xy[i][0], xy[i][1]);
        }
        Distance d = findMin(points, true);
        return d.calcSqrt();
    }

    private static Distance bruteForce(Point[] points) {
        Distance min = new Distance(points[0], points[1]);
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                Distance d = new Distance(points[i], points[j]);
                if (min.calc() > d.calc()) {
                    min = d;
                }
            }
        }
        return min;
    }

    static class Point {
        long x;
        long y;
        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString() {
            return x + " " + y;
        }
    }

    static class Distance {
        Point p1;
        Point p2;
        public Distance(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
        public double calc() {
            long dx = this.p1.x - this.p2.x;
            long dy = this.p1.y - this.p2.y;
            return dx * dx + dy * dy;
        }
        public double calcSqrt() {
            return Math.sqrt(calc());
        }
        @Override
        public String toString() {
            return p1 + " x " + p2 + " d=" + calc();
        }
    }

    static Comparator<Point> comparatorX = (o1, o2) -> o1.x == o2.x ? Long.signum(o1.y - o2.y) : Long.signum(o1.x - o2.x);
    static Comparator<Point> comparatorY = (o1, o2) -> o1.y == o2.y ? Long.signum(o1.x - o2.x) : Long.signum(o1.y - o2.y);

    public static void main(String[] args) throws Exception {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
        int n = nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = nextInt();
            y[i] = nextInt();
        }
        System.out.println(findClosestPoints(x, y));
        writer.close();
    }

    static BufferedReader reader;
    static PrintWriter writer;
    static StringTokenizer tok = new StringTokenizer("");


    static String next() {
        while (!tok.hasMoreTokens()) {
            String w = null;
            try {
                w = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (w == null)
                return null;
            tok = new StringTokenizer(w);
        }
        return tok.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }
}
