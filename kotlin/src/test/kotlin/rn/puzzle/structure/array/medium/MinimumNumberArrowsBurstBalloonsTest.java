package rn.puzzle.structure.array.medium;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.StrConverter;

import java.util.Arrays;
import java.util.Comparator;

public class MinimumNumberArrowsBurstBalloonsTest {

    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
        int count = 0;
        int earliestEnd = Integer.MAX_VALUE;
        for (int[] point : points) {
            int currentStart = point[0];
            if (currentStart > earliestEnd) {
                count++;
                earliestEnd = Integer.MAX_VALUE;
            }
            earliestEnd = Math.min(earliestEnd, point[1]);
        }

        return count + 1;
    }

    @Test
    public void test1() {
        run("[[10,16],[2,8],[1,6],[7,12]]", 2);
    }

    @Test
    public void test2() {
        run("[[1,2],[3,4],[5,6],[7,8]]", 4);
    }

    @Test
    public void test3() {
        run("[[1,2],[2,3],[3,4],[4,5]]", 2);
    }

    private void run(String pointsStr, int expected) {
        int[][] points = StrConverter.to2dIntArray(pointsStr);
        int res = findMinArrowShots(points);
        Assert.assertEquals(expected, res);
    }
}
