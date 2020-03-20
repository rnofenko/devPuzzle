package rn.puzzle.wip;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.FileHelper;
import rn.tool.StringToArrayConverter;
import rn.tool.combination.CombinatorFactory;
import rn.tool.combination.ICombinator;

import java.util.List;

public class TeacherCandiesTest {

    @Test
    public void test1() {
        test("[4,6,4,5,6,2]", 10);
    }

    @Test
    public void test2() {
        test("[2,4,2,6,1,7,8,9,2,1]", 19);
    }

    @Test
    public void test3() {
        test("[2,4,3,5,2,6,4,5]", 12);
    }

    @Test
    public void test4() {
        test("[1,2,2]", 4);
        test("[1,2]", 3);
        test("[2,1]", 3);
        test("[1,1]", 2);
        test("[1,2,2,3,4,4,3,2,2,1]", 18);
    }

    @Test
    public void test5() {
        test("[1,1,1,1,1]", 5);
    }

    @Test
    public void test7() {
        test("[2,1,1,1,1]", 6);
        test("[1,2,1,1,1]", 6);
        test("[1,2,1,2,1]", 7);
        test("[1,2,2,2,1]", 7);
        test("[1,1,1,1,2]", 6);
        test("[1,2,2,2,2]", 6);
        test("[1,2,1,2,2]", 7);
        test("[2,2,1,1,2]", 7);
        test("[1,2,2,1,1]", 7);
    }

    @Test
    public void testFile3() {
        testFile("03", 160929);
    }

    @Test
    public void compare1() {
        compare("[4,6,4,5,6,2]");
    }

    private void compare(String arrStr) {
        int[] a = StringToArrayConverter.INSTANCE.stringToIntArray(arrStr);
        long res = candies(a.length, a);
        bruteForce(a, res);
    }

    private void testFile(String no, long expected) {
        String path = "C:\\src\\data\\devpuzzle\\candies\\input"+no+".txt";
        List<String> lines = FileHelper.INSTANCE.load(path);
        int[] a = new int[Integer.parseInt(lines.get(0))];
        for (int i = 0; i < a.length; i++) {
            a[i] = Integer.parseInt(lines.get(i + 1));
        }

        long res = candies(a.length, a);
        Assert.assertEquals(expected, res);
    }

    @Test
    public void test6() {
        test("[1,2]", 3);
        test("[1,2,3]", 6);
        test("[1,2,3,4]", 10);
        test("[1,2,3,4,1]", 11);
        test("[1,2,3,4,1,2]", 13);
        test("[1,2,3,4,1,2,4]", 16);
        test("[1,2,3,4,1,2,4,3]", 17);
        test("[1,2,3,4,1,2,4,3,2]", 19);
        test("[1,2,3,4,1,2,4,3,2,1]", 23);
    }

    private void bruteForce(int[] a, long maxTotal) {
        int max = (int)maxTotal - a.length + 1;
        ICombinator combinator = CombinatorFactory.create(max, a.length);
    }

    private long candies(int n, int[] a) {
        long total = 0;
        int startIndex = 0;
        for (int i = 0; i < n - 1; i++) {
            if (a[i] == a[i + 1]) {
                total += candies(a, startIndex, i);
                startIndex = i + 1;
            }
        }
        total += candies(a, startIndex, n -1);

        return total;
    }

    private long candies(int[] a, int startIndex, int endIndex) {
        int n = endIndex - startIndex + 1;
        if (n < 2) {
            return Math.max(n, 0);
        }

        long total = 0;
        boolean isUp = a[startIndex + 1] > a[startIndex];
        int currentLen = 0;
        int prevLen = 0;
        for (int i = startIndex; i < endIndex; i++) {
            if (a[i] < a[i + 1] != isUp) {
                isUp = !isUp;
                if (isUp) {
                    total += Math.max(prevLen, currentLen);
                }
                prevLen = currentLen;
                currentLen = 0;
            }
            currentLen++;
            total += currentLen;
        }
        total += Math.max(prevLen, currentLen);
        total++;

        return total;
    }

    private void test(String str, long expected) {
        int[] a = StringToArrayConverter.INSTANCE.stringToIntArray(str);
        long res = candies(a.length, a);
        Assert.assertEquals(expected, res);
    }
}
