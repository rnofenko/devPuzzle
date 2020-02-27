package rn.puzzle.recursion.medium;

import org.junit.Assert;
import org.junit.Test;

public class BracketsGeneratorTest {

    @Test
    public void test1() {
        Assert.assertEquals("()", buildAllBrackets(1));
    }

    @Test
    public void test2() {
        Assert.assertEquals("(()) ()()", buildAllBrackets(2));
    }

    @Test
    public void test3() {
        Assert.assertEquals("((())) (()()) (())() ()(()) ()()()", buildAllBrackets(3));
    }

    private String buildAllBrackets(int n) {
        int[] a = new int[n * 2];
        StringBuilder b = new StringBuilder();
        build(0, a, b, 0, n);
        return b.toString().trim();
    }

    private void build(int ind, int[] a, StringBuilder b, int open, int n) {
        if (ind == n * 2) {
            for (int i : a) b.append(i == 0 ? '(' : ')');
            b.append(' ');
            return;
        }

        if (open < n) {
            a[ind] = 0;
            build(ind + 1, a, b, open + 1, n);
        }
        if (open > ind - open) {
            a[ind] = 1;
            build(ind + 1, a, b, open, n);
        }
    }
}
