package rn.tool.combination;

public class CombinatorFactory {
    public static ICombinator create(int maxValue, int length) {
        return new Combinator(length, maxValue);
    }

    public static ICombinator variantLength(int maxValue) {
        return new VariantLengthCombinator(maxValue);
    }
}