import org.junit.Test;

public class ListTrieBuilderTest {

    private TrieBuilder builder = new TrieBuilder();

    @Test
    public void test1() {
        ListTrie trie = new ListTrie();
        builder.build(new String[]{"ABC", "BCD"}, trie);
        assert (trie.get('A') != null) : "no A";
        assert (trie.get('B') != null) : "no B";
    }
}
