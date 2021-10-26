package rn.puzzle.structure.array.easy;

import org.junit.Test;
import rn.tool.StrConverter;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrixTest {
    public List<Integer> spiralOrder(int[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        int size = height * width;
        int level = 0;
        ArrayList<Integer> res = new ArrayList<>();

        while(res.size() < size) {
            for (int x = level; x < width - level; x++) {
                res.add(matrix[level][x]);
            }
            if (res.size() < size) {
                for (int y = level + 1; y < height - level - 1; y++) {
                    res.add(matrix[y][width - level - 1]);
                }
            }
            if (res.size() < size) {
                for (int x = width - level - 1; x >= level; x--) {
                    res.add(matrix[height - level - 1][x]);
                }
            }
            if (res.size() < size) {
                for (int y = height - level - 2; y > level; y--) {
                    res.add(matrix[y][level]);
                }
            }
            level++;
        }

        return res;
    }

    @Test
    public void test1() {
        spiralOrder(StrConverter.to2dIntArray("[[1,2,3,4],[5,6,7,8],[9,10,11,12]]"));
    }
}
