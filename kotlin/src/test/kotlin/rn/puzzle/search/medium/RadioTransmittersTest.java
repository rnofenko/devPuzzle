package rn.puzzle.search.medium;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import rn.tool.FileHelper;
import rn.tool.Rand;
import rn.tool.StrConverter;
import rn.tool.combination.CombinatorFactory;
import rn.tool.combination.ICombinator;

import java.util.Arrays;
import java.util.List;

//https://www.hackerrank.com/challenges/hackerland-radio-transmitters/problem
public class RadioTransmittersTest {

    @Test
    public void test1() {
        test("1 2 3 4 5", 1, 2);
    }

    @Test
    public void test2() {
        test("7 2 4 6 5 9 12 11", 2, 3);
        test("1 5 10", 3, 3);
    }

    @Test
    public void test6() {
        test("1 20 100", 3, 3);
    }

    @Test
    public void test3() {
        test("1 3 4 5 6 7 8 9 10", 3, 2);
    }

    @Test
    public void test4() {
        test("39 37 37 25 80", 3,3);
    }

    @Test
    public void test5() {
        test("8 10 13 18 66", 3,3);
    }

    @Test
    public void test7() {
        test("1 2 4 89", 3,2);
    }

    @Test
    public void test06() {
        testFromFile("input06.txt", 620);
    }

    @Test
    @Ignore
    public void randBrute() {
        for (int i = 0; i < 1000; i++) {
            int[] a = Rand.INSTANCE.newArray(4, 100);
            int[] comb = brute(a, 3);
            if (comb != null) {
                System.out.println("FOUND");
            }
        }

    }

    static int radioTransmitters(int[] x, int k) {
        Arrays.sort(x);

        int further = x[0];
        int count = 0;
        int lastH = 0;
        for (int i = 0; i < x.length; i++) {
            int h = x[i];
            if (i + 1 < x.length && h == x[i + 1]) continue;
            int covered = h - k;
            if (covered >= further) {
                count++;
                further = (covered == further ? h : lastH) + k + 1;

                for (int j = i; j < x.length; j++) {
                    int h2 = x[j];
                    if (h2 >= further) {
                        further = h2;
                        break;
                    }
                }
            }

            lastH = h;
        }
        if (further <= lastH) {
            count++;
        }
        return count;
    }

    static int[] brute(int[] x, int k) {
        x = Arrays.stream(x).distinct().toArray();
        int count = radioTransmitters(x, k) - 1;
        ICombinator combinator = CombinatorFactory.create(x.length - 1, count);
        while (true) {
            int[] comb = combinator.next();
            if (comb == null) {
                return null;
            }
            if (isValid(x, k, comb)) {
                return comb;
            }
        }
    }

    static boolean isValid(int[] x, int k, int[] comb) {
        int idx = 0;
        int r = x[comb[idx++]];
        int vi = 0;
        int h = x[vi++];

        while (true) {
            while (h > r + k) {
                if (idx >= comb.length) {
                    return false;
                }
                r = x[comb[idx++]];
            }
            if (h < r - k) {
                return false;
            }
            if (vi >= x.length) {
                return true;
            }
            h = x[vi++];
        }
    }

    private void testFromFile(String fileName, int expected) {
        List<String> lines = FileHelper.load("/temp/" + fileName);
        int k = Integer.parseInt(lines.get(0).split(" ")[1]);
        test(lines.get(1), k, expected);
    }

    private void test(String a, int k, int expected) {
        Assert.assertEquals(expected, radioTransmitters(StrConverter.toIntArray(a), k));
    }
}
