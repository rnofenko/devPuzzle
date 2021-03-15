package rn.tool.combination;

class Combinator implements ICombinator {

    private final int length;
    private final int maxValue;
    private int[] combination;

    Combinator(int length, int maxValue) {
        this.length = length;
        this.maxValue = maxValue;
    }

    @Override
    public int[] next() {
        if(combination == null) {
            this.combination = new int[length];
            for (int i = 0; i < length; i++) {
                combination[i] = i;
            }
        } else {
            inc(combination);
        }

        if(combination[length - 1] > maxValue) {
            return null;
        }

        return combination;
    }

    private void inc(int[] combination) {
        int index = length - 1;
        combination[index]++;

        int localMax = maxValue;
        while (combination[index] > localMax && index > 0) {
            index--;
            combination[index]++;
            localMax--;
        }

        for (int i = index + 1; i < length; i++) {
            combination[i] = combination[i - 1] + 1;
        }
    }
}
