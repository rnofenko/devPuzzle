package rn.puzzle.structure.array.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SuperSetFacebookTest {

    @Test
    public void test1() {
        List<List<String>> res = buildSuperSet(Arrays.asList("a", "b", "c", "d"));
        Assert.assertEquals(4, res.size());
    }

    public List<List<String>> buildSuperSet(List<String> elements) {
        List<List<String>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for (int size = 1; size <= elements.size(); size++) {
            int[] indexes = createIndexes(size);

            result.add(getSubSet(elements, indexes));
            while (increment(indexes, elements.size())) {
                result.add(getSubSet(elements, indexes));
            }
        }
        return result;
    }

    private List<String> getSubSet(List<String> elements, int[] indexes) {
        List<String> result = new ArrayList<>();
        for(int i:indexes) {
            result.add(elements.get(i));
        }
        return result;
    }

//[0,1] => [0,2] => [1,2] when max = 3 and size=2
//[0,1] => [0,2] => [0,3] => [1,2] => [1,3] => [2,3] when max=4 and size = 2
//[0,1,2] => [0,1,3] => [0,2,3] => [1,2,3] when max = 4 and size = 3
    private boolean increment(int[] a, int max) {
        int i = a.length - 1;
        a[i]++;
        while(a[i] == max - (a.length - 1 - i)) {
            i--;
            if (i<0) {
                return false;
            }
            a[i]++;
        }
        for (int j = i + 1;j < a.length;j++) {
            a[j] = a[j - 1] + 1;
            if (a[j] == max) {
                return false;
            }
        }
        return true;
    }

    private int[] createIndexes(int size) {
        int[] indexes = new int[size];
        for(int i = 0; i< size; i++) {
            indexes[i] = i;
        }
        return indexes;
    }
}
