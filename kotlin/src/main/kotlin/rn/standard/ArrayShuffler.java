package rn.standard;

import rn.tool.Rand;

public class ArrayShuffler {
    public void shuffle(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int index = Rand.INSTANCE.positiveInt(i);

            int temp = a[i];
            a[i] = a[index];
            a[index] = temp;
        }
    }
}
