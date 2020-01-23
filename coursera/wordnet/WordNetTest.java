import org.junit.Assert;
import org.junit.Test;

public class WordNetTest {

    @Test
    public void test4() {
        try {
            createWordNet("3", "3InvalidCycle");
            throw new RuntimeException();
        } catch (IllegalArgumentException ignored) { }
    }
    @Test
    public void test3() {
        try {
            createWordNet("3", "3InvalidTwoRoots");
            throw new RuntimeException();
        } catch (IllegalArgumentException ignored) { }
    }

    @Test
    public void test2() {
        WordNet wordNet = createWordNet("6", "6TwoAncestors");
        int d = wordNet.distance("b", "c");
        Assert.assertEquals(1, d);
    }

    @Test
    public void test1() {
        WordNet wordNet = createWordNet("15", "15Tree");
        try {
            wordNet.distance("x", "a");
            throw new RuntimeException();
        } catch (IllegalArgumentException ignored) { }
    }

    private WordNet createWordNet(String synsetsSuffix, String hypernymsSuffix) {
        return new WordNet("data/synsets" + synsetsSuffix + ".txt", "data/hypernyms" + hypernymsSuffix + ".txt");
    }

    private WordNet createWordNet() {
        return createWordNet("", "");
    }
}
