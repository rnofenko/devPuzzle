package rn.puzzle.structure.array.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class MinAbsDifferenceTest {

    @Test
    public void test1() {
        Map<Integer, Integer> res = findClosestNumbers(Arrays.asList(4, 2, 1, 3));
        Assert.assertNotNull(res);
    }

    public static Map<Integer, Integer> findClosestNumbers(List<Integer> numbers) {
        numbers.sort(Comparator.naturalOrder());

        int diff = Integer.MAX_VALUE;
        for (int i = 1; i < numbers.size(); i++) {
            diff = Math.min(diff, numbers.get(i) - numbers.get(i - 1));
        }

        Set<Integer> set = new HashSet<>(numbers);

        Map<Integer, Integer> result = new HashMap<>();
        for (Integer number : numbers) {
            if (set.contains(number + diff)) {
                result.put(number, number + diff);
            }
        }

        return result;
    }
}
