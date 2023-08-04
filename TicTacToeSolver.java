import java.util.*;
import java.io.*;

public class TicTacToeSolver {
    public static void main(String[] args) {
        List<String> board = new ArrayList<>();
        board.add("...");
        board.add("...");
        board.add("...");
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
    private final List<String> board;
    private int movesLeft;
    private boolean xWon;
    private boolean oWon;
    private boolean draw;

    public TicTacToe(List<String> board) {
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
        for (String row : board) {
            System.out.println(row);
        }
    }

    public void markCell(int row, int col, char player) {
        StringBuilder sb = new StringBuilder(board.get(row));
        sb.setCharAt(col, player);
        board.set(row, sb.toString());
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
            if (board.get(i).charAt(0) == player && board.get(i).charAt(1) == player && board.get(i).charAt(2) == player) {
                return true;
            }
        }
        return false;
    }

    private boolean hasPlayerWonVertically(char player) {
        for (int i = 0; i < 3; i++) {
            if (board.get(0).charAt(i) == player && board.get(1).charAt(i) == player && board.get(2).charAt(i) == player) {
                return true;
            }
        }
        return false;
    }

    private boolean hasPlayerWonDiagonally(char player) {
        if (board.get(0).charAt(0) == player && board.get(1).charAt(1) == player && board.get(2).charAt(2) == player) {
            return true;
        }
        if (board.get(0).charAt(2) == player && board.get(1).charAt(1) == player && board.get(2).charAt(0) == player) {
            return true;
        }
        return false;
    }
}