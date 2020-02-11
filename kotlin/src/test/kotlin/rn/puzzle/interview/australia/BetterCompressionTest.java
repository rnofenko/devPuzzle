package rn.puzzle.interview.australia;

import org.junit.Assert;
import org.junit.Test;

public class BetterCompressionTest {

    @Test
    public void test1() {
        String res = betterCompression("a3c9b2c1");
        Assert.assertEquals("a3b2c10", res);
    }

    public String betterCompression(String input) {
        if (input.length() < 3) {
            return input;
        }

        String regex = "(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)";
        String[] parts = input.split(regex);
        int[] res = new int['z' - 'a' + 1];

        for (int i = 0; i < parts.length; i += 2) {
            int key = parts[i].charAt(0) - 'a';
            int count = Integer.parseInt(parts[i + 1]);
            res[key] += count;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            int count = res[i];
            if (count == 0) continue;
            builder
                    .append((char)('a' + i))
                    .append(count);
        }

        return builder.toString();
    }
}
