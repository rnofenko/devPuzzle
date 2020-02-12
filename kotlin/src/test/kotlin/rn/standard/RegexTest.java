package rn.standard;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

    @Test
    public void logicalOr() {
        String text = "aab aac aad bbc bbd";
        printMatcher("aa.|bb." , text);
    }

    @Test
    public void quantifierSpecific() {
        String text = "heeeellloooo";
        printMatcher("e{2,5}" , text);
        printMatcher("l{2}" , text);
    }

    @Test
    public void quantifierAtLeastOne() {
        String text = "heeeellloooo";
        printMatcher("e+" , text);
    }

    @Test
    public void quantifierAny() {
        String text = "heeeellloooo";
        printMatcher("e*" , text);
    }

    @Test
    public void beginningEndOfWord() {
        String text = "abc def ghi";
        printMatcher("\\b" , text);
        printMatcher("\\B" , text);

        text = "word";
        printMatcher("\\b" , text);
        printMatcher("\\B" , text);
    }

    @Test
    public void beginningEndOfLine() {
        String text = "abc\ndef\nghi\n";
        printMatcher("^.." , text);
        printMatcher("..$" , text);
    }

    @Test
    public void wordCharacter() {
        String text = "zip. phone. code.";
        printMatcher("\\w\\w\\w" , text);
        printMatcher("\\W\\W\\w" , text);
    }

    @Test
    public void digits() {
        String text = "zip 53051. phone 908-216";
        printMatcher("p\\D\\D" , text);
        printMatcher("z\\D\\D\\D" , text);
        printMatcher("\\d\\d\\d" , text);
    }

    @Test
    public void setOfCharacters() {
        String text = "hello hallo hero. Hello.";

        printMatcher("h[ea]l." , text);
        printMatcher("h[ea][lr]." , text);
        printMatcher("h[a-z][a-z]." , text);
        printMatcher("[a-zA-Z]el." , text);
    }

    @Test
    public void anyCharacter() {
        String text = "hello hallo hero";
        printMatcher("h.l", text);
    }

    @Test
    public void escaping() {
        String text = "hello. buy.";

        printMatcher(".", text);
        printMatcher("\\.", text);
    }

    @Test
    public void matcherExample() {
        String text = "This is the text which is to be searched for occurrences of the word 'is'.";
        printMatcher("is", text);
    }

    @Test
    public void simpleExample() {
        String text = "This is the text to be searched for occurrences of the http:// pattern.";
        String regex = ".*http://.*";

        boolean matches = Pattern.matches(regex, text);
        System.out.println("matches = " + matches);
    }

    private void printMatcher(String patternText, String text) {
        System.out.println("\n" + patternText);
        Pattern pattern = Pattern.compile(patternText);
        Matcher matcher = pattern.matcher(text);
        printMatcher(matcher);
    }

    private void printMatcher(Matcher matcher) {
        int count = 0;
        while(matcher.find()) {
            count++;
            System.out.println("found: " + count + " : " + matcher.start() + " - " + matcher.end() +" - " + matcher.group());
        }
    }
}
