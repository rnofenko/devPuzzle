package rn.puzzle.structure.array.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class PairAllSumsTest {
    @Test
    public void test1() {
        int[] arr = {1, 2, 3, 4, 3};
        int res = numberOfWays(arr, 6);
        Assert.assertEquals(2, res);
    }

    @Test
    public void test2() {
        int[] arr = {1, 5, 3, 3, 3};
        int res = numberOfWays(arr, 6);
        Assert.assertEquals(4, res);
    }

    int numberOfWays(int[] arr, int k) {
        Map<Integer, Integer> met = new HashMap<>();
        int sum = 0;
        for(int v : arr) {
            Integer count = met.get(v);
            int peer = k - v;
            Integer peerCount = met.get(peer);
            if (peerCount != null) {
                sum += peerCount;
            }
            met.put(v, count == null ? 1 : count + 1);
        }

        return sum;
    }
}
