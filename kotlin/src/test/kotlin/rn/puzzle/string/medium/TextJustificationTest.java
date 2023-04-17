package rn.puzzle.string.medium;

import org.junit.Test;
import rn.tool.Ass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextJustificationTest {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();

        int charsLen = 0;
        List<String> lineWords = new ArrayList<>();
        for (String word : words) {
            int minLen = charsLen + word.length() + lineWords.size();

            if (minLen < maxWidth) {
                lineWords.add(word);
                charsLen += word.length();
            } else if (minLen == maxWidth) {
                lineWords.add(word);
                charsLen += word.length();

                res.add(buildLine(lineWords, maxWidth, charsLen));
                charsLen = 0;
            } else {
                res.add(buildLine(lineWords, maxWidth, charsLen));
                charsLen = 0;

                lineWords.add(word);
                charsLen += word.length();
            }
        }

        if (!lineWords.isEmpty()) {
            String lastLine = String.join(" ", lineWords);
            res.add(lastLine + createSpace(maxWidth - lastLine.length()));
        }

        return res;
    }

    private String buildLine(List<String> words, int maxWidth, int charsLen) {
        int brakesCount = Math.max(1, words.size() - 1);
        int emptyLen = maxWidth - charsLen;
        int brakeLen = emptyLen / brakesCount;

        String brake = createSpace(brakeLen);

        int leftSpaces = emptyLen - brakeLen * brakesCount;
        StringBuilder builder = new StringBuilder();
        for (String word : words) {
            builder.append(word).append(brake);
            if (leftSpaces > 0) {
                builder.append(" ");
                leftSpaces--;
            }
        }

        words.clear();

        return builder.substring(0, maxWidth);
    }

    private String createSpace(int count) {
        if (count == 0) {
            return "";
        }
        char[] chars = new char[count];
        Arrays.fill(chars, ' ');
        return new String(chars);
    }

    @Test
    public void test3() {
        run(new String[] {"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"}, 20,
                Arrays.asList("Science  is  what we", "understand      well", "enough to explain to","a  computer.  Art is","everything  else  we","do                  "));
    }

    @Test
    public void test2() {
        run(new String[] {"What","must","be","acknowledgment","shall","be"}, 16,
                Arrays.asList("What   must   be", "acknowledgment  ", "shall be        "));
    }

    @Test
    public void test1() {
        run(new String[] {"This", "is", "an", "example", "of", "text", "justification."}, 16,
                Arrays.asList("This    is    an", "example  of text", "justification.  "
        ));
    }

    private void run(String[] words, int maxWidth, List<String> expected) {
        List<String> res = fullJustify(words, maxWidth);
        Ass.assertListEquals(expected, res);
    }
}
