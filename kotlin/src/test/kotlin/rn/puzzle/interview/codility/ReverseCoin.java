package rn.puzzle.interview.codility;

import org.junit.Assert;
import org.junit.Test;

public class ReverseCoin {
    public int solution(int[] A) {
        int n = A.length;
        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            if (A[i] == A[i + 1])
                result = result + 1;
        }
        int r = 0;
        for (int i = 0; i < n; i++) {
            int count = 0;
            if (i > 0) {
                if (A[i - 1] != A[i])
                    count = count + 1;
                else
                    count = count - 1;
            }
            if (i < n - 1) {
                if (A[i + 1] != A[i])
                    count = count + 1;
                else
                    count = count - 1;
            }
            r = Math.max(r, count);
        }
        return result + r;
    }

    public int solution2(int[] A) {
        int n = A.length;
        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            if (A[i] == A[i + 1])
                result = result + 1;
        }
        int r = -1;
        for (int i = 0; i < n; i++) {
            int count = 0;
            if (i > 0) {
                if (A[i - 1] != A[i])
                    count = count + 1;
                else
                    count = count - 1;
            }
            if (i < n - 1) {
                if (A[i + 1] != A[i])
                    count = count + 1;
                else
                    count = count - 1;
            }
            r = Math.max(r, count);
        }
        return result + r;
    }

    @Test
    public void test1() {
        check(new int[] { 0,0,0,0,0 }, 3);
        check(new int[] { 0,1,0,0,0 }, 4);
        check(new int[] { 0,0,1,0,0 }, 4);
        check(new int[] { 0,0,0,1,0 }, 4);
        check(new int[] { 0,0,0,0,1 }, 4);
    }

    @Test
    public void test2() {
        check(new int[] { 0,1,0,1,0 }, 2);
    }

    @Test
    public void test3() {
        check(new int[] { 0,0,1,1 }, 2);
        check(new int[] { 0,1,0,1 }, 2);
        check(new int[] { 0,1,1,0 }, 2);
        check(new int[] { 1,0,0,1 }, 2);
        check(new int[] { 1,0,1,0 }, 2);
        check(new int[] { 1,1,0,0 }, 2);
        check(new int[] { 1,1 }, 0);
    }

    @Test
    public void test4() {
        check(new int[] { 0,1 }, 1);
        check(new int[] { 1,1 }, 0);
    }

    @Test
    public void test5() {
        check(new int[] { 0,0,0,1,1 }, 3);
        check(new int[] { 0,0,1,1,0 }, 3);
        check(new int[] { 0,1,1,0,0 }, 3);
        check(new int[] { 1,1,0,0,0 }, 3);
    }

    private void check(int[] data, int exp) {
        int r2 = solution2(data);
        Assert.assertEquals(exp, r2);
    }
}
