import edu.princeton.cs.algs4.StdOut;

public class TrieBuilder {
    public void build(String[] words, ITrieNode root) {
        parseWords(words, root, 0, 0, words.length - 1);
    }

    private void parseWords(String[] words, ITrieNode parent, int charAt, int wordStart, int wordEnd) {
        int subStartIndex = wordStart;
        String word = words[subStartIndex];
        while (word.length() == charAt) {
            parent.setLinkToWord(word);
            subStartIndex++;
            if (subStartIndex > wordEnd) {
                return;
            }
            word = words[subStartIndex];
        }

        char prevChar = word.charAt(charAt);

        for (int i = subStartIndex + 1; i <= wordEnd; i++) {
            char current = words[i].charAt(charAt);

            if (current == prevChar) {
                continue;
            }

            parseWords(words, parent.add(prevChar), charAt + 1, subStartIndex, i - 1);

            subStartIndex = i;
            prevChar = current;
        }

        parseWords(words, parent.add(prevChar), charAt + 1, subStartIndex, wordEnd);
    }
}
