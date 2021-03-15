package rn.puzzle.string.medium;

import org.junit.Assert;
import org.junit.Test;

public class DivisibilityOfStringsTests {

    public int findDividerSize(String s, String t) {
        if (!isDividable(s, t)) {
            return -1;
        }

        int size = 1;
        while (size < t.length() / 2 + 1) {
            int count = t.length() / size;
            if (size * count == t.length()) {
                if (isDividable(t, t.substring(0, size))) {
                    return size;
                }
            }

            size++;
        }

        return t.length();
    }

    public boolean isDividable(String s, String t) {
        int times = s.length() / t.length();
        if (times == 0 || times * t.length() != s.length()) {
            return false;
        }

        int i = 0;
        while (i < s.length()) {
            for (int j = 0; j < t.length(); j++, i++) {
                if (s.charAt(i) != t.charAt(j)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Test
    public void test1() {
        Assert.assertEquals(-1, findDividerSize("", "abc"));
        Assert.assertEquals(-1, findDividerSize("a", "abc"));
        Assert.assertEquals(-1, findDividerSize("abcd", "abc"));
        Assert.assertEquals(-1, findDividerSize("abcabcab", "abc"));
        Assert.assertEquals(3, findDividerSize("abcabcabc", "abc"));
    }

    @Test
    public void test2() {
        Assert.assertEquals(3, findDividerSize("abcabcabcabc", "abcabc"));
    }
}
