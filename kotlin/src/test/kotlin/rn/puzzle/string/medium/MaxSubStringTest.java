package rn.puzzle.string.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MaxSubStringTest {

    @Test
    public void banana() {
        String res = maxSubstring("banana");
        Assert.assertEquals("nana", res);
    }

    @Test
    public void abc() {
        String res = maxSubstring("abc");
        Assert.assertEquals("c", res);
    }

    @Test
    public void baba() {
        String res = maxSubstring("baba");
        Assert.assertEquals("baba", res);
    }

    @Test
    public void aaaaa() {
        String res = maxSubstring("aaaaa");
        Assert.assertEquals("aaaaa", res);
    }

    public static String maxSubstring(String s) {
        if (s.isEmpty()) {
            return "";
        }

        List<Integer> indices = IntStream.range(0, s.length()).boxed().collect(Collectors.toList());
        int size = 0;

        while (true) {
            indices = maxSubstring(s, indices);
            if (indices.size() == 1) {
                return s.substring(indices.get(0) - size);
            }
            size++;
            for (int i = 0; i < indices.size(); i++) {
                indices.set(i, indices.get(i) + 1);
            }
        }
    }

    public static List<Integer> maxSubstring(String s, List<Integer> startIndices) {
        List<Integer> indices = new ArrayList<>();
        char maxChar = Character.MIN_VALUE;
        for (int i : startIndices) {
            if (i >= s.length()) {
                break;
            }
            char c = s.charAt(i);
            if (c > maxChar) {
                indices = new ArrayList<>();
                maxChar = c;
            }
            if (maxChar == c) {
                indices.add(i);
            }
        }

        return indices;
    }
}
