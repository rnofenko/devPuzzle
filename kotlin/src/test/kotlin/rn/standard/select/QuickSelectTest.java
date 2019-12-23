package rn.standard.select;

import org.junit.Test;

public class QuickSelectTest {

    private QuickSelectSolver solver = new QuickSelectSolver();

    @Test
    public void test1() {
        int[] array = new int[]{9, 10, 4, 5, 8, 6, 11, 7};

        int kPosition = 3;
        int length = array.length;

        int kth = solver.kthSmallest(array, 0, length - 1, kPosition - 1);
        System.out.println("K-th smallest element in array : " + kth);
    }
}