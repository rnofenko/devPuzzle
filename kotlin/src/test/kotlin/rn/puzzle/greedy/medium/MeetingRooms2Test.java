package rn.puzzle.greedy.medium;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.StrConverter;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

//https://leetcode.com/problems/meeting-rooms-ii/
public class MeetingRooms2Test {
    private static final int START = 0;
    private static final int END = 1;

    public int minMeetingRooms(int[][] intervals) {
        List<int[]> list = Arrays.stream(intervals).sorted((a1, a2) -> a1[START] - a2[START]).collect(Collectors.toList());
        PriorityQueue<int[]> heap = new PriorityQueue<>((a1, a2) -> a1[END] - a2[END]);
        int max = 0;
        for(int[] interval : list) {
            int start = interval[START];
            while (!heap.isEmpty() && heap.peek()[END] <= start ) {
                heap.poll();
            }
            heap.add(interval);
            max = Math.max(max, heap.size());
        }

        return max;
    }

    @Test
    public void test3() {
        run("[[13,15],[1,13]]", 1);
    }

    @Test
    public void test1() {
        run("[[0,30],[5,10],[15,20]]", 2);
    }

    @Test
    public void test2() {
        run("[[7,10],[2,4]]", 1);
    }

    private void run(String intervalsStr, int expected) {
        int[][] intervals = StrConverter.to2dIntArray(intervalsStr);
        int res = minMeetingRooms(intervals);
        Assert.assertEquals(expected, res);
    }
}
