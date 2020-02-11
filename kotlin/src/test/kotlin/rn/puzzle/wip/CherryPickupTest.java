package rn.puzzle.wip;

import org.junit.Assert;
import org.junit.Test;
import rn.puzzle.dynamic.hard.cherry.CherryPickupSolver;
import rn.puzzle.dynamic.hard.cherry.GraphBuilder;
import rn.tool.StringToArrayConverter;

public class CherryPickupTest {

    @Test
    public void test0() {
        String gridStr =
                "[[1, 1]," +
                " [1, 1]]";
        test(gridStr, 4);

        gridStr =
                "[[0, 1]," +
                " [1, 1]]";
        test(gridStr, 3);

        gridStr =
                "[[1, 1]," +
                " [1, 0]]";
        test(gridStr, 3);

        gridStr =
                "[[0, 1]," +
                " [1, 0]]";
        test(gridStr, 2);
    }

    @Test
    public void test1() {
        String gridStr =
                "[[0, 1, -1]," +
                " [1, 0, -1]," +
                " [1, 1,  1]]";

        test(gridStr, 5);
    }

    @Test
    public void test2() {
        String gridStr =
                "[[0, 1, 0]," +
                " [0, 1, 1]," +
                " [1, 1, 0]," +
                " [0, 1, 0]]";

        print(gridStr);
        test(gridStr, 6);
    }

    @Test
    public void test5() {
        String gridStr =
                "[[0, 1, 0]," +
                " [1, 1, 0]," +
                " [0, 1, 1]," +
                " [0, 1, 0]]";

        test(gridStr, 8);
    }

    @Test
    public void test3() {
        String gridStr =
                 "[[0, 1, 0, 0]," +
                 " [0, 1, 1, 0]," +
                 " [1, 1, 0, 0]," +
                 " [0, 1, 1, 0]," +
                 " [1, 1, 0, 0]]";

        print(gridStr);
        test(gridStr, 8);
    }

    @Test
    public void test4() {
        String gridStr =
                "[[0, 0, 0, 1, 0, 0]," +
                " [0, 0, 0, 1, 1, 1]," +
                " [0, 0, 0, 1, 0, 0]," +
                " [1, 1, 1, 0, 0, 0]," +
                " [0, 1, 0, 0, 0, 0]," +
                " [0, 1, 0, 0, 0, 0]]";

        print(gridStr);
        test(gridStr, 8);
    }

    private void test(String gridStr, int expected) {
        int[][] grid = StringToArrayConverter.INSTANCE.stringTo2dIntArray(gridStr);
        int res = CherryPickupSolver.cherryPickup(grid);
        Assert.assertEquals(expected, res);
    }

    private void print(String gridStr) {
        int[][] grid = StringToArrayConverter.INSTANCE.stringTo2dIntArray(gridStr);
        GraphBuilder builder = new GraphBuilder(grid);
        builder.print();
    }
}
