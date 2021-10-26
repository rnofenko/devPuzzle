package rn.puzzle.structure.array.medium;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.FileHelper;
import rn.tool.Rand;
import rn.tool.StrConverter;
import rn.tool.combination.CombinatorFactory;
import rn.tool.combination.IPermutation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

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
    public void test8() {
        test("[3,2,1,2]", 8);
        test("[2,1,3,2,1,2]", 11);
        test("[1,3,2,1,2]", 9);
    }

    @Test
    public void testFile3() {
        testFile("03", 160929);
    }

    @Test
    public void compare1() {
        for (int i = 0; i < 100; i++) {
            int[] data = Rand.INSTANCE.newArray(6, 100);
            compare(data);
        }
    }

    private void compare(String arrStr) {
        int[] a = StrConverter.INSTANCE.toIntArray(arrStr);
        compare(a);
    }

    private void compare(int[] a) {
        long res = candies(a.length, a);
        bruteForce(a, (int)res);
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

    private void bruteForce(int[] a, int maxTotal) {
        int max = maxTotal - a.length + 1;
        IPermutation permutation = CombinatorFactory.permutation(a.length, 1, max);

        while (true) {
            int[] solution = permutation.next();
            if (solution == null) {
                System.out.println("end");
                return;
            }

            if (validate(solution, a, maxTotal)) {
                int sum = IntStream.of(solution).sum();
                if (sum == maxTotal) {
                    System.out.println("same");
                }
                if (sum < maxTotal) {
                    System.out.println(Arrays.toString(a));
                    System.out.println(Arrays.toString(solution));
                    System.out.println("" + sum + " " + maxTotal);
                    System.out.println("found");
                    return;
                }
            }
        }
    }

    private boolean validate(int[] s, int[] a, int max) {
        int sum = 0;
        for (int i = 0; i < s.length - 1; i++) {
            sum += s[i];
            if (sum > max) {
                return false;
            }

            if (a[i] == a[i + 1]) {
                continue;
            }
            if (s[i] == s[i + 1]) {
                return false;
            }

            boolean isUp = a[i + 1] > a[i];
            if (isUp != s[i + 1] > s[i]) {
                return false;
            }
        }
        return true;
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

        if (isUp) {
            total += currentLen;
        } else {
            total += Math.max(prevLen, currentLen);
        }
        total++;

        return total;
    }

    private void test(String str, long expected) {
        int[] a = StrConverter.INSTANCE.toIntArray(str);
        long res = candies(a.length, a);
        Assert.assertEquals(expected, res);
    }
}
