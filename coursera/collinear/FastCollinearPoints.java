import edu.princeton.cs.algs4.Queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {
    private LineSegment[] segments;
    private final static int SEGMENT_LENGTH = 4;

    public FastCollinearPoints(Point[] points) {
        validate(points);
        segments = findSegments(points);
    }

    private LineSegment[] findSegments(Point[] points) {
        if (points.length < SEGMENT_LENGTH) {
            return new LineSegment[0];
        }

        ArrayList<LineSegment> segments = new ArrayList<>();
        Point[] originPoints = points.clone();

        for (Point newPoint : originPoints) {
            Arrays.sort(points, slopeAndCoordinateComparator(newPoint.slopeOrder()));
            LineSegment segment = findSegment(points, newPoint);
            if (segment != null) {
                segments.add(segment);
            }
        }

        return segments.toArray(new LineSegment[segments.size()]);
    }

    private LineSegment findSegment(Point[] points, Point mainPoint) {
        Point first = null;
        Point last = null;
        double currentSlope = 0;
        int pointsCount = 0;

        for (Point newPoint : points) {
            if (mainPoint.equals(newPoint)) {
                continue;
            }

            if (first == null) {
                first = newPoint;
                currentSlope = mainPoint.slopeTo(newPoint);
                pointsCount = 1;
            } else {
                double newSlope = mainPoint.slopeTo(newPoint);

                if (Double.compare(currentSlope, newSlope) == 0) {
                    last = newPoint;
                    pointsCount++;
                } else {
                    if (pointsCount >= SEGMENT_LENGTH) {
                        segments.add(new LineSegment(first, last));
                    }

                    first = last;
                    last = newPoint;
                    pointsCount = 2;
                    currentSlope = first.slopeTo(last);;
                }
            }
        }

        return null;
    }

    public Comparator<Point> slopeAndCoordinateComparator(Comparator<Point> slopeComparator) {
        return (o1, o2) -> {
            int result = slopeComparator.compare(o1, o2);
            if(result == 0) {
                return o1.compareTo(o2);
            }
            return result;
        };
    }

    private void validate(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }

        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException();
            }
        }

        Arrays.sort(points, Point::compareTo);
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].equals(points[i + 1])) {
                throw new IllegalArgumentException();
            }
        }
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments;
    }
}
