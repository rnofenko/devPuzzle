package rn.puzzle.list.medium;

import org.junit.Test;

import java.util.Stack;

public class QueuesTaleOfTwoStacksTest {

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

        private Stack<T> arriveStack = new Stack<>();
        private Stack<T> orderedStack = new Stack<>();

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
    }
}
