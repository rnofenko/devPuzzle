package rn.puzzle.graph.easy;

import org.junit.Assert;
import org.junit.Test;
import rn.tool.StringToArrayConverter;

public class MinesweeperTest {

    @Test
    public void test1() {
        char[][] input = StringToArrayConverter.INSTANCE.stringArrayToDoubleCharArray(
                new String[] { "EEEEE", "EEMEE", "EEEEE", "EEEEE" });
        char[][] result = updateBoard(input, new int[]{3, 0});
        String[] resultS = StringToArrayConverter.INSTANCE.doubleCharArrayToStringArray(result);
        Assert.assertEquals("B1E1B",  resultS[0]);
        Assert.assertEquals("B1M1B",  resultS[1]);
        Assert.assertEquals("B111B",  resultS[2]);
        Assert.assertEquals("BBBBB",  resultS[3]);

    }

    final char M = 'M';//unrevealed mine
    final char X = 'X';//revealed mine
    final char E = 'E';//unrevealed empty
    final char B = 'B';//revealed empty
    final char T = 'T';//revealed empty

    public char[][] updateBoard(char[][] board, int[] click) {
        int y = click[0];
        int x = click[1];

        if (board[y][x] == M) {
            board[y][x] = X;
        } else {
            revealBlank(board, y, x);
        }

        return board;
    }

    private void revealBlank(char[][] board, int y, int x) {
        if (y < 0 || x < 0 || y == board.length || x == board[0].length) {
            return;
        }

        if (board[y][x] != E) {
            return;
        }

        int mines = countMines(board, y, x);
        if (mines == 0) {
            board[y][x] = B;

            revealBlank(board, y - 1, x - 1);
            revealBlank(board, y - 1, x);
            revealBlank(board, y - 1, x + 1);
            revealBlank(board, y, x - 1);
            revealBlank(board, y, x + 1);
            revealBlank(board, y + 1, x - 1);
            revealBlank(board, y + 1, x);
            revealBlank(board, y + 1, x + 1);
        } else {
            board[y][x] = Character.forDigit(mines, 10);
        }
    }

    private int countMines(char[][] board, int y, int x) {
        int minesCount = getOneIfMine(board, y - 1, x - 1);
        minesCount += getOneIfMine(board, y - 1, x);
        minesCount += getOneIfMine(board, y - 1, x + 1);
        minesCount += getOneIfMine(board, y, x - 1);
        minesCount += getOneIfMine(board, y, x + 1);
        minesCount += getOneIfMine(board, y + 1, x - 1);
        minesCount += getOneIfMine(board, y + 1, x);
        return minesCount + getOneIfMine(board, y + 1, x + 1);
    }

    private int getOneIfMine(char[][] board, int y, int x) {
        return getCell(board, y, x) == M ? 1 : 0;
    }

    private char getCell(char[][] board, int y, int x) {
        if (y < 0 || x < 0 || y == board.length || x == board[0].length) {
            return T;
        }
        return board[y][x];
    }
}
