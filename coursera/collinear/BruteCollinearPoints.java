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

                            Point[] linePoints = new Point[4];
                            linePoints[0] = pi;
                            linePoints[1] = points[j];
                            linePoints[2] = points[k];
                            linePoints[3] = points[l];
                            Arrays.sort(linePoints, Point::compareTo);

                            LineSegment segment = new LineSegment(linePoints[0], linePoints[3]);
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
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i] == null || points[i + 1] == null || points[i].equals(points[i + 1])) {
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
