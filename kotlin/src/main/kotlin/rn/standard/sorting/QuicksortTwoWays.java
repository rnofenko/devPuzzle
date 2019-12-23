package rn.standard.sorting;

public class QuicksortTwoWays {
    private InsertionSorter insertionSorter = new InsertionSorter();
    private final static int OPTIMAL_COUNT_WHEN_TO_USER_INSERTION = 20;
    private int countWhenUseInsertion;

    public QuicksortTwoWays() {
        this(true);
    }

    public QuicksortTwoWays(boolean enableInsertionSort) {
        this(enableInsertionSort, OPTIMAL_COUNT_WHEN_TO_USER_INSERTION);
    }

    public QuicksortTwoWays(boolean enableInsertionSort, int countWhenUseInsertion) {
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

        int lo = startIndex;
        int hi = endIndex;

        int partitionLo = getPartitionIndex(values, startIndex, endIndex);
        int partitionHi = partitionLo;
        int partition = values[partitionHi];

        while (lo < partitionLo || hi > partitionHi) {

            while (lo < partitionLo) {
                if (values[lo] < partition) {
                    lo++;
                } else if (values[lo] == partition) {
                    partitionLo--;
                    values[lo] = values[partitionLo];
                    values[partitionLo] = partition;
                } else {
                    break;
                }
            }

            while (hi > partitionHi) {
                if (values[hi] > partition) {
                    hi--;
                } else if (values[hi] == partition) {
                    partitionHi++;
                    values[hi] = values[partitionHi];
                    values[partitionHi] = partition;
                } else {
                    break;
                }
            }

            if (lo >= partitionLo && hi <= partitionHi) {
                break;
            }

            if (lo == partitionLo) {
                values[partitionLo] = values[hi];
                partitionLo++;
                partitionHi++;
                values[hi] = values[partitionHi];
                values[partitionHi] = partition;
            } else if (hi == partitionHi) {
                values[partitionHi] = values[lo];
                partitionHi--;
                partitionLo--;
                values[lo] = values[partitionLo];
                values[partitionLo] = partition;
            } else {
                int temp = values[lo];
                values[lo] = values[hi];
                values[hi] = temp;
                lo++;
                hi--;
            }
        }

        sort(values, startIndex, partitionLo - 1);
        sort(values, partitionHi + 1, endIndex);
    }

    private int getPartitionIndex(int[] values, int startIndex, int endIndex) {
        int partSize = (endIndex - startIndex) / 4;
        if(partSize == 0) {
            return startIndex + (endIndex - startIndex) / 2;
        }

        int index1 = startIndex + partSize;
        int index2 = index1 + partSize;
        int index3 = index2 + partSize;

        int v1 = values[index1];
        int v2 = values[index2];
        int v3 = values[index3];
        if (v1 >= v2) {
            if (v1 < v3) {
                return index1;
            } else {
                if (v2 >= v3) {
                    return index2;
                } else {
                    return index3;
                }
            }
        } else {
            if (v1 > v3) {
                return index1;
            } else {
                if (v2 > v3) {
                    return index3;
                } else {
                    return index2;
                }
            }
        }
    }
}
