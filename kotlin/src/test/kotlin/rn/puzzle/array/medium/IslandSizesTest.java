package rn.puzzle.array.medium;

import org.junit.Test;
import rn.tool.StringToArrayConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class IslandSizesTest {

    @Test
    public void test1() {
        String input = "[[1,0,0,1,0],[1,0,1,0,0],[0,0,1,0,1],[1,0,1,0,1]]";
        int[][] a = StringToArrayConverter.INSTANCE.stringTo2dIntArray(input);
        List<Integer> res = new Solver(a).count();
        rn.tool.Assert.INSTANCE.assertListEquals(Arrays.asList(1,2,2,4), res);
    }

    @Test
    public void test2() {
        String input = "[[1,0,0,1,0],[1,0,1,0,0],[1,0,0,1,0],[0,1,1,0,0]]";
        int[][] a = StringToArrayConverter.INSTANCE.stringTo2dIntArray(input);
        List<Integer> res = new Solver(a).count();
        rn.tool.Assert.INSTANCE.assertListEquals(Arrays.asList(8), res);
    }

    public static void solveFromStd() {
        Scanner scanner = new Scanner(System.in);
        String[] parts = scanner.nextLine().split(" ");
        int rowsCount = Integer.parseInt(parts[0]);
        int colsCount = Integer.parseInt(parts[1]);

        int[][] input = new int[rowsCount][];
        for (int i = 0; i < rowsCount; i++) {
            input[i] = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        List<Integer> result = new Solver(input).count();

        if (result.isEmpty()) {
            System.out.println("-");
        } else {
            String resultStr = result.stream().map(Object::toString).collect(Collectors.joining(" "));
            System.out.println(resultStr);
        }
    }

    private static class Solver {
        private final int[][] a;
        private final int height;
        private final int width;

        public Solver(int[][] a) {
            this.a = a;
            this.height = a.length;
            this.width = a[0].length;
        }

        public List<Integer> count() {
            List<Integer> counts = new ArrayList<>();

            for (int r = 0; r < height; r++) {
                int[] row = a[r];
                for (int c = 0; c < width; c++) {
                    if (row[c] != 1) continue;
                    counts.add(replace(r, c));
                }
            }

            counts.sort(Integer::compareTo);
            return counts;
        }

        private int replace(int r, int c) {
            if (a[r][c] != 1) {
                return 0;
            }
            int count = 1;
            a[r][c] = 0;
            if (r > 0) {
                if (c > 0) {
                    count += replace(r - 1, c - 1);
                }
                count += replace(r - 1, c);
                if (c + 1 < width) {
                    count += replace(r - 1, c + 1);
                }
            }
            if (c + 1 < width) {
                count += replace(r, c + 1);
            }
            if (c > 0) {
                count += replace(r, c - 1);
            }
            if (r + 1 < height) {
                if (c > 0) {
                    count += replace(r + 1, c - 1);
                }
                count += replace(r + 1, c);
                if (c + 1< width) {
                    count += replace(r + 1, c + 1);
                }
            }
            return count;
        }
    }
}
