package rn.puzzle.string.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

//https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
public class MinimumRemoveToMakeValidParenthesesTest {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> open = new Stack<>();
        StringBuilder res = new StringBuilder();
        int restIdx = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                open.push(i);
            } else if (c == ')') {
                if (open.size() > 1) {
                    open.pop();
                } else {
                    if (open.isEmpty()) {
                        res.append(s, restIdx, i);
                    } else {
                        res.append(s, restIdx, i + 1);
                        open.pop();
                    }
                    restIdx = i + 1;
                }
            }
        }

        int prevIdx = restIdx;
        for (Integer idx : open) {
            res.append(s, prevIdx, idx);
            prevIdx = idx + 1;
        }
        res.append(s.substring(prevIdx));

        return res.toString();
    }

    @Test
    public void test1() {
        Assert.assertEquals("lee(t(c)o)de", minRemoveToMakeValid("lee(t(c)o)de)"));
    }

    @Test
    public void test2() {
        Assert.assertEquals("ab(c)d", minRemoveToMakeValid("a)b(c)d"));
    }

    @Test
    public void test3() {
        Assert.assertEquals("", minRemoveToMakeValid("))(("));
    }
}
