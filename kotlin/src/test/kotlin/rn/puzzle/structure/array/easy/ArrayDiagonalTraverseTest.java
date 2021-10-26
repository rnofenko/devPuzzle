package rn.puzzle.structure.array.easy;

import org.junit.Test;
import rn.tool.StrConverter;

public class ArrayDiagonalTraverseTest {
    public int[] findDiagonalOrder(int[][] mat) {
        int xi = 0;
        int yi = 0;
        int i = 0;
        int y = mat.length;
        int x = mat[0].length;
        int[] res = new int[x * y];
        boolean up = true;
        while (i < x * y) {
            res[i++] = mat[yi][xi];
            if (up) {
                xi++;
                yi--;

                if (xi == x) {
                    yi += 2;
                    xi--;
                    up = false;
                } else if (yi == -1) {
                    yi++;
                    up = false;
                }
            } else {
                xi--;
                yi++;

                if (yi == y) {
                    yi--;
                    xi += 2;
                    up = true;
                } else if (xi == -1) {
                    xi++;
                    up = true;
                }
            }
        }

        return res;
    }

    @Test
    public void test1() {
        findDiagonalOrder(StrConverter.to2dIntArray("[[1,2,3],[4,5,6],[7,8,9]]"));
    }
}
