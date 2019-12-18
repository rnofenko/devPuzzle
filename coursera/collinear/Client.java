import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class Client {
    public static void main(String[] args) {
        // read the n points from a file
//        In in = new In(args[0]);
        In in = new In("input8.txt");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        Stopwatch w = new Stopwatch();
        FastCollinearPoints fast = new FastCollinearPoints(points);
        for (LineSegment segment : fast.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdOut.println("FAST " + w.elapsedTime());

        w = new Stopwatch();
        BruteCollinearPoints brute = new BruteCollinearPoints(points);
        for (LineSegment segment : brute.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdOut.println("SLOW " + w.elapsedTime());

        StdDraw.show();
    }
}
