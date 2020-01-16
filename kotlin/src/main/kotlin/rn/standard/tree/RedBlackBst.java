package rn.standard.tree;

public class RedBlackBst<TKey extends Comparable<TKey>, TValue> {

    private Node<TKey, TValue> root;

    public void add(TKey item, TValue value) {
        Node<TKey, TValue> newNode = new Node<>(item, value);
        this.root = put(root, newNode);
        this.root.isRed = false;
    }

    private Node<TKey, TValue> put(Node<TKey, TValue> parent, Node<TKey, TValue> newNode) {
        if (parent == null) {
            return newNode;
        }
        int compareResult = newNode.key.compareTo(parent.key);
        if (compareResult == 0) {
            parent.value = newNode.value;
        } else if (compareResult < 0) {
            parent.left = put(parent.left, newNode);
        } else {
            parent.right = put(parent.right, newNode);
        }

        if (parent.isAnyRed()) {
            if (parent.isLeftBlackRightRed()) {
                parent = rotateLeft(parent);
            }
            if (parent.isLeftLeftRed()) {
                parent = rotateRight(parent);
            }
            if (parent.isFullRed()) {
                flipColors(parent);
            }
        }

        return parent;
    }

    private void flipColors(Node<TKey, TValue> h)
    {
        h.isRed = true;
        h.left.isRed = false;
        h.right.isRed = false;
    }

    private Node<TKey, TValue> rotateRight(Node<TKey, TValue> h)
    {
        Node<TKey, TValue> x = h.left;
        h.left = x.right;
        x.right = h;
        x.isRed = h.isRed;
        h.isRed = true;
        return x;
    }

    private Node<TKey, TValue> rotateLeft(Node<TKey, TValue> h) {
        Node<TKey, TValue> x = h.right;
        h.right = x.left;
        x.left = h;
        x.isRed = h.isRed;
        h.isRed = true;
        return x;
    }

    public TValue get(TKey searchItem) {
        Node<TKey, TValue> node = root;
        while (node != null) {
            int compareResult = node.key.compareTo(searchItem);
            if (compareResult == 0) {
                return node.value;
            }
            node = compareResult > 0 ? node.left : node.right;
        }

        return null;
    }

    public boolean contains(TKey searchItem) {
        Node<TKey, TValue> node = root;
        while (node != null) {
            int compareResult = node.key.compareTo(searchItem);
            if (compareResult == 0) {
                return true;
            }
            node = compareResult > 0 ? node.left : node.right;
        }

        return false;
    }

    private static class Node<TKey, TValue> {
        private final TKey key;
        private TValue value;
        private Node<TKey, TValue> left;
        private Node<TKey, TValue> right;
        private boolean isRed;

        public Node(TKey key, TValue value) {
            this.key = key;
            this.value = value;
            this.isRed = true;
        }

        @Override
        public String toString() {
            return String.format("key=%s%s left=%s right=%s", key, isRed ? " r" : "", countLeft(), countRight());
        }

        private int countLeft() {
            if (left == null) {
                return 0;
            }
            return left.countLeft() + 1;
        }

        private int countRight() {
            if (right == null) {
                return 0;
            }
            return right.countRight() + 1;
        }

        public boolean isAnyRed() {
            return (left != null && left.isRed) || (right != null && right.isRed);
        }

        public boolean isLeftBlackRightRed() {
            return (left == null || !left.isRed) && (right != null && right.isRed);
        }

        public boolean isLeftLeftRed() {
            if (left == null || !left.isRed) {
                return false;
            }
            Node<TKey, TValue> l = left.left;
            return l != null && l.isRed;
        }

        public boolean isFullRed() {
            return left != null && left.isRed && right!= null && right.isRed;
        }
    }
}
