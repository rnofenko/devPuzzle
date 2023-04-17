package rn.coursera.algtool.w3;

import org.junit.Test;
import rn.tool.Ass;

public class CoveringSegmentsTest {
    @Test
    public void test1() {
        int[] res = CoveringSegments.runTest(new String[]{"1 3", "2 5", "3 6"});
        Ass.assertIntArrayEquals(new int[] {3}, res);
    }
}
