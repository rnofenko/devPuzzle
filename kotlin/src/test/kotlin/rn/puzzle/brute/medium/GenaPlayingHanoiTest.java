package rn.puzzle.brute.medium;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.Stopwatch;

import java.util.*;
import java.util.stream.IntStream;

public class GenaPlayingHanoiTest {

    private final static int PILES_COUNT = 4;
    private final static int BIT_SHIFT = 2;

    private static int play(int[] a) {
        int size = a.length;
        Set<Integer> allStates = new HashSet<>();
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(toState(a));
        allStates.add(queue.peek());
        int stepNo = 0;
        int finalState = toState(IntStream.range(0, size).map(i -> 1).toArray());

        while (!queue.isEmpty()) {
            LinkedList<Integer> newQueue = new LinkedList<>();
            while (!queue.isEmpty()) {
                int state = queue.poll();
                if (state == finalState) {
                    return stepNo;
                }

                addNextStates(state, size, allStates, newQueue);
            }
            queue = newQueue;
            stepNo++;
        }

        return 0;
    }

    private static void addNextStates(int state, int size, Set<Integer> allStates, LinkedList<Integer> newQueue) {
        int[] piles = getLowestDisks(state, size);

        for (int p1 = 0; p1 < PILES_COUNT; p1++) {
            int diskFrom = piles[p1];
            if (diskFrom == 0) continue;
            int fromState = state - (p1 << (diskFrom * BIT_SHIFT));
            for (int p2 = 0; p2 < PILES_COUNT; p2++) {
                if (p2 == p1) continue;
                int diskTo = piles[p2];
                if (diskTo == 0 || diskTo > diskFrom) {
                    int newState = fromState + (p2 << (diskFrom * BIT_SHIFT));
                    if (allStates.contains(newState)) continue;
                    allStates.add(newState);
                    newQueue.add(newState);
                }
            }
        }
    }

    private static int[] getLowestDisks(int state, int size) {
        int[] piles = new int[PILES_COUNT];
        for (int disk = size; disk > 0; disk--) {
            int pileNo = (state >> (disk * BIT_SHIFT)) & 3;
            piles[pileNo] = disk;
        }
        return piles;
    }

    private static int toState(int[] a) {
        int state = 0;
        for (int disk = 1; disk <= a.length; disk++) {
            state += (a[disk - 1] - 1) << (disk * BIT_SHIFT);
        }
        return state;
    }

    @Test
    public void input11() {
        Stopwatch w = Stopwatch.Companion.start();
        Assert.assertEquals(40, play(new int[] {4,1,2,1,4,3,3,4,3,4}));
        w.show("40end");
    }

    @Test
    public void test1() {
        Assert.assertEquals(3, play(new int[] {1,4,1}));
    }

    @Test
    public void test2() {
        Assert.assertEquals(0, play(new int[] {1,1,1}));
    }
}
