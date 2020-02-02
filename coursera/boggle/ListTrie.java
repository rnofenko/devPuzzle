import java.util.ArrayList;

public class ListTrie implements ITrieNode {
    private final char key;
    private String linkToWord;
    private ArrayList<ListTrie> kids = new ArrayList<>();

    public ListTrie() {
        this('!');
    }

    private ListTrie(char key) {
        this.key = key;
    }

    public ITrieNode add(char c) {
        ListTrie kid = new ListTrie(c);
        kids.add(kid);
        return kid;
    }

    public ITrieNode get(char c) {
        for (ListTrie kid : kids) {
            if (c == kid.key) {
                return kid;
            }
        }
        return null;
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
        return String.format("%s kids=%s %s", key, kids.size(), linkToWord == null ? "" : linkToWord);
    }

    public String getLinkToWord() {
        return linkToWord;
    }

    public void setLinkToWord(String linkToWord) {
        this.linkToWord = linkToWord;
    }
}
