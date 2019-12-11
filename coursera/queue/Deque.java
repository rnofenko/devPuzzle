import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private static final int MULTIPLIER = 3;
    private Item[] data;
    private int headPos = 0;
    private int tailPos = 0;

    // construct an empty deque
    public Deque() {
        data = (Item[]) new Object[0];
    }

    // is the deque empty?
    public boolean isEmpty() {
        return tailPos - headPos < 2;
    }

    // return the number of items on the deque
    public int size() {
        return isEmpty() ? 0 : tailPos - headPos - 1;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        checkSize();

        if (headPos == tailPos) {
            tailPos++;
        }
        data[headPos--] = item;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        checkSize();

        if (headPos == tailPos) {
            headPos--;
        }
        data[tailPos++] = item;
    }

    private void checkSize() {
        int oldSize = size();

        if (data.length == 0) {
            resize(MULTIPLIER, 0);
        } else if (headPos == -1 || tailPos == data.length) {
            if (oldSize == 0) {
                int shift = data.length / MULTIPLIER;
                headPos = shift;
                tailPos = shift;
            } else {
                resize(data.length * MULTIPLIER, oldSize);
            }
        }
    }

    private void resize(int newSize, int oldSize) {
        Item[] newData = (Item[]) new Object[newSize];
        int shift = newSize / MULTIPLIER;

        if (oldSize > 0) {
            System.arraycopy(data, headPos + 1, newData, shift, oldSize);
            headPos = shift - 1;
            tailPos = shift + oldSize;
        } else {
            headPos = shift;
            tailPos = shift;
        }

        data = newData;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        headPos++;
        Item item = data[headPos];
        data[headPos] = null;

        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        tailPos--;
        Item item = data[tailPos];
        data[tailPos] = null;

        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private int index;

        DequeIterator() {
            index = headPos;
        }

        @Override
        public boolean hasNext() {
            return index + 1 < tailPos && index + 1 < data.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            index++;
            return data[index];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        printDeque(deque, "Init");

        deque.addLast(1);
        printDeque(deque, "Add Last 1");

        deque.addLast(2);
        printDeque(deque, "Add Last 2");

        deque.addFirst(11);
        printDeque(deque, "Add First 11");

        deque.addLast(3);
        printDeque(deque, "Add Last 3");

        deque.removeFirst();
        printDeque(deque, "Remove First");

        deque.removeLast();
        printDeque(deque, "Remove Last");

        deque.removeLast();
        printDeque(deque, "Remove Last");

        deque.removeFirst();
        printDeque(deque, "Remove First");
    }

    private static void print(String name, Object value) {
        StdOut.println(String.format("%-25s", name) + " = " + value);
    }

    private static void printDeque(Deque<Integer> deque, String text) {
        StdOut.println("            " + text);
        print("Is Empty", deque.isEmpty());
        print("Size", deque.size());

        StringBuilder allNumbers = new StringBuilder();
        for (Integer val : deque) {
            allNumbers.append(val).append(" ");
        }
        print("Numbers", allNumbers.toString());
    }
}