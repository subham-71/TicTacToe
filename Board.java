import java.util.Random;

public class Board {

    private static int[][] board = new int[3][3];

    // - -> 0
    // X -> 1
    // O -> 2

    public int[][] getBoard() {
        return board;
    }

    // instruction about position numbers in the board
    public void boardnumber() {
        System.out.println("| 1 | 2 | 3 |");
        System.out.println("| 4 | 5 | 6 |");
        System.out.println("| 7 | 8 | 9 |");
    }

    // updates the row with the user/computer input
    private String display_row(int row) {
        String s = "|";
        for (int i = 0; i < 3; i++) {
            if (board[row][i] == 0) {
                s = s + " - ";
            } else if (board[row][i] == 1) {
                s = s + " X ";
            } else { // board[row][i]==2
                s = s + " O ";
            }
            s = s + "|";
        }
        return s;
    }

    // displays the board after each turn
    public void display() {
        System.out.println(display_row(0));
        System.out.println(display_row(1));
        System.out.println(display_row(2));
    }

    // to place the symbol in the board
    public static boolean place(int position, String symbol) {
        int row = (position - 1) / 3;
        int col = (position - 1) % 3;
        if (board[row][col] == 0) {
            if (symbol == "X") {
                board[row][col] = 1;
            }
            if (symbol == "O") {
                board[row][col] = 2;
            }
            return true;
        }
        return false;
    }

    // to check if the board is full or not
    public boolean full() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // checks that if any consecutive 3 symbols are going to occur in any of the 8
    // directions for a cell
    public static int streak(int[][] board, int row, int col) {

        boolean check = false;
        int symbol = board[row][col];
        if (symbol == 0)
            return 10;

        // towards E
        check = (col + 1 > 2);
        if (!check && (board[row][col + 1] == symbol)) {
            check = (col + 2 > 2);
            if (!check) {
                return (row) * 3 + col + 2;
            }
        }

        // towards SE
        check = (row + 1 > 2) || (col + 1 > 2);
        if (!check && (board[row + 1][col + 1] == symbol)) {
            check = row + 2 > 2 || (col + 2 > 2);
            if (!check) {
                return (row + 2) * 3 + col + 2;
            }
        }

        // towards S
        check = (row + 1 > 2);
        if (!check && (board[row + 1][col] == symbol)) {
            check = row + 2 > 2;
            if (!check) {
                return (row + 2) * 3 + col;
            }
        }

        // towards SW
        check = (row + 1 > 2) || (col - 1 < 0);
        if (!check && (board[row + 1][col - 1] == symbol)) {
            check = row + 2 > 2 || (col - 2 < 0);
            if (!check) {
                return (row + 2) * 3 + col - 2;
            }
        }

        // towards W
        check = (col - 1 < 0);
        if (!check && (board[row][col - 1] == symbol)) {
            check = (col - 2 < 0);
            if (!check) {
                return (row) * 3 + col - 2;
            }
        }

        // towards NW
        check = (row - 1 < 0) || (col - 1 < 0);
        if (!check && (board[row - 1][col - 1] == symbol)) {
            check = (row - 2 < 0) || (col - 2 < 0);
            if (!check) {
                return (row - 2) * 3 + col - 2;
            }
        }

        // towards N
        check = (row - 1 < 0);
        if (!check && (board[row - 1][col] == symbol)) {
            check = (row - 2 < 0);
            if (!check) {
                return (row - 2) * 3 + col;
            }
        }

        // towards NE
        check = (row - 1 < 0) || (col + 1 > 2);
        if (!check && (board[row - 1][col + 1] == symbol)) {
            check = (row - 2 < 0) || (col + 2 > 2);
            if (!check) {
                return (row - 2) * 3 + col + 2;
            }
        }

        return 10;
    }

    // goes through all the cells and checks if any consecutive 3 'O' symbols are
    // going to occur for each of them and places 'O' for computer to complete the
    // streak and win the game

    public static Boolean streak_win(int[][] board) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int streakstate = streak(board, row, col);

                if (streakstate != 10) {
                    // if streak of 'O' is detected
                    if (board[row][col] == 2) {
                        int nrow = streakstate / 3;
                        int ncol = streakstate % 3;
                        if (board[nrow][ncol] != 0) {
                            return false;
                        }
                        place(streakstate + 1, "O");
                        return true;
                    }

                }
            }
        }
        return false;

    }

    // goes through all the cells and checks if any consecutive 3 'X' symbols are
    // going to occur for each of them and places 'O' for computer to block the
    // streak

    public static Boolean streak_block(int[][] board) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int streakstate = streak(board, row, col);

                if (streakstate != 10) {
                    if (board[row][col] == 1) {
                        int nrow = streakstate / 3;
                        int ncol = streakstate % 3;
                        if (board[nrow][ncol] != 0) {
                            return false;
                        }
                        place(streakstate + 1, "O");
                        return true;
                    }
                }
            }
        }
        return false;

    }

    // Computer first tries to complete 'O' streak and win the game,
    // if not possible, it tries to block the 'X' streak,
    // else it places randomly

    public boolean computer_plays() {
        Boolean check2 = false;
        Boolean check1 = streak_win(board);
        if (!check1) {
            check2 = streak_block(board);
        }
        Boolean check = check1 || check2;
        if (!check) {
            int row = new Random().nextInt(3);
            int col = new Random().nextInt(3);
            if (board[row][col] == 0) {
                board[row][col] = 2;
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }

    }
}
