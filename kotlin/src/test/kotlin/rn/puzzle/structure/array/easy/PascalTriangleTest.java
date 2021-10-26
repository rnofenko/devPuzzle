package rn.puzzle.structure.array.easy;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PascalTriangleTest {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) {
            return res;
        }
        res.add(Arrays.asList(1));
        for (int i = 0; i < numRows - 1; i++) {
            res.add(generate(res.get(res.size() - 1)));
        }
        return res;
    }

    private List<Integer> generate(List<Integer> parent) {
        if (parent.size() == 1) {
            return Arrays.asList(1, 1);
        }

        ArrayList<Integer> res = new ArrayList<>();
        res.add(1);
        for (int i = 0; i < parent.size() - 1; i++) {
            res.add(parent.get(i) + parent.get(i + 1));
        }
        res.add(1);

        return res;
    }

    @Test
    public void test1() {
        List<List<Integer>> res = generate(30);
        Assert.assertEquals(30, res.size());
    }
}
