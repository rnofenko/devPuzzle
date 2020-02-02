import java.util.HashSet;
import java.util.Set;

public class WordsCollector {

    private static final int MIN_WORD_LENGTH = 3;
    private static final char SPECIAL_Q = 'Q';
    private static final char SPECIAL_U = 'U';
    private final int width;
    private final int height;
    private final char[] board;

    public WordsCollector(BoggleBoard board) {
        this.width = board.cols();
        this.height = board.rows();
        this.board = convert(board);
    }

    public Set<String> collect(ITrieNode root) {
        Set<String> matched = new HashSet<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                boolean[] visited = new boolean[board.length];
                collect(root, matched, y, x, visited);
            }
        }
        return matched;
    }

    private void collect(ITrieNode parent, Set<String> matched, int y, int x, boolean[] visited) {
        if (y < 0 || x < 0 || y >= height || x >= width) {
            return;
        }

        int idx = y * width + x;
        if (visited[idx]) {
            return;
        }

        ITrieNode kid = parent.get(board[idx]);
        if (kid == null) {
            return;
        }
        if (kid.getKey() == SPECIAL_Q) {
            kid = kid.get(SPECIAL_U);
            if (kid == null) {
                return;
            }
        }

        String word = kid.getLinkToWord();
        if (word != null && word.length() >= MIN_WORD_LENGTH) {
            matched.add(word);
        }

        visited[idx] = true;

        collect(kid, matched, y - 1, x -1, visited);
        collect(kid, matched, y - 1, x, visited);
        collect(kid, matched, y - 1, x + 1, visited);
        collect(kid, matched, y, x -1, visited);
        collect(kid, matched, y, x + 1, visited);
        collect(kid, matched, y + 1, x -1, visited);
        collect(kid, matched, y + 1, x, visited);
        collect(kid, matched, y + 1, x + 1, visited);

        visited[idx] = false;
    }

    private char[] convert(BoggleBoard board) {
        char[] result = new char[width * height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result[y * width + x] = board.getLetter(y, x);
            }
        }

        return result;
    }
}
