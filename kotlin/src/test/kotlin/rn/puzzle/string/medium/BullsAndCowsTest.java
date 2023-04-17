package rn.puzzle.string.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class BullsAndCowsTest {
    public String getHint(String secret, String guess) {
        int a = 0;
        Map<Character, Integer> mapB = new HashMap<>();
        Map<Character, Integer> guessMap = countChars(guess);
        for (int i = 0; i < secret.length(); i++) {
            char secretC = secret.charAt(i);
            if (secretC == guess.charAt(i)) {
                a++;
                guessMap.compute(secretC, (_c, count) -> count == null || count == 0 ? null : count - 1);
            } else if (guessMap.containsKey(secretC)) {
                mapB.compute(secretC, (_c, count) -> count == null ? 1 : count + 1);
            }
        }
        int b = 0;
        for (Character c : mapB.keySet()) {
            int min = Math.min(getNotNull(guessMap, c), getNotNull(mapB, c));
            b += min;
        }
        return a + "A" + b + "B";
    }

    private int getNotNull(Map<Character, Integer> map, char c) {
        Integer v = map.get(c);
        return v == null ? 0 : v;
    }

    private Map<Character, Integer> countChars(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            map.compute(c, (_c, count) -> count == null ? 1 : count + 1);
        }
        return map;
    }

    @Test
    public void test3() {
        run("11", "10", "1A0B");
    }

    @Test
    public void test1() {
        run("1807", "7810", "1A3B");
    }

    @Test
    public void test2() {
        run("1123", "0111", "1A1B");
    }

    private void run(String secret, String guess, String expected) {
        Assert.assertEquals(expected, getHint(secret, guess));
    }
}
