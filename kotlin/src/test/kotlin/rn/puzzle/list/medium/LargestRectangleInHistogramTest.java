package rn.puzzle.list.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

public class LargestRectangleInHistogramTest {

    @Test
    public void test1() {
        test(new int[] { 2,1,5,6,2,3 }, 10);
    }

    @Test
    public void test2() {
        test(new int[] { 3,2,1,2,1 }, 5);
    }

    @Test
    public void test3() {
        test(new int[] { 0 }, 0);
        test(new int[] { }, 0);
    }

    @Test
    public void test4() {
        test(new int[] { 100000000 }, 100000000);
        test(new int[] { 0,0,0,Integer.MAX_VALUE }, Integer.MAX_VALUE);
    }

    public int largestRectangleArea(int[] heights) {
        Stack<int[]> startPoints = new Stack<>();
        int max = 0;

        for (int i = 0; i <= heights.length; i++) {
            int height = i == heights.length ? 0 : heights[i];

            int[] data = new int[] { i, 0 };

            while (!startPoints.isEmpty() && startPoints.peek()[1] > height) {
                data = startPoints.pop();
                int width = i - data[0];
                int size = width * data[1];
                max = Math.max(size, max);
            }

            data[1] = height;
            startPoints.add(data);
        }

        return max;
    }

    private void test(int[] heights, int expected) {
        int res = largestRectangleArea(heights);
        Assert.assertEquals(expected, res);
    }
}
