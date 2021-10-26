package rn.puzzle.structure.list.medium;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.StrConverter;

import java.util.Stack;

public class LargestRectangleTest {
    @Test
    public void test1() {
        long res = largestRectangle(StrConverter.INSTANCE.toIntArray("1 2 3 4 5"));
        Assert.assertEquals(9, res);
    }

    @Test
    public void test2() {
        long res = largestRectangle(StrConverter.INSTANCE.toIntArray("5 4 3 2 1"));
        Assert.assertEquals(9, res);
    }

    @Test
    public void test3() {
        long res = largestRectangle(StrConverter.INSTANCE.toIntArray("3 2 3 2"));
        Assert.assertEquals(8, res);
    }

    @Test
    public void test4() {
        long res = largestRectangle(StrConverter.INSTANCE.toIntArray("4 3 2 0 4 3 2 2"));
        Assert.assertEquals(8, res);
    }

    @Test
    public void test5() {
        long res = largestRectangle(StrConverter.INSTANCE.toIntArray("4 3 2 0 3 4 3 2 2"));
        Assert.assertEquals(10, res);
    }

    @Test
    public void test6() {
        long res = largestRectangle(StrConverter.INSTANCE.toIntArray("1 2 3 2 1"));
        Assert.assertEquals(6, res);
    }

    @Test
    public void test7() {
        long res = largestRectangle(StrConverter.INSTANCE.toIntArray("1 1 2 2 4 4 4"));
        Assert.assertEquals(12, res);
    }

    static final int HEIGHT = 0;
    static final int INDEX = 1;

    static long largestRectangle(int[] heights) {
        long max = Long.MIN_VALUE;
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            int h = heights[i];
            int[] prev = stack.isEmpty() ? null : stack.peek();
            if (prev != null && prev[HEIGHT] < h) {
                stack.add(new int[]{h, i});
            } else {
                while (!stack.isEmpty() && stack.peek()[HEIGHT] > h) {
                    prev = stack.pop();
                    long count = i - prev[INDEX];
                    max = Math.max(max, count * prev[HEIGHT]);
                }

                if (!stack.isEmpty() && stack.peek()[HEIGHT] == h) continue;
                stack.add(new int[]{h, prev == null ? 0 : prev[INDEX]});
            }
        }

        while (!stack.isEmpty() && stack.peek()[HEIGHT] > 0) {
            int[] last = stack.pop();
            long count = heights.length - last[INDEX];
            max = Math.max(max, count * last[HEIGHT]);
        }

        return max;
    }
}
