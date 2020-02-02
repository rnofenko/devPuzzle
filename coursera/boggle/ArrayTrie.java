public class ArrayTrie implements ITrieNode {
    private static final char MIN_CHAR = 'A';
    private static final char MAX_CHAR = 'Z';
    private final char key;
    private String linkToWord;
    private ArrayTrie[] kids;

    public ArrayTrie() {
        this('!');
    }

    private ArrayTrie(char key) {
        this.key = key;
        kids = new ArrayTrie[MAX_CHAR - MIN_CHAR + 1];
    }

    public ITrieNode add(char c) {
        ArrayTrie kid = new ArrayTrie(c);
        kids[c - MIN_CHAR] = kid;
        return kid;
    }

    public ITrieNode get(char c) {
        return kids[c - MIN_CHAR];
    }

    public char getKey() {
        return key;
    }

    @Override
    public boolean exists(String word) {
        ITrieNode node = this;
        for (int i = 0; i < word.length(); i++) {
            node = node.get(word.charAt(i));
            if (node == null) {
                return false;
            }
        }
        return node.getLinkToWord() != null;
    }

    @Override
    public String toString() {
        return String.format("%s %s", key, linkToWord == null ? "" : linkToWord);
    }

    public String getLinkToWord() {
        return linkToWord;
    }

    public void setLinkToWord(String linkToWord) {
        this.linkToWord = linkToWord;
    }
}
