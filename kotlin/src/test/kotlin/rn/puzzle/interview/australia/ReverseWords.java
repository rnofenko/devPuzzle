package rn.puzzle.interview.australia;

import org.junit.Assert;
import org.junit.Test;

public class ReverseWords {

    public String reverseSentence(String sentence) {
        String[] words = sentence.split(" ");
        if (words.length == 0) {
            return sentence;
        }
        StringBuilder res = new StringBuilder();
        for (int i = words.length - 1; i > 0; i--) {
            res.append(words[i]).append(" ");
        }
        res.append(words[0]);
        return res.toString();
    }

    @Test
    public void test1() {
        String res = reverseSentence("hello");
        Assert.assertEquals("hello", res);
    }

    @Test
    public void test2() {
        String res = reverseSentence("he llo");
        Assert.assertEquals("llo he", res);
    }

    @Test
    public void test3() {
        Assert.assertEquals("", reverseSentence(""));
        Assert.assertEquals(" ", reverseSentence(" "));
    }
}
