package rn.puzzle.dynamic.medium;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.ArrayPrinter;
import rn.tool.Rand;
import rn.tool.combination.CombinatorFactory;
import rn.tool.combination.ICombinator;

import java.util.Arrays;

public class MaxArraySumTest {

    @Test
    public void test1() {
        int[] numbers = {-2,1,3,-4,5};
        solve(numbers, 8);
    }

    @Test
    public void test2() {
        int[] numbers = {3,5,-7,8,10};
        solve(numbers, 15);
    }

    @Test
    public void test3() {
        int[] numbers = {-2,-2,-5,7,2};
        solve(numbers, 7);
    }

    @Test
    public void searchMismatch() {
        for (int i = 0; i < 1000; i++) {
            int[] arr = Rand.INSTANCE.newArray(5, 10, true);
            compare(arr);
        }
    }

    private void solve(int[] arr, int expected) {
        long r = maxSubsetSum(arr);
        Assert.assertEquals(expected, r);
    }

    private void compare(int[] arr) {
        long r = maxSubsetSum(arr);
        long brute = solveBruteForce(arr);
        if(brute != r) {
            ArrayPrinter.INSTANCE.print(arr);
            Assert.assertEquals(brute, r);
        }
    }

    private long solveBruteForce(int[] arr) {
        ICombinator combinator = CombinatorFactory.variantLength(arr.length - 1);
        long max = Long.MIN_VALUE;
        while (true) {
            int[] indices = combinator.next();
            if(indices == null) {
                break;
            }
            long sum = 0;
            int prevIndex = -2;
            for (int index : indices) {
                if(prevIndex + 1 == index) {
                    sum = Long.MIN_VALUE;
                    break;
                }
                prevIndex = index;

                sum += arr[index];
            }
            max = Math.max(max, sum);
        }
        return max;
    }

    private static int maxSubsetSum(int[] arr) {
        int[] cached = new int[arr.length];

        for(int i = arr.length - 1; i >= 0; i--) {
            cached[i] = maxSubsetSum(arr, i, cached);
        }

        int result = cached[0];
        if(result == 0) {
            result = Arrays.stream(arr).max().orElse(0);
        }

        return result;
    }

    private static int maxSubsetSum(int[] arr, int index, int[] cached) {
        int v1 = Math.max(arr[index], 0);
        if(index + 1 == arr.length) {
            return v1;
        }

        index++;
        int v2 = Math.max(arr[index], 0);

        index++;
        if(index < arr.length) {
            v1 += Math.max(cached[index], 0);
        }

        index++;
        if(index < arr.length) {
            v2 += Math.max(cached[index], 0);
        }

        return Math.max(Math.max(v1, v2), 0);
    }
}
