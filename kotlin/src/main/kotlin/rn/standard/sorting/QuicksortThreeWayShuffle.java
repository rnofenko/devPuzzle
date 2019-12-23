package rn.standard.sorting;

import rn.standard.ArrayShuffler;

public class QuicksortThreeWayShuffle {
    private QuicksortThreeWay threeWay = new QuicksortThreeWay();
    private ArrayShuffler shuffler = new ArrayShuffler();

    public void sort(int[] values) {
        shuffler.shuffle(values);
        threeWay.sort(values);
    }
}
