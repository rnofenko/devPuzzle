package rn.puzzle.string.medium;

import org.junit.Assert;
import org.junit.Test;

public class StrStrTest {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }
        int[] dfa = buildDfa(needle);

        int k = 0;
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(k)) {
                k++;
                if (k == needle.length()) {
                    return i - k + 1;
                }
            } else {
                if (k > 0 && haystack.charAt(i) != needle.charAt(k)) {
                    k = dfa[k - 1];
                    i--;
                }
            }
        }

        return -1;
    }

    private int[] buildDfa(String pattern) {
        int j = 0;
        int[] dfa = new int[pattern.length()];

        for (int i = 1; i < dfa.length; i++) {
            while (j > 0 && pattern.charAt(j) != pattern.charAt(i)) {
                j = dfa[j - 1];
            }
            if (pattern.charAt(j) == pattern.charAt(i)) {
                j++;
            }
            dfa[i] = j;
        }

        return dfa;
    }

    @Test
    public void test1() {
        solve("hello", "ll", 2);
    }

    @Test
    public void test2() {
        solve("abababacaa", "ababaca", 2);
    }

    @Test
    public void test3() {
        solve("abababc", "ababc", 2);
    }

    @Test
    public void test4() {
        solve("ababbabbb", "bbb", 6);
    }

    private void solve(String text, String pattern, int index) {
        Assert.assertEquals(index, strStr(text, pattern));
    }
}
