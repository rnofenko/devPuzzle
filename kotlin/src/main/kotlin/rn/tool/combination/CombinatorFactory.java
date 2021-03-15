package rn.tool.combination;

public class CombinatorFactory {
    public static ICombinator create(int maxValue, int length) {
        return new Combinator(length, maxValue);
    }

    public static ICombinator variantLength(int maxValue) {
        return new VariantLengthCombinator(maxValue);
    }

    public static IPermutation permutation(int length, int maxValue) {
        return new Permutation(length, 0, maxValue);
    }

    public static IPermutation permutation(int length, int minValue, int maxValue) {
        return new Permutation(length, minValue, maxValue);
    }
}