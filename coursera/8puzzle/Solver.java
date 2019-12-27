import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stack;

import java.util.ArrayList;
import java.util.Comparator;

public class Solver {
    private final ArrayList<Board> solution;
    private boolean isSolvable;

    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }

        InnerSolver solverOrigin = new InnerSolver(initial);
        InnerSolver solverTwin = new InnerSolver(initial.twin());
        solution = new ArrayList<>();

        while (true) {
            if (solverOrigin.isSolvable) {
                isSolvable = true;
                for (Board board : solverOrigin.solution) {
                    solution.add(board);
                }
                break;
            } else if (solverTwin.isSolvable) {
                isSolvable = false;
                break;
            }

            solverOrigin.nextStep();
            solverTwin.nextStep();
        }
    }

    public boolean isSolvable() {
        return isSolvable;
    }

    public int moves() {
        return solution.size() - 1;
    }

    public Iterable<Board> solution() {
        return isSolvable ? solution : null;
    }

    private static class InnerSolver {

        private final MinPQ<BoardStep> pq;
        private final Stack<Board> solution = new Stack<>();
        private boolean isSolvable;

        public InnerSolver(Board initial) {
            pq = new MinPQ<>(initial.dimension(), new BoardStepComparator());
            BoardStep wrapper = new BoardStep(initial, null);
            pq.insert(wrapper);

            if (initial.isGoal()) {
                solution.push(initial);
                isSolvable = true;
            }
        }

        public void nextStep() {
            BoardStep out = pq.delMin();
            Board parent = out.parent == null ? null : out.parent.board;

            for (Board neighbor : out.board.neighbors()) {
                if (neighbor.equals(parent)) {
                    continue;
                }

                BoardStep wrapper = new BoardStep(neighbor, out);

                if (neighbor.isGoal()) {
                    while (wrapper != null) {
                        solution.push(wrapper.board);
                        wrapper = wrapper.parent;
                    }
                    isSolvable = true;
                    break;
                }

                pq.insert(wrapper);
            }
        }
    }

    private static class BoardStep {
        private final Board board;
        private final int stepNo;
        private final int manhattan;
        private final BoardStep parent;

        public BoardStep(Board board, BoardStep parent) {

            this.board = board;
            this.stepNo = parent == null ? 0 : parent.stepNo + 1;
            this.manhattan = board.manhattan();
            this.parent = parent;
        }

        @Override
        public String toString() {
            return  "manhattan=" + manhattan + " stepNo=" + stepNo + " board=" + board;
        }
    }

    private static class BoardStepComparator implements Comparator<BoardStep> {

        @Override
        public int compare(BoardStep o1, BoardStep o2) {
            return Integer.compare(o1.manhattan + o1.stepNo, o2.manhattan + o2.stepNo);
        }
    }

    public static void main(String[] args) {

//        args = new String[] { "puzzle17.txt" };
//        args = new String[] { "puzzle4x4-unsolvable.txt" };

        if (args == null || args.length == 0) {

            if (args == null) {
                Stopwatch w = new Stopwatch();
                solve("puzzle4x4-unsolvable.txt", true, true);
                StdOut.println("TIME: " + w.elapsedTime());
            } else {
                for (int i = 0; i <= 50; i++) {
                    Stopwatch w = new Stopwatch();
                    String fileName = "puzzle" + String.format("%2d", i).replace(' ', '0') + ".txt";
                    solve(fileName, false, false);
//                    solve("puzzle4x4-" + String.format("%2d", i).replace(' ', '0') + ".txt");
                    if (w.elapsedTime() > 0.1) {
                        StdOut.println("#" + i + " TIME: " + w.elapsedTime());
                    }
                }
            }
        } else {
            solve(args[0], false, true);
        }
    }

    private static void solve(String fileName, boolean printInitial, boolean printSolution) {
        In in = new In(fileName);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles[i][j] = in.readInt();
            }
        }

        Board initial = new Board(tiles);

        if (printInitial) {
            StdOut.println("Initial: " + initial);
        }

        Solver solver = new Solver(initial);

        StdOut.println(fileName + " number of moves = " + solver.moves());
        if (!solver.isSolvable())
            StdOut.println(fileName + " No solution possible");
        else {
            if (printSolution) {
                for (Board board : solver.solution()) {
                    StdOut.println(board);
                }
            }
        }
    }
}
