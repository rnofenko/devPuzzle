package rn.puzzle.structure.array.hard;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.FileHelper;
import rn.tool.StrConverter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class TruckTourTest {

    //Output  - An integer which will be the smallest index of the petrol pump from which we can start the tour.
    static int truckTour(int[][] pumps) {
        final int FUEL = 0;
        final int DISTANCE = 1;
        final int FULL_LOOP = -1;

        int stops = pumps.length;
        int start = stops - 1;
        int best = start;
        int[] jumps =  IntStream.range(0, stops).toArray();
        long[] petrolAfterJumps = IntStream.range(0, stops).map((idx) -> pumps[idx][FUEL]).asLongStream().toArray();
        while (start >= 0) {
            int currentJump = jumps[start];
            if ((currentJump + 1) % stops == start) {
                best = start;
                jumps[start] = FULL_LOOP;
                start--;
                continue;
            }
            long fuel = petrolAfterJumps[start];
            int distance = pumps[currentJump][DISTANCE];
            if (fuel >= distance) {
                int newJump = (currentJump + 1) % stops;
                int reJump = jumps[newJump];
                jumps[start] = reJump;
                if (reJump == FULL_LOOP) {
                    best = start;
                    start--;
                    continue;
                }
                fuel -= distance;
                fuel += petrolAfterJumps[newJump];
                petrolAfterJumps[start] = fuel;
            } else {
                start--;
            }
        }
        return best;
    }

    @Test
    public void test1() {
        run("1 5,10 3,3 4", 1);
    }

    @Test
    public void test2() {
        run("10 7,2 4,1 5,10 1,1 1,3 4", 3);
    }

    @Test
    public void test3() {
        run("47340 0,45863 0,7793 0,84590 0,26405 0,55690 0,42756 0,63396 0,53314 0,50728 0,61392 0,10283 0,82040 0,41342 0,56577 0,39520 0,0 87484,0 463", 0);
    }

    @Test
    public void test4() {
        run("1 2,2 1,3 1,5 1", 1);
    }

    @Test
    public void input01() {
        runFile("input01.txt", 573);
    }

    private void runFile(String filename, int expected) {
        int[][] arr = fileRunner("C:\\temp\\" + filename);
        reduceNumbers(arr);
//        int[][] update = new int[50][];
//        System.arraycopy(arr, 573, update, 0, update.length);
//        arr = update;
//        StringBuilder b = new StringBuilder();
//        for (int[] ints : update) {
//            b.append(ints[0]).append(' ').append(ints[1]).append(',');
//        }
//        System.out.println(b.toString());

        int res = truckTour(arr);
        Assert.assertEquals(expected, res);
    }

    private void run(String input, int expected) {
        int[][] arr = StrConverter.INSTANCE.to2dIntArray(input);
        int res = truckTour(arr);
        Assert.assertEquals(expected, res);
    }

    private void reduceNumbers(int[][] arr) {
        for (int[] line : arr) {
            int min = Math.min(line[0], line[1]);
            line[0] -= min;
            line[1] -= min;
        }
    }

    private int[][] fileRunner(String path) {
        List<String> lines = FileHelper.INSTANCE.load(path);
        int size = Integer.parseInt(lines.get(0));
        int[][] result = new int[size][];
        for (int i = 0; i < size; i++) {
            result[i] = Arrays.stream(lines.get(i + 1).split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        return result;
    }
}
