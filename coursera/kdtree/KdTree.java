import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;

public class KdTree {
//    private static final double PRECISION = 0.00000001;

    private int size = 0;
    private Node root;

    public KdTree() { }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        if (root == null) {
            root = new Node(p, new RectHV(0, 0, 1, 1));
            size++;
        } else {
            boolean horizontal = true;
            Node current = root;

            while (true) {
                if (current.p.equals(p)) {
                    break;
                }

                double currentCoord = horizontal ? current.p.x() : current.p.y();
                double newCoord = horizontal ? p.x() : p.y();

                RectHV rect = current.rect;
                if (newCoord < currentCoord) {
                    if (current.lb == null) {
                        double x = horizontal ? current.p.x() : rect.xmax();
                        double y = horizontal ? rect.ymax() : current.p.y();
                        RectHV newRect = new RectHV(rect.xmin(), rect.ymin(), x, y);
                        current.lb = new Node(p, newRect);
                        size++;
                        break;
                    } else {
                        current = current.lb;
                    }
                } else {
                    if (current.rt == null) {
                        double x = horizontal ? current.p.x() : rect.xmin();
                        double y = horizontal ? rect.ymin() : current.p.y();
                        RectHV newRect = new RectHV(x, y, rect.xmax(), rect.ymax());
                        current.rt = new Node(p, newRect);
                        size++;
                        break;
                    } else {
                        current = current.rt;
                    }
                }

                horizontal = !horizontal;
            }
        }
    }

    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        return contains(p, root, true);
    }

    private boolean contains(Point2D p, Node node, boolean horizontal) {
        if (node == null) {
            return false;
        }

        if (node.p.equals(p)) {
            return true;
        }

        if (horizontal) {
            return contains(p, p.x() < node.p.x() ? node.lb : node.rt, false);
        } else {
            return contains(p, p.y() < node.p.y() ? node.lb : node.rt, true);
        }
    }

    public void draw() {
        draw(root);
    }

    private void draw(Node node) {
        if (node == null) {
            return;
        }
        node.p.draw();
        draw(node.lb);
        draw(node.rt);
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }

        ArrayList<Point2D> found = new ArrayList<>();
        range(rect, root, found);

        return found;
    }

    private void range(RectHV rect, Node node, ArrayList<Point2D> found) {
        if (node == null) {
            return;
        }

        if (!rect.intersects(node.rect)) {
            return;
        }

        if (rect.contains(node.p)) {
            found.add(node.p);
        }
        range(rect, node.lb, found);
        range(rect, node.rt, found);
    }

    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        if (root == null) {
            return null;
        }

        return nearest(p, root, root.p, true);
    }

    private Point2D nearest(Point2D p, Node node, Point2D closestPoint, boolean horizontal) {
        if (node == null) {
            return closestPoint;
        }

        double closestDistance = p.distanceSquaredTo(closestPoint);
        if (closestDistance <= node.rect.distanceSquaredTo(p)) {
            return closestPoint;
        }

        if (p.distanceSquaredTo(node.p) < closestDistance) {
            closestPoint = node.p;
        }

        Node first = node.lb;
        Node second = node.rt;
        if ((horizontal && p.x() >= node.p.x()) || (!horizontal && p.y() >= node.p.y())) {
            first = node.rt;
            second = node.lb;
        }

        closestPoint = nearest(p, first, closestPoint, !horizontal);
        return nearest(p, second, closestPoint, !horizontal);
    }

    private static class Node {
        private final Point2D p;
        private final RectHV rect;
        private Node lb;
        private Node rt;

        public Node(Point2D p, RectHV rect) {
            this.p = p;
            this.rect = rect;
        }
    }
}
