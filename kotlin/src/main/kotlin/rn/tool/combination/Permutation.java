package rn.tool.combination;

class Permutation implements IPermutation {
    private final int length;
    private int minValue;
    private final int maxValue;
    private int[] values;

    Permutation(int length, int minValue, int maxValue) {
        this.length = length;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public int[] next() {
        if(values == null) {
            this.values = new int[length];
            for (int i = 0; i < length; i++) {
                values[i] = minValue;
            }
        } else {
            inc(values);
        }

        if(values[0] > maxValue) {
            return null;
        }

        return values;
    }

    private void inc(int[] values) {
        int index = length - 1;
        values[index]++;

        while (values[index] > maxValue && index > 0) {
            index--;
            values[index]++;
        }

        for (int i = index + 1; i < length; i++) {
            values[i] = minValue;
        }
    }
}
