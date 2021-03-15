package rn.puzzle.array.easy;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class findWrongPositionTest {
    public int findWrongPositions(List<Integer> values) {
        ArrayList<Integer> sorted = new ArrayList<>(values);
        sorted.sort(Integer::compareTo);
        int count = 0;
        for (int i = 0; i < values.size(); i++) {
            if (!sorted.get(i).equals(values.get(i))) {
                count++;
            }
        }
        return count;
    }

    @Test
    public void test1() {
        Assert.assertEquals(3, findWrongPositions(Arrays.asList(1,3,3,4,1)));
    }
}