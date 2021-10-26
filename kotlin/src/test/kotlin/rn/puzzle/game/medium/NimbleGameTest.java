package rn.puzzle.game.medium;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.StrConverter;

public class NimbleGameTest {

    @Test
    public void test1() {
        test("0 2 3 0 6", "First");
    }

    @Test
    public void testAll() {
        test("0 0 0 0", "Second");
        test("0 1", "First");
        test("0 2", "Second");
        test("0 0 1", "First");
    }

    @Test
    public void test01() {
        test("383 886 777 915 793 335 386 492 649 421 362 27 690 59 763 926 540 426 172 736 211 368 567 429 782 530 862 123 67 135 929 802 22 58 69 167 393 456 11 42 229 373 421 919 784 537 198 324 315 370 413 526 91 980 956 873 862 170 996 281 305 925 84 327 336 505 846 729 313 857 124 895 582 545 814 367 434 364 43 750 87 808 276 178 788 584 403 651 754 399 932 60 676 368 739 12 226 586 94 539", "First");
    }

    static String nimbleGame(int[] s) {
        int nim = 0;
        for(int i = 1; i < s.length; i++) {
            int v = s[i] % 2;
            for (int j = 0; j < v; j++) {
                nim = nim ^ i;
            }
        }

//        String bin = Integer.toBinaryString(nim);
//        int r = 0;
//        for (int i = 0; i < bin.length(); i++) {
//            r = r ^ Integer.parseInt(bin.charAt(i) + "");
//        }

        return nim != 0 ? "First" : "Second";
    }

    private void test(String a, String winner) {
        Assert.assertEquals(winner, nimbleGame(StrConverter.toIntArray(a)));
    }
}
