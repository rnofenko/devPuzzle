package rn.puzzle.array.hard;

public class MinimumCostToMergeStonesSolution {
    private final static int max = 999999999;

    public int mergeStones(int[] stones, int k) {
        int len = stones.length;
        if ((len - 1) % (k - 1) != 0) {
            return -1;
        }
        int[][][] dp = new int[len + 1][len + 1][k + 1];
        int[] prefixSum = new int[len + 1];

        for (int i = 1; i <= len; i++) {
            prefixSum[i] = prefixSum[i - 1] + stones[i - 1];
        }

        return getResult(prefixSum, 1, len, 1, k, dp);
    }

    private int getResult(int[] prefixSum, int left, int right, int piles, int k, int[][][] dp) {
        if (dp[left][right][piles] != 0) {
            return dp[left][right][piles];
        }
        int res = max;
        if (left == right) {
            res = (piles == 1) ? 0 : max;
        }
        else {
            if (piles == 1) {
                res = getResult(prefixSum, left, right, k, k, dp) + prefixSum[right] - prefixSum[left - 1];
            }
            else {
                for (int t = left; t < right; t++) {
                    int leftRes = getResult(prefixSum, left, t, piles - 1, k, dp);
                    int rightRes = getResult(prefixSum, t + 1, right, 1, k, dp);
                    res = Math.min(res, leftRes + rightRes);
                }
            }
        }
        dp[left][right][piles] = res;
        return res;
    }
}
