package rn.coursera.algtool.w4;

import org.junit.Assert;
import org.junit.Test;

public class BinarySearchTest {
    @Test
    public void test1() {
        Assert.assertEquals(0, BinarySearch.binarySearch(new int[] {1,2,3,4,5}, 1));
        Assert.assertEquals(1, BinarySearch.binarySearch(new int[] {1,2,3,4,5}, 2));
    }
}
