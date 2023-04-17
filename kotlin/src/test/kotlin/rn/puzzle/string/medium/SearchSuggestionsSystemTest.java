package rn.puzzle.string.medium;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.Ass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchSuggestionsSystemTest {
    private static class Range {
        int start;
        int end;
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Range range = new Range();
        range.end = products.length - 1;
        Arrays.sort(products);

        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < searchWord.length(); i++) {
            List<String> charSuggestions = suggestedProductsByChar(products, range, searchWord.charAt(i), i);
            result.add(charSuggestions);
        }

        return result;
    }

    private List<String> suggestedProductsByChar(String[] products, Range range, char searchChar, int charIdx) {
        while (range.start <= range.end) {
            String product = products[range.start];
            if (product.length() > charIdx && product.charAt(charIdx) == searchChar) {
                break;
            }
            range.start++;
        }
        while (range.start <= range.end) {
            String product = products[range.end];
            if (product.length() > charIdx && product.charAt(charIdx) == searchChar) {
                break;
            }
            range.end--;
        }

        List<String> result = new ArrayList<>();
        for (int i = range.start; i <= range.end && result.size() < 3; i++) {
            result.add(products[i]);
        }
        return result;
    }

    @Test
    public void test2() {
        List<List<String>> res = suggestedProducts(new String[]{"bags", "baggage", "banner", "box", "cloths"}, "bags");
        Assert.assertEquals(4, res.size());
        Ass.assertListEquals(res.get(0), Arrays.asList("baggage","bags","banner"));
        Ass.assertListEquals(res.get(3), Arrays.asList("bags"));
    }

    @Test
    public void test3() {
        List<List<String>> res = suggestedProducts(new String[]{"havana"}, "ta");
        Assert.assertEquals(2, res.size());
        Assert.assertEquals(0, res.get(0).size());
    }

    @Test
    public void test1() {
        suggestedProducts(new String[] {"aaaa", "aaaa", "aaaaa", "aaaaa", "aaaaa"}, "aaaa");
    }
}
