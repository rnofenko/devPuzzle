package rn.coursera.algtool.w4;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.StrConverter;

public class ClosestPointsTest {
    @Test
    public void test1() {
        run("[[4,4],[-2,-2],[-3,-4],[-1,3],[2,3],[-4,0],[1,1],[-1,-1],[3,-1],[-4,2],[-2,4]]", 1.414213);
    }

    private void run(String pointsStr, double expected) {
        int[][] points = StrConverter.to2dIntArray(pointsStr);
        double res = ClosestPoints.findClosestPoints(points);
        Assert.assertEquals(expected, res, 0.0001);
    }
}
