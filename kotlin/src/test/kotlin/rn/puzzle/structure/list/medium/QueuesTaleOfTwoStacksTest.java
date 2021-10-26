package rn.puzzle.structure.list.medium;

import org.junit.Test;

import java.util.Scanner;
import java.util.Stack;

public class QueuesTaleOfTwoStacksTest {

    public static void consoleRunner() {
        Scanner scan = new Scanner(System.in);
        int queriesCount = scan.nextInt();

        MyQueue<Integer> queue = new MyQueue<>();
        for (int i = 0; i < queriesCount; i++) {
            int type = scan.nextInt();
            if (type == 1) {
                queue.enqueue(scan.nextInt());
            } else if (type == 2) {
                queue.dequeue();
            } else {
                System.out.println(queue.peek());
            }
        }
    }

    @Test
    public void test1() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);

        System.out.println(queue.peek());
        queue.dequeue();

        queue.enqueue(3);

        System.out.println(queue.peek());
        queue.dequeue();

        queue.enqueue(4);
        queue.enqueue(5);

        System.out.println(queue.peek());
        queue.dequeue();

        System.out.println(queue.peek());
        queue.dequeue();

        System.out.println(queue.peek());
        queue.dequeue();
    }

    private static class MyQueue<T> {

        private final Stack<T> arriveStack = new Stack<>();
        private final Stack<T> orderedStack = new Stack<>();

        public void enqueue(T newElement) {
            arriveStack.addElement(newElement);
        }

        public void dequeue() {
            if(orderedStack.isEmpty()) {
                moveFromOneToTwo();
            }
            orderedStack.pop();
        }

        public T peek() {
            if(orderedStack.isEmpty()) {
                moveFromOneToTwo();
            }
            return orderedStack.peek();
        }

        private void moveFromOneToTwo() {
            while (!arriveStack.isEmpty()) {
                orderedStack.addElement(arriveStack.pop());
            }
        }

        public T peekFirst() {
            moveFromOneToTwo();
            return orderedStack.peek();
        }
    }
}
