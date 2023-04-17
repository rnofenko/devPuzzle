package rn.puzzle.string.medium;

import org.junit.Assert;
import org.junit.Test;

public class ZigzagConversionTest {
    public String convert(String s, int numRows) {
        if (numRows < 2) {
            return s;
        }
        int len = s.length();
        StringBuilder[] lines = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            lines[i] = new StringBuilder();
        }

        int l = numRows * 2 - 2;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            int lineNo = i % l;
            if (lineNo >= numRows) {
                lineNo -= numRows;
                lineNo = numRows - 2 - lineNo;
            }
            lines[lineNo].append(c);
        }

        StringBuilder result = lines[0];
        for (int i = 1; i < numRows; i++) {
            result.append(lines[i].toString());
        }
        return result.toString();
    }

    public String convert2(String s, int numRows) {
        if (numRows < 2) {
            return s;
        }
        int len = s.length();
        char[] resChars = new char[len];
        int[] indexes = new int[numRows];
        for (int i = 1; i < numRows; i++) {
            indexes[i] = indexes[i - 1] + sizeOfLine(len, numRows, i - 1);
        }

        int l = numRows * 2 - 2;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            int lineNo = i % l;
            if (lineNo >= numRows) {
                lineNo -= numRows;
                lineNo = numRows - 2 - lineNo;
            }
            resChars[indexes[lineNo]++] = c;
        }

        return String.valueOf(resChars);
    }

    private int sizeOfLine(int len, int numRows, int lineNo) {
        int l = numRows * 2 - 2;
        int cyclesCount = len / l;
        int count = (lineNo == 0 || lineNo == numRows - 1) ? cyclesCount : cyclesCount * 2;
        int rest = len % l;
        if (rest < lineNo + 1) {
            return count;
        }
        rest -= numRows;
        if (rest >= numRows - lineNo - 1) {
            return count + 2;
        }

        return count + 1;
    }

    @Test
    public void test5() {
        run("abcde", 3, "aebdc");
    }

    @Test
    public void test4() {
        run("abcdefghijklmnop", 3, "aeimbdfhjlnpcgko");
    }

    @Test
    public void test1() {
        run("PAYPALISHIRING", 3, "PAHNAPLSIIGYIR");
    }

    @Test
    public void test2() {
        run("PAYPALISHIRING", 4, "PINALSIGYAHRPI");
    }

    @Test
    public void test3() {
        run("A", 1, "A");
    }

    private void run(String s1, int rows, String expected) {
        Assert.assertEquals(expected, convert(s1, rows));
        Assert.assertEquals(convert(s1, rows), convert2(s1, rows));
    }
}
