public interface ITrieNode {
    ITrieNode get(char c);
    String getLinkToWord();
    char getKey();
    boolean exists(String word);

    ITrieNode add(char c);
    void setLinkToWord(String linkToWord);
}
