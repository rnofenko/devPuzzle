package rn.puzzle.string.hard;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/
public class CountUniqueCharactersTest {
    public int uniqueLetterString(String s) {
        CharIndexes charIndexes = new CharIndexes();
        int totalPoints = 0;
        int prevPoints = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            int newPoints;
            if (charIndexes.isUnique(c)) {
                newPoints = prevPoints + i + 1;
            } else {
                int n = charIndexes.getNegative(c);
                int p = charIndexes.getPositive(c, i);
                newPoints = prevPoints - n + p;
            }
            prevPoints = newPoints;
            totalPoints += newPoints;

            charIndexes.addIndex(c, i);
        }

        return totalPoints;
    }

    private static class CharIndexes {
        private final Map<Character, int[]> _charIndexes = new HashMap<>();

        boolean isUnique(char c) {
            return !_charIndexes.containsKey(c);
        }

        int getNegative(char c) {
            int[] indexes = _charIndexes.get(c);
            if (indexes[1] == 0) {
                return indexes[2];
            }

            return indexes[2] - indexes[1];
        }

        int getPositive(char c, int currentIndex) {
            int[] indexes = _charIndexes.get(c);
            return currentIndex - indexes[2] + 1;
        }

        void addIndex(char c, int i) {
            int[] indexes = _charIndexes.computeIfAbsent(c, k -> new int[3]);
            indexes[0] = i + 1;
            Arrays.sort(indexes);
        }
    }

    @Test
    public void test3() {
        Assert.assertEquals(50, uniqueLetterString("ABEDFE"));
    }

    @Test
    public void test1() {
        Assert.assertEquals(20, uniqueLetterString("ABCD"));
    }

    @Test
    public void test2() {
        Assert.assertEquals(92, uniqueLetterString("LEETCODE"));
    }

    @Test
    public void test2a() {
        Assert.assertEquals(64, uniqueLetterString("LEETCOD"));
    }
}
