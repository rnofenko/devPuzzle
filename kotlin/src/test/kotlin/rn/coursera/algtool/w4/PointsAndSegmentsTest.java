package rn.coursera.algtool.w4;

import org.junit.Test;
import rn.tool.Ass;
import rn.tool.StrConverter;

public class PointsAndSegmentsTest {
    @Test
    public void test1() {
        run("-10", "10", "-100 100 0", "0 0 1");
    }

    @Test
    public void test2() {
        run("-10", "10", "9 10 11", "1 1 0");
    }

    private void run(String starts, String end, String points, String expected) {
        int[] res = PointsAndSegments.fastCountSegments(StrConverter.toIntArray(starts),
                StrConverter.toIntArray(end),
                StrConverter.toIntArray(points));

        Ass.assertIntArrayEquals(StrConverter.toIntArray(expected), res);
    }
}
