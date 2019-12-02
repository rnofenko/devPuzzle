package rn.tool.combination;

public class VariantLengthCombinator implements ICombinator {
    private final int maxValue;
    private ICombinator combinator;
    private int length;

    VariantLengthCombinator(int maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public int[] next() {
        int[] combination = combinator == null ? null : combinator.next();
        if(combination == null) {
            length++;
            if(length > maxValue + 1) {
                return null;
            }
            combinator = new Combinator(length, maxValue);
            combination = combinator.next();
        }

        return combination;
    }
}
