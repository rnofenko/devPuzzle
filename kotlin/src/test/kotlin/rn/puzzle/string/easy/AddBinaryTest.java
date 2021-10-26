package rn.puzzle.string.easy;

import org.junit.Assert;
import org.junit.Test;

public class AddBinaryTest {
    public String addBinary(String a, String b) {
        int ai = a.length() - 1;
        int bi = b.length() - 1;
        StringBuilder res = new StringBuilder();
        int buffer = 0;
        while (ai >= 0 || bi >= 0) {
            int ca = ai >= 0 ? a.charAt(ai) - '0' : 0;
            int cb = bi >= 0 ? b.charAt(bi) - '0' : 0;

            int sum = ca + cb + buffer;
            buffer = sum / 2;
            res.append(sum % 2);

            ai--;
            bi--;
        }
        if (buffer == 1) {
            res.append("1");
        }

        return res.reverse().toString();
    }

    @Test
    public void test1() {
        Assert.assertEquals("10101", addBinary("1010", "1011"));
    }
}