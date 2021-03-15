package rn.puzzle.array.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckEncryptionTests {
    public int maxDivisibility(List<Integer> keys) {
        Map<Integer, Integer> keysCount = new HashMap<>();
        for (Integer key : keys) {
            keysCount.merge(key, 1, Integer::sum);
        }

        int maxD = Integer.MIN_VALUE;
        for (int key : keys) {
            int d = calcDivisibility(key, keysCount);
            maxD = Math.max(d, maxD);
        }
        return maxD;
    }

    private int calcDivisibility(int key, Map<Integer, Integer> keysCount) {
        int count = keysCount.containsKey(1) ? 1 : 0;
        for (int i = 2; i <= key; i++) {
            int n = key / i;
            if (n <= 1) break;
            if (n * i != key) continue;
            Integer nCount = keysCount.get(n);
            if (nCount != null) {
                count += nCount;
            }
        }
        return count + 1;
    }

    @Test
    public void test_maxDivisibility() {
        Assert.assertEquals(2, maxDivisibility(Arrays.asList(2,4)));
        Assert.assertEquals(3, maxDivisibility(Arrays.asList(2,2,4)));
        Assert.assertEquals(3, maxDivisibility(Arrays.asList(1,2,3,4)));
        Assert.assertEquals(3, maxDivisibility(Arrays.asList(1,2,3,4,5)));
        Assert.assertEquals(4, maxDivisibility(Arrays.asList(1,2,3,4,5,6)));
    }
}
