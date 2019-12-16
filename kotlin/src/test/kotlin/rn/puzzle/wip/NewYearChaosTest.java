package rn.puzzle.wip;

import org.junit.Assert;
import org.junit.Test;

public class NewYearChaosTest {

    @Test
    public void test3() {
        solve(new int[] {1,2,5,3,7,8,6,4}, 7);
        //               1 2 3 4 5 6 7 8
    }

    @Test
    public void test4() {
        solve(new int[] {2,4,5,3,1}, 6);
    }

    private int minimumBribes(int[] q) {
        int positive = 0;
        int negative = 0;

        for (int i = 0; i < q.length; i++) {
            int expected = i + 1;
            int current = q[i];
            int diff = expected - current;

            if(diff < -2) {
                return -1;
            }
            if(diff == -2) {
                if(q[i+1] - (expected + 1) == 0) {
                    positive++;
                    negative--;
                }
            }


            if(diff > 0) {
                positive += diff;
            } else {
                negative += diff;
            }
        }
        return positive + negative == 0 ? positive : -1;
    }

    private void solve(int[] q, int expected) {
        int res = minimumBribes(q);
        Assert.assertEquals(expected, res);
    }

    @Test
    public void test1() {
        solve(new int[] {2,1,5,3,4}, 3);
    }

    @Test
    public void test2() {
        solve(new int[] {2,5,1,3,4}, -1);
    }
}
