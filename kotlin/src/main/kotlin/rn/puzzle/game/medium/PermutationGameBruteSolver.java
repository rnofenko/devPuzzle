package rn.puzzle.game.medium;

public class PermutationGameBruteSolver {

    private static final int FIRST = 1;
    private static final int SECOND = 2;
    private static final int STENCIL = 15;

    public String permutationGame(int[] arr) {
        long start = toLong(arr);
        if (isIncreasing(start)) {
            return "Alice";
        }
        int res = solve(start, arr.length);

        return res == FIRST ? "Alice" : "Bob";
    }

    private int solve(long start, int len) {
        if (len == 2) {
            return FIRST;
        }

        for (int i = 0; i < len; i++) {
            long newNumber = remove(start, i);
            if (isIncreasing(newNumber)) {
                return FIRST;
            }
            int shortRes = solve(newNumber, len - 1);
            if (shortRes == SECOND) {
                return FIRST;
            }
        }
        return SECOND;
    }

    private long remove(long start, int toRemove) {
        long leftPattern = (1L << (toRemove * 4)) - 1;
        long left = start & leftPattern;
        long right = start >> (toRemove + 1) * 4;
        return right << (toRemove * 4) | left;
    }

    private boolean isIncreasing(long number) {
        long current = 0;
        long prev;
        while (number > 0) {
            prev = current;
            current = number & STENCIL;
            if (prev > current) {
                return false;
            }
            number = number >>> 4;
        }

        return true;
    }

    private long toLong(int[] arr) {
        long n = 0;
        for (int i = 0; i < arr.length; i++) {
            long val = ((long) arr[i]) << (i * 4);
            n = n | val;
        }
        return n;
    }
}
