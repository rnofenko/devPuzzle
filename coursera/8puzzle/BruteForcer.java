import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.HashSet;

public class BruteForcer {
    public static void main(String[] args) {
        String fileName = "puzzle11.txt";

        In in = new In(fileName);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles[i][j] = in.readInt();
            }
        }

        BfBoard initial = new BfBoard(tiles, null);

        BruteForcer solver = new BruteForcer();
        solver.solve(initial);
    }

    private void solve(BfBoard initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }
        HashSet<BfBoard> set = new HashSet<>();

        ArrayList<BfBoard> boards = new ArrayList<>();
        boards.add(initial);

        while (true) {
            ArrayList<BfBoard> newBoards = new ArrayList<>();

            for (BfBoard board : boards) {
                for (BfBoard neighbor : board.neighbors()) {
                    if (set.contains(neighbor)) {
                        continue;
                    }

                    if (neighbor.isGoal()) {
                        StdOut.println("Solution found");
                        printSolution(neighbor);
                        return;
                    }

                    set.add(neighbor);
                    newBoards.add(neighbor);
                }
            }

            boards = newBoards;
        }
    }

    private void printSolution(BfBoard neighbor) {
        Stack<BfBoard> stack = new Stack<>();
        while (neighbor != null) {
            stack.push(neighbor);
            neighbor = neighbor.parent;
        }

        int stepNo = 1;
        for (BfBoard bfBoard : stack) {
            StdOut.println(String.format("STEP %s manhattan=%s", stepNo++, bfBoard.manhattan()));
            StdOut.println(bfBoard);
        }
    }
}
