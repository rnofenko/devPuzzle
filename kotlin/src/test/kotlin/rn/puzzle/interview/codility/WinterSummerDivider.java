package rn.puzzle.interview.codility;

import org.junit.Assert;
import org.junit.Test;

public class WinterSummerDivider {
    public int solution(int[] T) {
        int winterLength = 0;
        int winterMaxTemp = T[winterLength];
        int globalMaxTemp = T[winterLength];
        for(int i = 1; i < T.length - 1; i++) {
            int temp = T[i];

            if(winterMaxTemp > temp) {
                winterLength = i;
                winterMaxTemp = globalMaxTemp;
            }

            globalMaxTemp = Math.max(globalMaxTemp, temp);
        }
        return winterLength + 1;
    }

    @Test
    public void test1() {
        Assert.assertEquals(3, solution(new int[] { 5,-2,3,8,6 }));
    }

    @Test
    public void test2() {
        Assert.assertEquals(5, solution(new int[] { 5,-2,3,8,0,6 }));
    }
}
