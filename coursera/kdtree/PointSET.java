import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

import java.util.ArrayList;

public class PointSET {
    private final SET<Point2D> set = new SET<>();

    public PointSET() { }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public int size() {
        return set.size();
    }

    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        set.add(p);
    }

    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        return set.contains(p);
    }

    public void draw() {
        for (Point2D point2D : set) {
            point2D.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }

        ArrayList<Point2D> found = new ArrayList<>();

        for (Point2D point : set) {
            if (rect.contains(point)) {
                found.add(point);
            }
        }

        return found;
    }
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        Point2D nearest = null;
        double nearestDistance = Double.POSITIVE_INFINITY;
        for (Point2D current : set) {
            double distance = current.distanceSquaredTo(p);
            if (distance < nearestDistance) {
                nearestDistance = distance;
                nearest = current;
            }
        }

        return nearest;
    }
}
