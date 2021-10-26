package rn.puzzle.dynamic.medium;

import org.junit.Assert;
import org.junit.Test;

//https://www.hackerrank.com/challenges/construct-the-array/problem
public class ConstructTheArrayTest {

    static long countArray(int n, int k, int x) {
        long m = 1000000007;
        int pos = n - 2;
        long nextOnePart = 1;
        boolean isPos = false;
        while (true) {
            long current = nextOnePart * k;
            current += isPos ? 1 : -1;

            if (pos == 1) {
                long result = current - nextOnePart;
                if (x == 1) {
                    result -= isPos ? 1 : -1;
                }
                return result % m;
            }

            nextOnePart = (current - nextOnePart) % m;

            pos--;
            isPos = !isPos;
        }
    }

    @Test
    public void test3() {
        test(942,77,13,804842436);
    }

    @Test
    public void test2() {
        test(761,99,1,236568308);
    }

    @Test
    public void test1() {
        test(4,3,2,3);
    }

    @Test
    public void test1_2() {
        test(5,5,3,51);
    }

    @Test
    public void test1_3() {
        test(6,5,3,205);
    }

    private void test(int n, int k, int x, long expected) {
        Assert.assertEquals(expected, countArray(n,k,x));
    }
}
