package rn.puzzle.structure.array.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ArrayManipulationTest {
    static long arrayManipulation(int n, int[][] queries) {
        Map<Integer, Long> open = new HashMap<>();
        Map<Integer, Long> close = new HashMap<>();
        for (int[] query : queries) {
            open.merge(query[0], (long) query[2], Long::sum);
            close.merge(query[1] + 1, (long) query[2], Long::sum);
        }
        long max = Long.MIN_VALUE;
        long current = 0;
        for (int i = 1; i <= n; i++) {
            Long plus = open.get(i);
            Long minus = close.get(i);
            if (plus != null) current += plus;
            if (minus != null) current -= minus;
            max = Math.max(max, current);
        }
        return max;
    }

    @Test
    public void test1() {
        int[][] ops = new int[][]{
                { 1,2,100 },
                { 2,5,100 },
                { 3,4,100 }
        };
        long res = arrayManipulation(5, ops);
        Assert.assertEquals(200, res);
    }
}
