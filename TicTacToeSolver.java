import java.util.*;
import java.io.*;

public class TicTacToeSolver {
    public static void main(String[] args) {
        char[][] board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            Arrays.fill(board[i], '.');
        }
        TicTacToe game = new TicTacToe(board);
        Scanner sc = new Scanner(System.in);
        char player = 'X';
        while (true) {
            game.showBoard();
            System.out.println("Enter row and column for player " + player);
            int row = sc.nextInt();
            int col = sc.nextInt();
            game.markCell(row, col, player);
            if (game.isOver()) {
                break;
            }
            player = player == 'X' ? 'O' : 'X';
        }

        String result = game.hasXWon() ? "Player X has won!" :
                        (game.hasOWon() ? "Player O has won!" : "It's a draw!");
        System.out.println(result);
    }
}

class TicTacToe {
    private final char[][] board;
    private int movesLeft;
    private boolean xWon;
    private boolean oWon;
    private boolean draw;

    public TicTacToe(char[][] board) {
        this.board = board;
        this.movesLeft = 9;
        xWon = false;
        oWon = false;
        draw = false;
    }

    public boolean hasXWon() {
        return xWon;
    }

    public boolean hasOWon() {
        return oWon;
    }

    public boolean isOver() {
        return xWon || oWon || movesLeft == 0;
    }

    public void showBoard() {
        for (char[] row : board) {
            for(char ch : row) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
    }

    public void markCell(int row, int col, char player) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 3; i++) {
            sb.append(board[row][i]);
        }
        sb.setCharAt(col, player);
        for(int i = 0; i < 3; i++) {
            board[row][i] = sb.charAt(i);
        }
        movesLeft--;
        if(hasPlayerWon(player)) {
            if(player == 'X') {
                xWon = true;
            } else {
                oWon = true;
            }
        }
    }

    private boolean hasPlayerWon(char player) {
        return hasPlayerWonHorizontally(player) || hasPlayerWonVertically(player) || hasPlayerWonDiagonally(player);
    }

    private boolean hasPlayerWonHorizontally(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }
        return false;
    }

    private boolean hasPlayerWonVertically(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        return false;
    }

    private boolean hasPlayerWonDiagonally(char player) {
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }
}