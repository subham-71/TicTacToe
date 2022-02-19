public class Result {

    // flag variables
    public static int NOT_OVER = 1;
    public static int X_WINS = 2;
    public static int O_WINS = 3;

    // to check if there are three consecutive symbols in any orientation

    public static int game_result(int[][] board, int row, int col) {

        boolean check = false;
        int symbol = board[row][col];
        if (symbol == 0)
            return NOT_OVER;

        // towards E
        check = (col + 1 > 2);
        if (!check && (board[row][col + 1] == symbol)) {
            check = (col + 2 > 2);
            if (!check && (board[row][col + 2] == symbol)) {
                if (symbol == 1) {
                    return X_WINS;
                } else {
                    return O_WINS;
                }
            }
        }

        // towards SE
        check = (row + 1 > 2) || (col + 1 > 2);
        if (!check && (board[row + 1][col + 1] == symbol)) {
            check = row + 2 > 2 || (col + 2 > 2);
            if (!check && (board[row + 2][col + 2] == symbol)) {
                if (symbol == 1) {
                    return X_WINS;
                } else {
                    return O_WINS;
                }
            }
        }

        // towards S
        check = (row + 1 > 2);
        if (!check && (board[row + 1][col] == symbol)) {
            check = row + 2 > 2;
            if (!check && (board[row + 2][col] == symbol)) {
                if (symbol == 1) {
                    return X_WINS;
                } else {
                    return O_WINS;
                }
            }
        }

        // towards SW
        check = (row + 1 > 2) || (col - 1 < 0);
        if (!check && (board[row + 1][col - 1] == symbol)) {
            check = row + 2 > 2 || (col - 2 < 0);
            if (!check && (board[row + 2][col - 2] == symbol)) {
                if (symbol == 1) {
                    return X_WINS;
                } else {
                    return O_WINS;
                }
            }
        }

        // towards W
        check = (col - 1 < 0);
        if (!check && (board[row][col - 1] == symbol)) {
            check = (col - 2 < 0);
            if (!check && (board[row][col - 2] == symbol)) {
                if (symbol == 1) {
                    return X_WINS;
                } else {
                    return O_WINS;
                }
            }
        }

        // towards NW
        check = (row - 1 < 0) || (col - 1 < 0);
        if (!check && (board[row - 1][col - 1] == symbol)) {
            check = (row - 2 < 0) || (col - 2 < 0);
            if (!check && (board[row - 2][col - 2] == symbol)) {
                if (symbol == 1) {
                    return X_WINS;
                } else {
                    return O_WINS;
                }
            }
        }

        // towards N
        check = (row - 1 < 0);
        if (!check && (board[row - 1][col] == symbol)) {
            check = (row - 2 < 0);
            if (!check && (board[row - 2][col] == symbol)) {
                if (symbol == 1) {
                    return X_WINS;
                } else {
                    return O_WINS;
                }
            }
        }

        // towards NE
        check = (row - 1 < 0) || (col + 1 > 2);
        if (!check && (board[row - 1][col + 1] == symbol)) {
            check = (row - 2 < 0) || (col + 2 > 2);
            if (!check && (board[row - 2][col + 2] == symbol)) {
                if (symbol == 1) {
                    return X_WINS;
                } else {
                    return O_WINS;
                }
            }
        }

        return NOT_OVER;
    }

    // goes through all cells and checks if there are three consecutive symbols in
    // any direction
    public static int result(int[][] board) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int res = game_result(board, row, col);
                if (res != NOT_OVER) {
                    return res;
                }
            }
        }

        return NOT_OVER;
    }

}
