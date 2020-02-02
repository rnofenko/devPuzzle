import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Assert;
import org.junit.Test;

public class BoggleSolverTest {

    @Test
    public void perfDictionary() {
        Stopwatch w = new Stopwatch();
        getSolver("data/dictionary-zingarelli2005.txt");
        StdOut.println(w.elapsedTime());
    }

    @Test
    public void testQ() {
        BoggleSolver solver = getSolver();
        BoggleBoard board = new BoggleBoard("data/board-q.txt");

        int score = calcScore(solver, board, true);

        Assert.assertEquals(29, score);
    }

    @Test
    public void testQ2() {
        BoggleSolver solver = new BoggleSolver(new String[] { "QUT", "QUIE" });
        BoggleBoard board = new BoggleBoard("data/board-q.txt");

        int score = calcScore(solver, board, true);

        Assert.assertEquals(2, score);
    }

    @Test
    public void performance() {
        BoggleSolver solver = getSolver();

        Stopwatch w = new Stopwatch();
        for (int i = 0; i < 5000; i++) {
            BoggleBoard board = new BoggleBoard(10, 10);
            solver.getAllValidWords(board);
        }

        StdOut.println(w.elapsedTime());
    }

    @Test
    public void test1() {
        BoggleSolver solver = getSolver();
        BoggleBoard board = new BoggleBoard("data/board4x4.txt");

        int score = calcScore(solver, board, false);

        Assert.assertEquals(33, score);
    }

    private int calcScore(BoggleSolver solver, BoggleBoard board, boolean onlyQ) {
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            if (onlyQ && !word.startsWith("Q")) continue;
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        return score;
    }

    private BoggleSolver getSolver() {
        return getSolver("data/dictionary-algs4.txt");
    }

    private BoggleSolver getSolver(String fileName) {
        In in = new In(fileName);
        String[] dictionary = in.readAllStrings();
        return new BoggleSolver(dictionary);
    }
}
