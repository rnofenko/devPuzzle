package rn.puzzle.interview.australia;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CurrencyCounterfeitTest {

    @Test
    public void test1() {
        List<String> input = Arrays.asList("AVG190420T", "RTF20001000Z", "QWER201850G", "RT120001000Z", "RTT20001000Z");
        int result = countCounterfeit(input);
        Assert.assertEquals(1020, result);
    }


    private static int MIN_LENGTH = 10;
    private static int MAX_LENGTH = 12;
    private static int PREFIX_LENGTH = 3;
    private static int MIN_YEAR = 1900;
    private static int MAX_YEAR = 2019;
    private static int YEAR_STR_LENGTH = 4;
    private static List<Integer> validNoteAmounts = Arrays.asList(10, 20, 50, 100, 200, 500, 1000);

    private static int countCounterfeit(List<String> serialNumber) {
        return serialNumber
                .stream()
                .filter(s -> isValidSerialNumberLength(s))
                .mapToInt(s -> parseAmount(s))
                .sum();
    }

    private static int parseAmount(String serialNumber) {
        int index = 0;
        String prefix = serialNumber.substring(0, PREFIX_LENGTH);
        if(!isValidPrefix(prefix)) {
            return 0;
        }

        index += PREFIX_LENGTH;
        String year = serialNumber.substring(index, index + YEAR_STR_LENGTH);
        if(!isValidYear(year)) {
            return 0;
        }

        index += YEAR_STR_LENGTH;
        String amount = serialNumber.substring(index, serialNumber.length() - 1);

        String suffix = serialNumber.substring(serialNumber.length() - 1);
        if(!isValidSuffix(suffix)) {
            return 0;
        }

        return parseAmountIfValid(amount);
    }

    private static int parseAmountIfValid(String amountStr) {
        int amount;

        try {
            amount = Integer.parseInt(amountStr);
        } catch (NumberFormatException e) {
            return 0;
        }

        if(validNoteAmounts.contains(amount)) {
            return amount;
        }

        return 0;
    }

    private static boolean isValidSuffix(String suffix) {
        return Character.isUpperCase(suffix.charAt(0));
    }

    private static boolean isValidYear(String yearStr) {
        int year;

        try {
            year = Integer.parseInt(yearStr);
        } catch (NumberFormatException e) {
            return false;
        }

        return year >= MIN_YEAR && year <= MAX_YEAR;
    }

    private static boolean isValidPrefix(String prefix) {
        Set<Character> all = new HashSet<>();

        for (int i = 0; i < prefix.length(); i++) {
            char character = prefix.charAt(i);
            if(Character.isUpperCase(character)) {
                all.add(character);
            }
        }

        return all.size() == PREFIX_LENGTH;
    }

    private static boolean isValidSerialNumberLength(String serialNumber) {
        return serialNumber != null
                && serialNumber.length() >= MIN_LENGTH
                && serialNumber.length() <= MAX_LENGTH;
    }
}
