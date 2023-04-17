package rn.puzzle.structure.array.medium;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.StrConverter;

import java.util.Stack;
import java.util.stream.IntStream;

public class CountSubArraysTest {

    @Test
    public void test1() {
        int[] res = countSubArrays(StrConverter.toIntArray("3 4 1 6 2"));
        Assert.assertEquals(5, res[3]);
    }

    int[] countSubArrays(int[] arr) {
        int[] res = countSubArraysLeftToRight(arr);
        int len = arr.length;
        int[] reverseArray = IntStream.range(0,len).map(i -> arr[len-i-1]).toArray();
        int[] reverseRes = countSubArraysLeftToRight(reverseArray);

        for (int i = 0; i < len;i++) {
          res[i] += 1 + reverseRes[len - i - 1];
        }
        return res;
    }

    private int[] countSubArraysLeftToRight(int[] arr) {
        Stack<Integer> prevPeaks = new Stack<>();
        int[] res = new int[arr.length];
        int points = 0;
        res[0] = points;
        for(int i = 1; i < arr.length; i++) {
            int prevV = arr[i -1];
            int v = arr[i];

            if (v > prevV) {
                points++;
                while(!prevPeaks.isEmpty() && v > arr[prevPeaks.peek()]) {
                    int prevPeak = prevPeaks.pop();
                    points += res[prevPeak];
                    points += i - prevPeak - 1;
                }
            } else {
                if (points > 0) {
                    prevPeaks.push(i - 1);
                    points = 0;
                }
            }
            res[i] = points;
        }

        return res;
    }
}
