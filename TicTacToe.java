import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {

        Board board = new Board();
        board.boardnumber();
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter 1 for two player.\nEnter 2 for playing against computer\n");
        int choice = sc.nextInt();

        int position;
        int winner = 1;
        while (!board.full()) {
            winner = Result.result(board.getBoard());
            if (winner != 1) {
                break;
            }

            System.out.println("Player 1.\n Please enter a position:");

            position = sc.nextInt();
            Boolean accepted1 = false;
            while (true) {
                accepted1 = Board.place(position, "X");
                if (accepted1) {
                    break;
                } else {
                    System.out.println("Player 1.\n Invalid Position. Please enter another position:");
                    position = sc.nextInt();
                }
            }

            board.display();
            winner = Result.result(board.getBoard());
            if (winner != 1) {
                break;
            }

            if (board.full()) {
                break;
            }
            if (choice == 1) {
                System.out.println("Player 2.\n Please enter a position:");

                position = sc.nextInt();
                Boolean accepted2 = false;
                while (true) {
                    accepted2 = Board.place(position, "O");
                    if (accepted2) {
                        break;
                    } else {
                        System.out.println("Player 2.\n Invalid Position. Please enter another position:");
                        position = sc.nextInt();
                    }
                }
            } else {

                System.out.println("\nComputer's Turn");

                Boolean accepted2 = false;
                while (true) {
                    accepted2 = board.computer_plays();
                    if (accepted2) {
                        break;
                    }
                }
            }

            board.display();
            winner = Result.result(board.getBoard());
            if (winner != 1)
                break;
        }

        sc.close();
        System.out.println(" \n GAME OVER!!!");
        System.out.println(" \n Final Game Board \n");
        if (winner == 2) {
            board.display();
            System.out.println(" \nX WON");
        } else if (winner == 3) {
            board.display();
            System.out.println(" \nO WON");
        } else {
            board.display();
            System.out.println(" \nTIED");
        }

    }
}