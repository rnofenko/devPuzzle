package rn.puzzle.graph.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/number-of-islands/
public class NumberOfIslandsTest {

    private static class DisjointSet {
        private final int[] parents;
        private final int[] sizes;
        int nodesCount;

        DisjointSet(int size) {
            this.parents = new int[size];
            this.sizes = new int[size];
            for (int i = 0; i < parents.length; i++) {
                parents[i] = i;
                sizes[i] = 1;
            }
            nodesCount = size;
        }

        private void join(int a, int b) {
            int parentA = getParent(a);
            int parentB = getParent(b);
            if (parentA == parentB) return;

            if (sizes[parentA] >= sizes[parentB]) {
                parents[parentB] = parentA;
                sizes[parentA] += sizes[parentB];
            } else {
                parents[parentA] = parentB;
                sizes[parentB] += sizes[parentA];
            }
            nodesCount--;
        }

        private int getParent(int a) {
            if (a == parents[a]) {
                return a;
            }
            parents[a] = getParent(parents[a]);
            return parents[a];
        }
    }

    public int numIslands(char[][] grid) {
        int width = grid[0].length;
        DisjointSet ds = new DisjointSet(grid.length * width + 1);

        for (int i = 0; i < grid.length; i++) {
            char[] line = grid[i];
            for (int j = 0; j < width; j++) {
                int currentIdx = i * width + j;
                if (line[j] == '0') {
                    ds.join(grid.length * width, currentIdx);
                } else {
                    if (j < line.length - 1 && line[j + 1] == '1') {
                        ds.join(currentIdx, currentIdx + 1);
                    }
                    if (i < grid.length - 1 && grid[i + 1][j] == '1') {
                        ds.join(currentIdx, currentIdx + width);
                    }
                }
            }
        }

        if (ds.sizes[ds.sizes.length - 1] == 1) {
            return 1;
        }
        return ds.nodesCount - 1;
    }

    @Test
    public void test1() {
        run(Arrays.asList("11110", "11010", "11000", "00000"), 1);
    }

    @Test
    public void test2() {
        run(Arrays.asList("11000", "11000", "00100", "00011"), 3);
    }

    @Test
    public void test3() {
        run(Arrays.asList("10111", "10101", "11101"), 1);
    }

    @Test
    public void test4() {
        run(Arrays.asList("1111111", "0000001", "1111101", "1000101", "1010101", "1011101", "1111111"), 1);
    }

    @Test
    public void test5() {
        run(Arrays.asList("111", "111", "111"), 1);
    }

    private void run(List<String> data, int expectedResult) {
        char[][] a2 = new char[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            a2[i] = data.get(i).toCharArray();
        }
        int res = numIslands(a2);
        Assert.assertEquals(expectedResult, res);
    }
}
