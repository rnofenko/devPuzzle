package rn.puzzle.search.easy;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.Ass;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

//https://www.hackerrank.com/challenges/ctci-ice-cream-parlor/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=search
public class HashTablesIceCreamParlorTest {
    public static int[] findFlavors(List<Integer> costs, int money) {
        HashMap<Integer, Integer> existing = new HashMap<>();
        int i = 1;
        for(int first: costs) {
            int second = money - first;
            if (existing.containsKey(second)) {
                int idx2 = existing.get(second);
                return new int[] { Math.min(i, idx2), Math.max(i, idx2) };
            }
            existing.put(first, i);
            i++;
        }

        return null;
    }

    @Test
    public void test1() {
        int[] res = findFlavors(Arrays.asList(1,4,5,3,2), 4);
        Ass.assertIntArrayEquals(new int[] {1,4}, res);
    }
}
