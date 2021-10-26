package rn.puzzle.structure.stack.easy;

import org.junit.Test;
import rn.tool.Ass;
import rn.tool.StrConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//https://www.hackerrank.com/challenges/waiter/problem
public class WaiterTest {

    @Test
    public void test1() {
        test("3 4 7 6 5", 1, "4 6 3 7 5");
    }

    @Test
    public void test2() {
        test("2 3 4 5 6 7", 1200, "2 4 6 3 5 7");
    }

    @Test
    public void test3() {
        test("3 3 4 4 9", 2, "4 4 9 3 3");
    }

    static int[] waiter(int[] input, int q) {
        Stack<Integer> a = new Stack<>();
        for (int i : input) {
            a.add(i);
        }

        int[] res = new int[input.length];
        int resIdx = 0;

        List<Integer> primes = generatePrimeNumbers(10000);

        for (int i = 0; i < q; i++) {
            Stack<Integer> newA = new Stack<>();
            Stack<Integer> b = new Stack<>();
            int prime = primes.get(i);
            while (!a.isEmpty()) {
                int v = a.pop();
                if (v % prime == 0) {
                    b.add(v);
                } else {
                    newA.add(v);
                }
            }

            while (!b.isEmpty()) {
                res[resIdx++] = b.pop();
            }

            a = newA;
        }

        while (!a.isEmpty()) {
            res[resIdx++] = a.pop();
        }

        return res;
    }

    static List<Integer> generatePrimeNumbers(int n) {
        boolean[] flags = new boolean[n];
        List<Integer> res = new ArrayList<>();

        int prime = 1;
        while (prime < n) {
            for (int i = prime + 1; i < n; i++) {
                if (!flags[i]) {
                    prime = i;
                    break;
                }
            }

            if (!res.isEmpty() && res.get(res.size() - 1) == prime) {
                return res;
            }
            res.add(prime);

            for (int i = prime; i < n; i+= prime) {
                flags[i] = true;
            }
        }
        return res;
    }

    private void test(String a, int q, String expected) {
        int[] res = waiter(StrConverter.toIntArray(a), q);
        Ass.assertIntArrayEquals(StrConverter.toIntArray(expected), res);
    }
}
