import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] data;
    private int tailPos = -1;

    // construct an empty queue
    public RandomizedQueue() {
        data = (Item[]) new Object[0];
    }

    public boolean isEmpty() {
        return tailPos == -1;
    }

    public int size() {
        return tailPos + 1;
    }

    public void enqueue(Item item) {
        if(item == null) {
            throw new IllegalArgumentException();
        }

        checkSize();

        data[++tailPos] = item;
    }

    private void checkSize() {
        int oldSize = size();

        if (oldSize == 0) {
            resize(2, 0);
        } else if (oldSize == data.length) {
            resize(oldSize * 2, oldSize);
        }
    }

    private void resize(int newSize, int oldSize) {
        Item[] newData = (Item[]) new Object[newSize];
        if(oldSize > 0) {
            System.arraycopy(data, 0, newData, 0, oldSize);
        }

        data = newData;
    }

    public Item dequeue() {
        if(tailPos == -1) {
            throw new NoSuchElementException();
        }

        int index = getRandomIndex();

        Item item = data[index];
        data[index] = data[tailPos];
        data[tailPos] = null;
        tailPos--;

        return item;
    }

    public Item sample() {
        if(tailPos == -1) {
            throw new NoSuchElementException();
        }

        return data[getRandomIndex()];
    }

    private int getRandomIndex() {
        return StdRandom.uniform(tailPos + 1);
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        RandomizedQueue<Item> queue = new RandomizedQueue<>();
        queue.data = data.clone();
        queue.tailPos = tailPos;

        return new RandomizedQueueIterator(queue);
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private final RandomizedQueue<Item> queue;
        private int index;
        private Object next;

        public RandomizedQueueIterator(RandomizedQueue<Item> queue) {
            this.queue = queue;
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public Item next() {
            if (queue.isEmpty()) {
                throw new NoSuchElementException();
            }
            return queue.dequeue();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        printQueue(queue, "Init");

        queue.enqueue(1);
        printQueue(queue, "enqueue 1");

        queue.enqueue(2);
        printQueue(queue, "enqueue 2");

        queue.enqueue(3);
        printQueue(queue, "enqueue 3");

        queue.enqueue(4);
        printQueue(queue, "enqueue 4");

        queue.dequeue();
        printQueue(queue, "dequeue");

        queue.dequeue();
        printQueue(queue, "dequeue");

        queue.dequeue();
        printQueue(queue, "dequeue");

        queue.dequeue();
        printQueue(queue, "dequeue");
    }

    private static void print(String name, Object value) {
        StdOut.println(String.format("%-25s", name) + " = " + value);
    }

    private static void printQueue(RandomizedQueue<Integer> queue, String text) {
        StdOut.println("            " + text);
        print("Is Empty", queue.isEmpty());
        print("Size", queue.size());

        StringBuilder allNumbers = new StringBuilder();
        for (Integer val : queue) {
            allNumbers.append(val).append(" ");
        }
        print("Numbers", allNumbers.toString());
    }
}