package rn.puzzle.search.medium;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.FileHelper;
import rn.tool.StrConverter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountTripletsTest {
    @Test
    public void test1() {
        long res = countTriplets(StrConverter.toLongList("1 2 2 4"), 2);
        Assert.assertEquals(2, res);
    }

    @Test
    public void test2() {
        long res = countTriplets(StrConverter.toLongList("1 5 5 25 125"), 5);
        Assert.assertEquals(4, res);
    }

    @Test
    public void test3() {
        long res = countTriplets(StrConverter.toLongList("1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1"), 1);
        Assert.assertEquals(161700, res);
    }

    @Test
    public void test1_2() {
        Assert.assertEquals(0, countTriplets(StrConverter.toLongList("1 1"), 1));
    }

    @Test
    public void test1_3() {
        Assert.assertEquals(1, countTriplets(StrConverter.toLongList("1 1 1"), 1));
    }

    @Test
    public void test3_2_2_1() {
        Assert.assertEquals(4, countTriplets(StrConverter.toLongList("1 1 3 3 9"), 3));
    }

    @Test
    public void input06() {
        List<String> lines = FileHelper.load("c:/temp/input06.txt");
        long r = Long.parseLong(lines.get(0).split(" ")[1]);
        List<Long> list = StrConverter.toLongList(lines.get(1));
        Assert.assertEquals(2325652489L, countTriplets(list, r));
    }

    static long countTriplets(List<Long> arr, long r) {
        Map<Long, Integer> rightMap = new HashMap<>();
        for (long v3 : arr) {
            rightMap.compute(v3, (k, v) -> v == null ? 1 : v + 1);
        }
        Map<Long, Integer> leftMap = new HashMap<>();
        long totalCount = 0;
        for (int i = 0; i < arr.size(); i++) {
            long v2 = arr.get(i);
            rightMap.compute(v2, (k, v) -> v - 1);
            if (i > 0 && i + 1 < arr.size() && v2 % r == 0) {
                totalCount += calculate(v2, leftMap, rightMap, r);
            }
            leftMap.compute(v2, (k, v) -> v == null ? 1 : v + 1);
        }
        return totalCount;
    }

    private static long calculate(long v2, Map<Long, Integer> leftMap, Map<Long, Integer> rightMap, long r) {
        long v1 = v2 / r;
        if (!leftMap.containsKey(v1)) {
            return 0;
        }
        long count1 = leftMap.get(v1);
        long v3 = v2 * r;
        if (!rightMap.containsKey(v3)) {
            return 0;
        }
        long count3 = rightMap.get(v3);

        return count1 * count3;
    }
}
