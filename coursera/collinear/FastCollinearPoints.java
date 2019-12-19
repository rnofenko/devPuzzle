import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FastCollinearPoints {

    private static final int SEGMENT_LENGTH = 4;
    private final LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
        validate(points);
        List<Point[]> listOfPoints = findSegments(points.clone());
        List<LineSegment> listOfSegments = dedupe(listOfPoints);
        segments = listOfSegments.toArray(new LineSegment[listOfSegments.size()]);
    }

    private List<Point[]> findSegments(Point[] points) {
        ArrayList<Point[]> all = new ArrayList<>();

        if (points.length < SEGMENT_LENGTH) {
            return all;
        }

        Point[] originPoints = points.clone();

        for (Point newPoint : originPoints) {
            Arrays.sort(points, slopeAndCoordinateComparator(newPoint.slopeOrder()));
            List<Point[]> newSegments = findSegment(points, newPoint);
            all.addAll(newSegments);
        }

        return all;
    }

    private List<LineSegment> dedupe(List<Point[]> list) {
        Comparator<Point[]> comparator = arrayOfPointsComparator();

        list.sort(comparator);

        ArrayList<LineSegment> result = new ArrayList<>();
        Point[] last = null;
        for (Point[] points : list) {
            if (last == null || comparator.compare(last, points) != 0) {
                last = points;
                result.add(new LineSegment(points[0], points[1]));
            }
        }

        return result;
    }

    private List<Point[]> findSegment(Point[] points, Point mainPoint) {
        Point first = null;
        Point last = null;
        double currentSlope = 0;
        int pointsCount = 0;
        ArrayList<Point[]> allSegments = new ArrayList<>();
        Point lastPointInArray = points[points.length - 1];

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

                boolean areSlopesEqual = Double.compare(currentSlope, newSlope) == 0;

                if (areSlopesEqual) {
                    last = newPoint;
                    pointsCount++;
                }
                if (!areSlopesEqual || newPoint == lastPointInArray) {
                    if (pointsCount + 1 >= SEGMENT_LENGTH) {
                        if (mainPoint.compareTo(first) < 0) {
                            first = mainPoint;
                        }
                        if (mainPoint.compareTo(last) > 0) {
                            last = mainPoint;
                        }
                        Point[] segmentPoints = wrapPointsToArray(first, last);
                        allSegments.add(segmentPoints);
                    }

                    first = newPoint;
                    last = null;
                    pointsCount = 1;
                    currentSlope = newSlope;
                }
            }
        }

        return allSegments;
    }

    private Point[] wrapPointsToArray(Point p1, Point p2) {
        Point[] points = new Point[2];
        points[0] = p1;
        points[1] = p2;
        return points;
    }

    private Comparator<Point> slopeAndCoordinateComparator(Comparator<Point> slopeComparator) {
        return (o1, o2) -> {
            int result = slopeComparator.compare(o1, o2);
            if (result == 0) {
                return o1.compareTo(o2);
            }
            return result;
        };
    }

    private Comparator<Point[]> arrayOfPointsComparator() {
        return (o1, o2) -> {
            int result = o1[0].compareTo(o2[0]);
            if (result == 0) {
                result = o1[1].compareTo(o2[1]);
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
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments.clone();
    }
}
