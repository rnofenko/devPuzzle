package rn.puzzle.string.easy;

import org.junit.Assert;
import org.junit.Test;

public class RotationalCipherTest {

    @Test
    public void test1() {
        Assert.assertEquals("Cheud-726", rotationalCipher("Zebra-493", 3));
    }

    String rotationalCipher(String input, int rotationFactor) {
        char[] rotated = new char[input.length()];
        for(int i = 0; i < input.length(); i++) {
            rotated[i] = newChar(input.charAt(i), rotationFactor);
        }
        return String.valueOf(rotated);
    }

    char newChar(char from, int rotationFactor) {
        if (from >= 'A' && from <= 'Z') {
            return newChar(from, rotationFactor, 'A', 26);
        }
        if (from >= 'a' && from <= 'z') {
            return newChar(from, rotationFactor, 'a', 26);
        }
        if (from >= '0' && from <= '9') {
            return newChar(from, rotationFactor, '0', 10);
        }
        return from;
    }

    char newChar(char from, int rotationFactor, char startChar, int alphabetSize) {
        int shifted = (from - startChar + rotationFactor) % alphabetSize;
        return (char)(startChar + shifted);
    }
}
