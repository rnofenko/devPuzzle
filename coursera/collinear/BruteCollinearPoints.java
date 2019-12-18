import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        validate(points);
        segments = veryBrute(points);
    }

    private LineSegment[] veryBrute(Point[] points) {
        ArrayList<LineSegment> segments = new ArrayList<>();

        for (int i = 0; i < points.length; i++) {
            Point pi = points[i];
            for (int j = i + 1; j < points.length; j++) {
                double slopeJ = pi.slopeTo(points[j]);
                for (int k = j + 1; k < points.length; k++) {
                    double slopeK = pi.slopeTo(points[k]);
                    if (Double.compare(slopeJ, slopeK) != 0) {
                        continue;
                    }

                    for (int l = k + 1; l < points.length; l++) {
                        double slopeL = pi.slopeTo(points[l]);
                        if (Double.compare(slopeJ, slopeL) == 0) {

                            Point[] segmentPoints = new Point[4];
                            segmentPoints[0] = pi;
                            segmentPoints[1] = points[j];
                            segmentPoints[2] = points[k];
                            segmentPoints[3] = points[l];
                            Arrays.sort(segmentPoints, Point::compareTo);

                            for (int f = 0; f < segmentPoints.length - 1; f++) {
                                if (segmentPoints[f].equals(segmentPoints[f + 1])) {
                                    throw new IllegalArgumentException();
                                }
                            }

                            LineSegment segment = new LineSegment(segmentPoints[0], segmentPoints[3]);
                            segments.add(segment);
                        }
                    }
                }
            }
        }

        return segments.toArray(new LineSegment[segments.size()]);
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
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments;
    }
}
