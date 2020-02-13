package rn.puzzle.wip;

import org.junit.Assert;
import org.junit.Test;
import rn.puzzle.dynamic.hard.cherry.ICherryPickSolver;
import rn.tool.Stopwatch;
import rn.tool.StringToArrayConverter;

public class CherryPickupTest {

    @Test
    public void timeOut5() {
        String gridStr = "[" +
                "[ 1, 1, 1,1,1,1,0,1,1,-1,-1,1,1,-1,0,1,1,-1,0,-1]," +
                "[ 1, 1, 1,0,1,1,0,1,0,1,1,-1,1,1,1,1,-1,0,1,0]," +
                "[ 1, 1, 1,1,1,1,1,0,1,1,1,0,1,1,1,1,0,1,1,-1]," +
                "[ 1, 1, 1,0,0,-1,1,1,1,1,0,1,1,0,1,1,-1,1,1,1]," +
                "[ 1,-1,-1,1,0,0,1,1,0,1,1,1,1,1,1,0,-1,1,1,1]," +
                "[ 1,-1, 1,0,1,-1,-1,0,1,-1,1,1,0,1,1,1,1,1,-1,1]," +
                "[ 0, 0,-1,1,0,1,1,1,0,1,1,1,1,0,1,1,0,1,1,1]," +
                "[ 1, 0,-1,1,0,1,1,1,0,1,0,0,1,-1,1,1,1,1,-1,1]," +
                "[ 0,-1, 0,1,1,1,1,1,1,-1,-1,1,1,1,0,1,1,1,1,0]," +
                "[ 1, 1, 1,1,1,0,0,1,1,1,1,0,1,1,-1,0,-1,0,1,0]," +
                "[ 0, 1,-1,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,-1,0]," +
                "[ 0, 0, 1,1,1,1,1,0,0,1,1,1,1,-1,1,1,1,0,1,-1]," +
                "[ 1, 1, 1,1,1,-1,1,1,0,1,1,1,1,1,1,-1,-1,0,1,0]," +
                "[-1,-1, 1,1,-1,1,1,1,1,1,1,1,-1,1,0,0,1,0,1,1]," +
                "[ 0, 1,-1,-1,1,-1,0,1,1,-1,-1,1,1,1,0,1,0,-1,1,-1]," +
                "[ 1, 1, 1,-1,1,-1,1,1,0,-1,1,1,1,1,1,1,1,1,1,-1]," +
                "[ 1, 1,-1,1,1,1,1,1,1,1,0,-1,1,-1,1,1,1,1,1,1]," +
                "[ 1, 1, 1,-1,0,1,0,-1,1,0,1,1,1,0,1,1,1,0,0,1]," +
                "[ 1, 1, 0,0,-1,1,1,0,-1,1,1,1,1,-1,1,1,0,-1,0,1]," +
                "[ 0, 0, 0,1,1,1,-1,1,0,1,0,1,1,0,1,0,0,1,1,1]]";

        performance(gridStr);
    }

    @Test
    public void timeOut4() {
        String gridStr = "[" +
        "[ 0, 0, 1,0,0,1,0,1,1,-1,0,0,-1,-1,0,1,1,-1,0,-1],"+
        "[ 1, 1, 1,0,1,0,0,0,0,1,1,1,1,1,1,1,0,0,1,0],"+
        "[ 1, 0, 1,1,0,0,1,0,0,0,1,0,1,1,1,-1,0,1,1,0],"+
        "[ 0, 1, 1,0,0,0,1,0,1,1,0,-1,1,0,0,1,0,0,1,1],"+
        "[-1, 0,-1,1,0,0,1,1,0,0,1,1,0,-1,1,0,0,0,1,1],"+
        "[ 0, 0, 1,0,1,1,0,0,1,0,0,1,0,1,1,1,1,1,1,0],"+
        "[ 0, 0, 0,1,0,1,1,0,0,1,1,-1,1,0,1,1,0,1,1,0],"+
        "[ 0, 0, 0,0,0,0,1,0,0,1,0,0,1,0,0,0,1,0,1,1],"+
        "[ 0, 0, 0,-1,1,0,0,1,0,0,1,1,1,1,0,0,0,1,1,0],"+
        "[ 1, 0, 1,1,1,0,0,1,1,0,1,0,0,0,-1,0,-1,0,1,0],"+
        "[ 0, 1,-1,1,1,1,1,0,1,0,0,1,1,0,-1,1,0,0,-1,0],"+
        "[ 0, 0, 0,0,1,0,1,0,0,-1,0,1,0,-1,0,0,1,0,1,1],"+
        "[ 1,-1,-1,0,0,1,1,1,0,1,1,1,1,1,1,0,0,0,1,0],"+
        "[-1, 0, 1,1,1,1,1,1,0,1,1,1,1,1,0,0,1,0,1,0],"+
        "[ 0, 1,-1,1,1,1,0,0,1,-1,1,1,0,1,0,1,0,-1,1,0],"+
        "[ 1,-1, 1,0,1,1,1,0,0,0,1,1,1,1,-1,0,0,1,0,-1],"+
        "[-1, 1, 0,0,0,1,1,1,1,1,0,1,1,-1,0,1,0,0,1,0],"+
        "[ 0, 0, 0,-1,0,1,0,0,0,0,0,0,1,0,1,1,0,0,0,1],"+
        "[ 0, 1, 0,0,0,0,0,0,0,1,1,1,1,0,0,1,0,0,0,1],"+
        "[ 0, 0, 0,1,-1,0,-1,1,0,1,0,0,0,0,1,0,0,1,-1,0]]";

        performance(gridStr);
    }

    @Test
    public void timeOut2() {
        String gridStr =
                "[[ 1, 1, 1, 1, 1, 1, 1, 1, 1]," +
                " [ 1, 1, 1, 1, 1, 1, 1, 1, 1]," +
                " [ 1, 1, 1, 1, 1, 1, 1, 1, 1]," +
                " [ 1, 1, 1, 1, 1, 1, 1, 1, 1]," +
                " [ 1, 1, 1, 1, 1, 1, 1, 1, 1]," +
                " [ 1, 1, 1, 1, 1, 1, 1, 1, 1]," +
                " [ 1, 1, 1, 1, 1, 1, 1, 1, 1]," +
                " [ 1, 1, 1, 1, 1, 1, 1, 1, 1]," +
                " [ 1, 1, 1, 1, 1, 1, 1, 1, 1]]";

        performance(gridStr);
    }

    @Test
    public void timeOut3() {
        String gridStr =
                        "[[ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]," +
                        " [ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]," +
                        " [ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]," +
                        " [ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]," +
                        " [ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]," +
                        " [ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]," +
                        " [ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]," +
                        " [ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]," +
                        " [ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]," +
                        " [ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]," +
                        " [ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]," +
                        " [ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]," +
                        " [ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]]";

        performance(gridStr);
    }

    @Test
    public void test5x5() {
        String gridStr =
                        "[[ 1,  1, 1,  1,  1]," +
                        " [ 1,  1, 1,  1,  1]," +
                        " [-1, -1, 1,  1, -1]," +
                        " [ 0,  0, 1,  1,  1]," +
                        " [ 0,  0, 1, -1,  1]]";

        print(gridStr);
        test(gridStr, 14);
    }

    @Test
    public void test_1x1() {
        String gridStr = "[[0]]";
        test(gridStr, 0);
    }

    @Test
    public void test_0points() {
        String gridStr =
                        "[[1,  1, -1]," +
                        " [1, -1,  1]," +
                        " [-1, 1,  1]]";

        test(gridStr, 0);
    }

    @Test
    public void test2xx2() {
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
    public void test3x3_1() {
        String gridStr =
                "[[0, 1, -1]," +
                " [1, 0, -1]," +
                " [1, 1,  1]]";

        test(gridStr, 5);
    }

    @Test
    public void test3x3_3() {
        String gridStr =
                        "[[0, 1,  1]," +
                        " [1, 0, -1]," +
                        " [1, 1,  1]]";

        print(gridStr);
        test(gridStr, 5);
    }

    @Test
    public void test3x3_2() {
        String gridStr =
                "[[1, 1, 1]," +
                " [1, 1, 1]," +
                " [1, 1, 1]]";

        print(gridStr);
        test(gridStr, 8);
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

        test(gridStr, 6);
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
    public void test5x4_full() {
        String gridStr =
                        "[[1, 1, 1, 1]," +
                        " [1, 1, 1, 1]," +
                        " [1, 1, 1, 1]," +
                        " [1, 1, 1, 1]," +
                        " [1, 1, 1, 1]]";

        print(gridStr);
        test(gridStr, 14);
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
        Stopwatch w = Stopwatch.Companion.start();
        int[][] grid = StringToArrayConverter.INSTANCE.stringTo2dIntArray(gridStr);
        int res = getSolver().cherryPickup(grid);
        w.show("");
        Assert.assertEquals(expected, res);
    }

    private void performance(String gridStr) {
        Stopwatch w = Stopwatch.Companion.start();
        int[][] grid = StringToArrayConverter.INSTANCE.stringTo2dIntArray(gridStr);
        getSolver().cherryPickup(grid);
        w.show("total");
    }

    private void print(String gridStr) {
        int[][] grid = StringToArrayConverter.INSTANCE.stringTo2dIntArray(gridStr);
        new rn.puzzle.dynamic.hard.cherry.index.GraphBuilder(grid).print();
    }

    private ICherryPickSolver getSolver() {
        return new rn.puzzle.dynamic.hard.cherry.index.CherryPickupSolver();
    }

}
