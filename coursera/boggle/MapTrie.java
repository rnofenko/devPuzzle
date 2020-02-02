import java.util.HashMap;
import java.util.Map;

public class MapTrie implements ITrieNode {
    private final char key;
    private String linkToWord;
    private Map<Character, MapTrie> kids = new HashMap<>();

    public MapTrie() {
        this('!');
    }

    private MapTrie(char key) {
        this.key = key;
    }

    public ITrieNode add(char c) {
        MapTrie kid = new MapTrie(c);
        kids.put(c, kid);
        return kid;
    }

    public ITrieNode get(char c) {
        return kids.get(c);
    }

    public char getKey() {
        return key;
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
}
