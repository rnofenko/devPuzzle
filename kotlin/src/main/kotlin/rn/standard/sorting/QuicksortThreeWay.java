package rn.standard.sorting;

public class QuicksortThreeWay {
    private InsertionSorter insertionSorter = new InsertionSorter();
    private final static int OPTIMAL_COUNT_WHEN_TO_USER_INSERTION = 14;
    private int countWhenUseInsertion;

    public QuicksortThreeWay() {
        this(true);
    }

    public QuicksortThreeWay(boolean enableInsertionSort) {
        this(enableInsertionSort, OPTIMAL_COUNT_WHEN_TO_USER_INSERTION);
    }

    public QuicksortThreeWay(boolean enableInsertionSort, int countWhenUseInsertion) {
        this.countWhenUseInsertion = enableInsertionSort ? countWhenUseInsertion : 0;
    }

    public void sort(int[] values) {
        sort(values, 0, values.length - 1);
    }

    private void sort(int[] values, int startIndex, int endIndex) {
        int elementsCount = endIndex - startIndex + 1;

        if (elementsCount < 2) {
            return;
        } else if (elementsCount < countWhenUseInsertion) {
            insertionSorter.sort(values, startIndex, endIndex);
            return;
        }

        int sortIndex = startIndex;
        int lo = sortIndex + 1;
        int hi = endIndex;
        selectOptimalPartition(values, startIndex, endIndex);
        int partition = values[sortIndex];

        while (lo <= hi) {

            if (values[hi] > partition) {
                hi--;
            } else if (values[lo] < partition) {
                values[sortIndex] = values[lo];
                values[lo] = partition;
                sortIndex++;
                lo++;
            } else if (values[lo] == partition) {
                lo++;
            } else { //when lo higher partition and hi smaller or equal partition
                int temp = values[lo];
                values[lo] = values[hi];
                values[hi] = temp;
            }
        }

        if (hi == endIndex && sortIndex == startIndex) {//all are equal
            return;
        }

        sort(values, startIndex, sortIndex - 1);
        sort(values, lo, endIndex);
    }

    private void selectOptimalPartition(int[] values, int startIndex, int endIndex) {
        int newPartition = getNewPartition(values, startIndex, endIndex);

        if (newPartition == startIndex) {
            return;
        }

        int temp = values[startIndex];
        values[startIndex] = values[newPartition];
        values[newPartition] = temp;
    }

    private int getNewPartition(int[] values, int startIndex, int endIndex) {
        int secondIndex = startIndex + (endIndex - startIndex) / 2;
        int v1 = values[startIndex];
        int v2 = values[secondIndex];
        int v3 = values[endIndex];
        if (v1 >= v2) {
            if (v1 <= v3) {
                return startIndex;
            } else {
                if (v2 > v3) {
                    return secondIndex;
                } else {
                    return endIndex;
                }
            }
        } else {
            if (v1 >= v3) {
                return startIndex;
            } else {
                if (v2 > v3) {
                    return endIndex;
                } else {
                    return secondIndex;
                }
            }
        }
    }
}
