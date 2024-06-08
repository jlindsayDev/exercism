import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

public class MinesweeperBoard {
    private static final char EMPTY_SPACE = ' ';
    private static final char MINE_CHAR = '*';

    private char[][] board;
    
    public MinesweeperBoard(String board) {
        String[] rows = board.split("\n");
        this.board = Arrays.stream(rows)
            .map(String::toCharArray)
            .toArray(char[][]::new);
    }

    private void countMine(char[][] rows, int r, int c) {
        int[] rCoords = new int[] { r - 1, r, r + 1 };
        int[] cCoords = new int[] { c - 1, c, c + 1 };

        for (int y : rCoords) {
            if (y < 0 || y >= rows.length)
                continue;
            for (int x : cCoords) {
                if (x < 0 || x >= rows[y].length)
                    continue;

                char ch = rows[y][x];
                if (ch == EMPTY_SPACE) {
                    rows[y][x] = '1';
                } else if (Character.isDigit(ch)) {
                    rows[y][x] += 1; // no cell should have over 8 siblings
                }
            }
        }
    }

    public String withNumbers() {
        for (int i = 0; i < this.board.length; ++i)
            for (int j = 0; j < this.board[i].length; ++j)
                if (this.board[i][j] == MINE_CHAR)
                    countMine(this.board, i, j);
        
        StringBuilder sb = new StringBuilder();
        for (char[] row : this.board) {
            sb.append(row);
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String board = String.join("\n",
            "*-----+",
            "| *** |",
            "  * * |",
            "  *** |",
            "   *  *",
            "+- ---+"
        );
        MinesweeperBoard m = new MinesweeperBoard(board);
        System.out.println(m.withNumbers());
    }
}
