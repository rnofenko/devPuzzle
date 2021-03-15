package rn.puzzle.constructive.medium;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.StrConverter;

public class ConstructQuadTreeTest {

    @Test
    public void singleRoot() {
        Node root = test("[[1,1],[1,1]]");
        Assert.assertTrue(root.isLeaf);
        Assert.assertTrue(root.val);
    }

    @Test
    public void topLeft_is_leaf() {
        Node root = test("[[0,1],[1,1]]");
        Assert.assertFalse(root.isLeaf);

        Assert.assertTrue(root.topLeft.isLeaf);
        Assert.assertFalse(root.topLeft.val);

        Assert.assertTrue(root.bottomRight.isLeaf);
        Assert.assertTrue(root.bottomRight.val);
    }

    @Test
    public void test1() {
        Node root = test("[" +
                "[1,1,1,1,0,0,0,0]," +
                "[1,1,1,1,0,0,0,0]," +
                "[1,1,1,1,1,1,1,1]," +
                "[1,1,1,1,1,1,1,1]," +
                "[1,1,1,1,0,0,0,0]," +
                "[1,1,1,1,0,0,0,0]," +
                "[1,1,1,1,0,0,0,0]," +
                "[1,1,1,1,0,0,0,0]]");
        Assert.assertFalse(root.isLeaf);

        Assert.assertTrue(root.topLeft.isLeaf);
        Assert.assertFalse(root.topRight.isLeaf);
        Assert.assertTrue(root.bottomLeft.isLeaf);
        Assert.assertTrue(root.bottomRight.isLeaf);
    }

    public Node construct(int[][] grid) {
        int[][] counts = buildCountGrid(grid);
        return buildNode(grid, counts, 0, 0, grid.length);
    }

    private Node buildNode(int[][] grid, int[][] counts, int startX, int startY, int size) {
        Node node = new Node();

        if (isLead(grid, counts, startX, startY, size)) {
            node.isLeaf = true;
            node.val = grid[startY][startX] == 1;
        } else {
            int newSize = size / 2;
            node.topLeft = buildNode(grid, counts, startX, startY, newSize);
            node.topRight = buildNode(grid, counts, startX + newSize, startY, newSize);
            node.bottomLeft = buildNode(grid, counts, startX, startY + newSize, newSize);
            node.bottomRight = buildNode(grid, counts, startX + newSize, startY + newSize, newSize);
        }

        return node;
    }

    private boolean isLead(int[][] grid, int[][] counts, int startX, int startY, int size) {
        int value = grid[startY][startX];
        for (int i = 0; i < size; i++) {
            if (counts[startY + i][startX] < size || value != grid[startY + i][startX]) {
                return false;
            }
        }
        return true;
    }

    private int[][] buildCountGrid(int[][] grid) {
        int size = grid.length;
        int[][] counts = new int[size][size];
        for (int y = 0; y < size; y++) {
            int[] dataRow = grid[y];
            int[] countRow = counts[y];
            countRow[size - 1] = 1;
            for (int x = size - 2; x >= 0; x--) {
                if (dataRow[x] == dataRow[x + 1]) {
                    countRow[x] = countRow[x + 1] + 1;
                } else {
                    countRow[x] = 1;
                }
            }
        }

        return counts;
    }

    private static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {}

        public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    }

    private Node test(String gridStr) {
        int[][] a = StrConverter.INSTANCE.to2dIntArray(gridStr);
        return construct(a);
    }
}
