import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    private final LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        validate(points);
        segments = veryBrute(points.clone());
    }

    private LineSegment[] veryBrute(Point[] points) {
        ArrayList<LineSegment> list = new ArrayList<>();

        for (int i = 0; i < points.length; i++) {

            Point pi = points[i];
            for (int j = i + 1; j < points.length; j++) {

                double slopeJ = pi.slopeTo(points[j]);
                for (int k = j + 1; k < points.length; k++) {

                    double slopeK = pi.slopeTo(points[k]);
                    if (Double.compare(slopeJ, slopeK) != 0) {
                        continue;
                    }

                    for (int lastIndex = k + 1; lastIndex < points.length; lastIndex++) {

                        double slopeL = pi.slopeTo(points[lastIndex]);
                        if (Double.compare(slopeJ, slopeL) == 0) {

                            Point[] segmentPoints = new Point[4];
                            segmentPoints[0] = pi;
                            segmentPoints[1] = points[j];
                            segmentPoints[2] = points[k];
                            segmentPoints[3] = points[lastIndex];
                            Arrays.sort(segmentPoints, Point::compareTo);

                            LineSegment segment = new LineSegment(segmentPoints[0], segmentPoints[3]);
                            list.add(segment);
                        }
                    }
                }
            }
        }

        return list.toArray(new LineSegment[list.size()]);
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

        for (int i = 0; i < points.length; i++) {
            Point pi = points[i];
            for (int j = i + 1; j < points.length; j++) {
                if (pi.compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
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
