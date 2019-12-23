package rn.standard.sorting;

public class InsertionSorter {
    public void sort(int[] values) {
        sort(values, 0, values.length - 1);
    }

    public void sort(int[] values, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {

            int minIndex = i;
            for (int j = i + 1; j <= endIndex; j++) {
                if (values[minIndex] > values[j]) {
                    minIndex = j;
                }
            }

            int temp = values[i];
            values[i] = values[minIndex];
            values[minIndex] = temp;
        }
    }
}
