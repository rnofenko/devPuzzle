package rn.puzzle.constructive.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JustifyTextTest {

    @Test
    public void test1() {
        String[] words = new String[] {"This", "is", "an", "example", "of", "text", "justification."};
        List<String> res = fullJustify(words, 16);
        Assert.assertEquals("This    is    an", res.get(0));
        Assert.assertEquals("example  of text", res.get(1));
        Assert.assertEquals("justification.  ", res.get(2));
    }

    public List<String> fullJustify(String[] allWords, int maxWidth) {
        List<String> result = new ArrayList<>();

        int width = 0;
        List<String> words = new ArrayList<>();
        for (String word : allWords) {

            if (width + word.length() + (width > 0 ? 1 : 0) > maxWidth) {
                result.add(process(words, width, maxWidth));
                words.clear();
                width = 0;
            }

            words.add(word);
            width += word.length() + (width > 0 ? 1 : 0);
        }

        if (!words.isEmpty()) {
            result.add(processLast(words, maxWidth));
        }

        return result;
    }

    private String processLast(List<String> words, int maxWidth) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            if (i > 0) {
                builder.append(' ');
            }
            builder.append(word);
        }

        return format(builder.toString(), maxWidth);
    }

    private String process(List<String> words, int width, int maxWidth) {
        int count = words.size();

        if (count == 1) {
            return format(words.get(0), maxWidth);
        }

        StringBuilder builder = new StringBuilder();

        int totalExtra = maxWidth - width;
        int constantExtra = totalExtra / (count - 1);
        int leftSpaces = totalExtra - constantExtra * (count - 1);
        constantExtra++;

        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);

            if (i > 0) {
                for (int j = 0; j < constantExtra; j++) {
                    builder.append(' ');
                }
                if (leftSpaces > 0) {
                    builder.append(' ');
                    leftSpaces--;
                }
            }

            builder.append(word);
        }

        return builder.toString();
    }

    private String format(String str, int maxWidth) {
        String template = "%-" + maxWidth + "s";
        return String.format(template, str);
    }
}
