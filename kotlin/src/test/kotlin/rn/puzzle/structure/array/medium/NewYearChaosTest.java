package rn.puzzle.structure.array.medium;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.Rand;
import rn.tool.Stopwatch;

public class NewYearChaosTest {

    @Test
    public void test3() {
        solve(new int[] {1,2,5,3,7,8,6,4}, 7);
        //               1 2 3 4 5 6 7 8
    }

    @Test
    public void test4() {
        solve(new int[] {3,1,5,6,4,2}, 7);
    }

    @Test
    public void test5() {
        solve(new int[] {2,4,5,3,1}, 6);
        //1 2 3 4 5     0 0 0 0 0
        //2 1 3 4 5    -1 1 0 0 0
        //2 3 1 4 5    -1-1 2 0 0
        //2 3 4 1 5    -1-1-1 3 0
        //2 3 4 5 1    -1-1-1-1 4
        //2 4 3 5 1    -1-2 0-1 4
        //2 4 5 3 1    -1-2-2 1 4


        //1 2 3 4   0 0 0 0
        //1 3 2 4   0-1 1 0
        //3 1 2 4  -2 1 1 0
        //3 2 1 4  -2 0 2 0


        //3 2 1 4  -2 0 2 0
        //2 3 1 4  -1-1 2 0
        //2 1 3 4  -1 1 0 0


        //2 4 3 5 1    -1-2 0-1 4
        //2 3 4 5 1    -1-1-1-1 4
    }

    @Test
    public void perf() {
        solvePerf(1000 * 1000, 1000 * 1000 * 100);
    }

    private int minimumBribesLoop(int[] q) {
        int swapsCount = 0;
        int startIndex = 0;

        boolean isNotSorted = true;
        while (isNotSorted) {
            isNotSorted = false;

            for (int i = startIndex; i < q.length; i++) {
                int diff = (i + 1) - q[i];
                if(diff == 0) {
                    startIndex++;
                } else {
                    break;
                }
            }

            for (int i = startIndex; i < q.length - 1; i++) {
                int diff1 = (i + 1) - q[i];
                int diff2 = (i + 2) - q[i + 1];

                if(diff1 < -2 || diff2 < -2) {
                    return -1;
                }

                if (diff1 < diff2) {
                    int temp = q[i];
                    q[i] = q[i + 1];
                    q[i + 1] = temp;
                    swapsCount++;

                    isNotSorted = true;
                }
            }
        }

        return swapsCount;
    }

    private int[] getDiff(int[] q) {
        int[] r = new int[q.length];

        for (int i = 0; i < q.length; i++) {
            int expected = i + 1;
            int current = q[i];
            r[i] = expected - current;
        }
        return r;
    }

    private void solve(int[] q, int expected) {
        int res = minimumBribesLoop(q);
        Assert.assertEquals(expected, res);
    }

    private void solvePerf(int size, int randsCount) {
        int[] q = createRandArray(size, randsCount);

        Stopwatch w = Stopwatch.Companion.start();
        minimumBribesLoop(q);
        w.show("TEST");
    }

    @Test
    public void test1() {
        solve(new int[] {2,1,5,3,4}, 3);
    }

    @Test
    public void test2() {
        solve(new int[] {2,5,1,3,4}, -1);
    }

    private int[] createRandArray(int size, int randsCount) {
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = i + 1;
        }

        for (int i = 0; i < randsCount; i++) {
            int p = Rand.INSTANCE.positiveInt(size - 1);
            if((p + 1) - a[p] == -2 || (p + 2) - a[p + 1] == -2) {
                continue;
            }

            int temp = a[p];
            a[p] = a[p + 1];
            a[p + 1] = temp;
        }

        return a;
    }
}
