package rn.puzzle.structure.list.hard;

import org.junit.Test;
import rn.tool.Ass;
import rn.tool.FileHelper;
import rn.tool.StrConverter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//https://leetcode.com/problems/sliding-window-maximum/
public class SlidingWindowMaximumTest {

    public int[] maxSlidingWindowOptimal(int[] nums, int k) {
        if(nums == null || nums.length < 2) return nums;
        LinkedList<Integer> queue = new LinkedList<>();
        int[] result = new int[nums.length-k+1];
        for(int i = 0;i < nums.length;i++){
            while(!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]){
                queue.pollLast();
            }
            queue.addLast(i);
            if(queue.peek() <= i-k){
                queue.poll();
            }
            if(i+1 >= k){
                result[i+1-k] = nums[queue.peek()];
            }
        }
        return result;
    }

    private static class Node {
        int value;
        int idx;
        Node prev;
        Node next;

        Node(int value, int idx) {
            this.value = value;
            this.idx = idx;
        }

        @Override
        public String toString() {
            return value + "";
        }
    }

    private static class Deque {
        private Node root;
        private Node tail;
        private final Set<Integer> indexSet = new HashSet<>();

        void addNode(int v, int idx) {
            Node newNode = new Node(v, idx);
            indexSet.add(idx);

            while (tail != null && tail.value < v) {
                indexSet.remove(tail.idx);
                tail = tail.prev;
                if (tail != null) {
                    tail.next = null;
                }
            }

            if (tail != null) {
                if (tail.value == v) {
                    tail.idx = idx;
                } else {
                    tail.next = newNode;
                    newNode.prev = tail;
                    tail = newNode;
                }
            } else {
                tail = newNode;
                root = newNode;
            }
        }

        void removeNode(int idx) {
            indexSet.remove(idx);
        }

        Integer getValue() {
            while (root != null && !indexSet.contains(root.idx)) {
                root = root.next;
                if (root != null) {
                    root.prev = null;
                }
            }

            return root == null ? null : root.value;
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque deque = new Deque();
        k = Math.min(k, nums.length);
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            deque.addNode(nums[i], i);
        }
        if (deque.getValue() == null) {
            return res;
        }
        res[0] = deque.getValue();

        for (int i = k; i < nums.length; i++) {
            deque.removeNode(i - k);
            deque.addNode(nums[i], i);
            res[i - k + 1] = deque.getValue();
        }

        return res;
    }

    private int[] bruteForce(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length - k + 1; i++) {
            int max = nums[i];
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            res[i] = max;
        }

        return res;
    }

    @Test
    public void test8() {
        String nums = "[134,132,54,77,126,73,104,12,14,43,33,130,99,62,16,53,101,22,125,129,50,9,51,90,61,93,75,112,34,40,84,13,109,67,49,65,46,72,98,66,42,27,76,8,133,21,115,60,35,7,59,131,100,36,38,24,39,83,82,3,15,26,80,97,95,70,134,6,111,107,69,29,105,74,81,25,110,55,79,31,89,68,4,117,96,47,118,19,57,128,87,127,123,17,28,85,44,94,102,96,18,2,23,1,63,116,10,122,30,11,120,56,37,106,78,58,91,5,119,64,88,41,20,124,45,121,103,52,71,108,0,113,32,86,92,48,114]";
        run(nums, 70);
    }

    @Test
    public void test7() {
        List<String> lines = FileHelper.load("C:\\temp\\max_queue2.txt");
        String updated = lines.get(0);
        run(updated, 100);
    }

    @Test
    public void test6() {
        run("[9,10,9,-7,-4,-8,2,-6]", 5);
    }

    @Test
    public void test5() {
        run("[9,10,9,-7,-4,-8,2,-6]", 5);
    }

    @Test
    public void test4() {
        run("[1,3,1,2,0,5]", 3);
    }

    @Test
    public void test3() {
        run("[1,-1]", 1);
    }

    @Test
    public void test1() {
        run("[1,3,-1,-3,5,3,6,7]", 3);
    }

    @Test
    public void test2() {
        run("[1]", 1);
    }

    private void run(String numsStr, int k) {
        int[] nums = StrConverter.toIntArray(numsStr);
        int[] res = maxSlidingWindow(nums, k);
        int[] brute = bruteForce(nums, k);
        Ass.assertIntArrayEquals(brute, res);
    }
}
