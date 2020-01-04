/******************************************************************************
 *  Compilation:  javac KdTreeGenerator.java
 *  Execution:    java KdTreeGenerator n
 *  Dependencies: 
 *
 *  Creates n random points in the unit square and print to standard output.
 *
 *  % java KdTreeGenerator 5
 *  0.195080 0.938777
 *  0.351415 0.017802
 *  0.556719 0.841373
 *  0.183384 0.636701
 *  0.649952 0.237188
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KdTreeGenerator {

    public static void main(String[] args) {
//        int n = Integer.parseInt(args[0]);
//        for (int i = 0; i < n; i++) {
//            double x = StdRandom.uniform(0.0, 1.0);
//            double y = StdRandom.uniform(0.0, 1.0);
//            StdOut.printf("%8.6f %8.6f\n", x, y);
//        }

        test1();
        test2();
    }

    public static void test2() {

        List<Point2D> points = Arrays.asList(new Point2D(0.7, 0.2), new Point2D(0.5, 0.4), new Point2D(0.2, 0.3),
                new Point2D(0.4, 0.7), new Point2D(0.9, 0.6));

        KdTree tree = new KdTree();
        for (Point2D point : points) {
            tree.insert(point);
        }

        for (Point2D point : points) {
            assert (tree.contains(point));
        }
    }

    public static void test1() {
        KdTree tree = new KdTree();
        tree.insert(new Point2D(0.7, 0.2));
        tree.insert(new Point2D(0.5, 0.4));
        tree.insert(new Point2D(0.2, 0.3));
        tree.insert(new Point2D(0.4, 0.7));
        tree.insert(new Point2D(0.9, 0.6));

        tree.nearest(new Point2D(0.67, 0.73));
    }
}
