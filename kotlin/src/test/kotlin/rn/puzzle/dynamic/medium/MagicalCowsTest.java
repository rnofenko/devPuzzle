package rn.puzzle.dynamic.medium;

import org.junit.Test;
import rn.tool.Ass;
import rn.tool.StrConverter;

import java.util.*;

//https://open.kattis.com/problems/magicalcows
public class MagicalCowsTest {
    @Test
    public void test1() {
        runTest(1, "1 1 1 1 1", "0 1 2 3 4", "5 10 20 40 80");
    }

    @Test
    public void test2() {
        runTest(2, "1 2 1 2 1", "0 1 2", "5 7 14");
    }

    @Test
    public void test3() {
        runTest(8, "1 3 2 1", "4", "16");
    }

    @Test
    public void test4() {
        runTest(4, "3", "4", "16");
    }

    @Test
    public void test5() {
        runTest(1, "1", "50", "1125899906842624");
    }

    @Test
    public void test6() {
        runTest(1, "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1", "50", "23643898043695104");
    }

    public static long[] calc(int c, int[] n, int[] m) {
        int maxM = Arrays.stream(m).max().orElse(0);
        long[] farmCounts = calc(c, n, maxM);
        long[] res = new long[m.length];
        for (int i = 0; i < m.length; i++) {
            res[i] = farmCounts[m[i]];
        }
        return res;
    }

    private static long[] calc(int c, int[] n, int m) {
        long[] farmCounts = new long[m + 1];
        farmCounts[0] = n.length;

        Map<Long, Long> farms = new HashMap<>();
        for (long count : n) {
            farms.compute(count, (key, v) -> v == null ? 1 : v + 1);
        }

        int day = 1;
        while(day <= m) {
            Map<Long, Long> newFarms = new HashMap<>();
            long total = 0;
            for (Map.Entry<Long, Long> entry : farms.entrySet()) {
                long key = entry.getKey() * 2;
                long count = entry.getValue();
                total += count;
                if (key > c) {
                    newFarms.compute(key / 2, (k, v) -> v == null ? count * 2 : v + count * 2);
                    total += count;
                } else {
                    newFarms.compute(key, (k, v) -> v == null ? count : v + count);
                }
            }

            farms = newFarms;
            farmCounts[day] = total;
            day++;
        }

        return farmCounts;
    }

    public static void runFromConsole() {
        Scanner scan = new Scanner(System.in);
        int c = scan.nextInt();
        int nCount = scan.nextInt();
        int mCount = scan.nextInt();

        int[] n = new int[nCount];
        for (int i = 0; i < nCount; i++) {
            n[i] = scan.nextInt();
        }
        int[] m = new int[mCount];
        for (int i = 0; i < mCount; i++) {
            m[i] = scan.nextInt();
        }

        long[] res = calc(c, n, m);
        for (long r : res) {
            System.out.println(r);
        }
    }

    private void runTest(int c, String nStr, String mStr, String expectedStr) {
        int[] n = StrConverter.toIntArray(nStr);
        int[] m = StrConverter.toIntArray(mStr);
        long[] expected = StrConverter.toLongArray(expectedStr);
        long[] res = calc(c, n, m);
        Ass.assertLongArrayEquals(expected, res);
    }
}
