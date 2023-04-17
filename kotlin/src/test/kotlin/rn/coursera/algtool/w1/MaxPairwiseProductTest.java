package rn.coursera.algtool.w1;

import org.junit.Assert;
import org.junit.Test;

public class MaxPairwiseProductTest {
    @Test
    public void test1() {
        long res = MaxPairwiseProduct.getMaxPairwiseProduct(new int[]{4,1,6,9});
        Assert.assertEquals(54, res);
    }
}



