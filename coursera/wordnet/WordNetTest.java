import edu.princeton.cs.algs4.In;
import org.junit.Test;
import sun.jvm.hotspot.utilities.Assert;

public class WordNetTest {

    @Test
    public void test1() {
        WordNet wordNet = createWordNet("15", "15Tree");
        try {
            wordNet.distance("x", "a");
        } catch (IllegalArgumentException ignored) { }
    }

    private void test(int fileNo, String expected) {
        String[] nouns = getNouns(fileNo);
        Outcast outcast = createOutcast();
        String actual = outcast.outcast(nouns);
        Assert.that(expected.equals(actual), "Expected |" + expected + "|, but actual is |" + actual + "|");
    }

    private String[] getNouns(int fileNo) {
        String filename = "data/outcast" + fileNo + ".txt";
        In in = new In(filename);
        return in.readAllStrings();
    }

    private Outcast createOutcast() {
        WordNet wordNet = createWordNet();
        return new Outcast(wordNet);
    }

    private WordNet createWordNet(String synsetsSuffix, String hypernymsSuffix) {
        return new WordNet("data/synsets" + synsetsSuffix + ".txt", "data/hypernyms" + hypernymsSuffix + ".txt");
    }

    private WordNet createWordNet() {
        return createWordNet("", "");
    }
}
