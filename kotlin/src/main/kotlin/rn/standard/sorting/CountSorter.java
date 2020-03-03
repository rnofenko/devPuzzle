package rn.standard.sorting;

import java.util.stream.IntStream;

public class CountSorter {

    public void sort(int[] a) {
        if (a.length == 0) {
            return;
        }
        int max = IntStream.of(a).max().getAsInt();
        sort(a, max + 1);
    }

    public void sort(int[] a, int alphabetSize) {//implementation form a book
        int N = a.length;
        int[] count = new int[alphabetSize + 1];

        for (int i = 0; i < N; i++)
            count[a[i]+1]++;
        for (int r = 0; r < alphabetSize; r++)
            count[r+1] += count[r];

        int[] aux = new int[a.length];

        for (int i = 0; i < N; i++)
            aux[count[a[i]]++] = a[i];
        for (int i = 0; i < N; i++)
            a[i] = aux[i];
    }
}
