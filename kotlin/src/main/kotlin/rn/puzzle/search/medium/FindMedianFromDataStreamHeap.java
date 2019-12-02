package rn.puzzle.search.medium;

import java.util.Comparator;
import java.util.PriorityQueue;

public class FindMedianFromDataStreamHeap {
    private PriorityQueue<Integer> left;
    private PriorityQueue<Integer> right;

    public FindMedianFromDataStreamHeap() {
        left = new PriorityQueue<>(Comparator.reverseOrder());
        right = new PriorityQueue<>(Integer::compareTo);
    }

    public void addNum(int num) {
        Integer l = left.peek();
        if(l == null) {
            left.add(num);
        } else {
            if(num <= l) {
                left.add(num);
            } else {
                right.add(num);
            }
        }

        int sizeDiff = left.size() - right.size();
        if(sizeDiff == 2) {
            right.add(left.poll());
        } else if(sizeDiff == -1) {
            left.add(right.poll());
        }
    }

    public double findMedian() {
        Integer l = left.peek();
        Integer r = right.peek();
        if(l == null || r == null) {
            return r != null ? r : l == null ? 0 : l;
        }
        if(left.size() > right.size()) {
            return l;
        }
        return (l + r) / 2.0;
    }
}
