import edu.princeton.cs.algs4.In;
import org.junit.Test;
import sun.jvm.hotspot.utilities.Assert;

public class OutcastTest {

    @Test
    public void test5() {
        test(5, "table");
    }

    @Test
    public void test8() {
        test(8, "bed");
    }

    @Test
    public void test11() {
        test(11, "potato");
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

    private WordNet createWordNet() {
        return new WordNet("data/synsets.txt", "data/hypernyms.txt");
    }
}
