package TicTacToe;

import java.util.Arrays;
import java.util.Scanner;

class Player {
    private String name;
    private char symbol;

    public Player(String name, char symbol) {
        setName(name);
        setSymbol(symbol);
    }

    public void setName(String name) {
        if (!name.isEmpty()) this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSymbol(char symbol) {
        if (symbol != '\0') this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}

class Board {
    private int sizeOfBoard = 3;
    private final char[][] board;
    private final char p1Symbol;
    private final char p2Symbol;
    private int count;
    public static final int PLAYER_1_WINS = 1;
    public static final int PLAYER_2_WINS = 2;
    public static final int DRAW = 3;
    public static final int INCOMPLETE = 4;
    public static final int INVALID = 5;

    public Board(char p1Symbol, char p2Symbol) {
        board = new char[sizeOfBoard][sizeOfBoard];
        for (int i = 0; i < sizeOfBoard; i++) {
            for (int j = 0; j < sizeOfBoard; j++) {
                board[i][j] = ' ';
            }
        }
        this.p1Symbol = p1Symbol;
        this.p2Symbol = p2Symbol;
    }

    public int move(char symbol, int x, int y) {
        if (x < 0 || x > this.sizeOfBoard - 1 || y < 0 || y > this.sizeOfBoard || board[x][y] != ' ') {
            return Board.INVALID;
        } else {
            this.board[x][y] = symbol;
            this.count++;
            if (board[x][0] == board[x][1] && board[x][1] == board[x][2]) {
                return symbol == p1Symbol ? Board.PLAYER_1_WINS : Board.PLAYER_2_WINS;
            }
            if (board[0][y] == board[1][y] && board[1][y] == board[2][y]) {
                return symbol == p1Symbol ? PLAYER_1_WINS : PLAYER_2_WINS;
            }
            if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
                return symbol == p1Symbol ? PLAYER_1_WINS : PLAYER_2_WINS;
            }
            if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
                return symbol == p1Symbol ? PLAYER_1_WINS : PLAYER_2_WINS;
            }
            if (count == sizeOfBoard * sizeOfBoard) {
                return DRAW;
            }
            return INCOMPLETE;
        }
    }

    public void print() {
        System.out.println("--------------------");
        for (int i = 0; i < sizeOfBoard; i++) {
            for (int j = 0; j < sizeOfBoard; j++) {
                System.out.print("| " + board[i][j] + " |");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("--------------------");
    }
}

public class TicTacToe {
    private Player player1, player2;
    private Board board;

    public void startGame() {
//        Players input
        Scanner sc = new Scanner(System.in);
        Player p1 = takePlayerInput(1);
        Player p2 = takePlayerInput(2);
        while (p1.getSymbol() == p2.getSymbol()) {
            System.out.println("Symbol already take.Try other Symbol!");
            char symbolType = sc.next().charAt(0);
            p2.setSymbol(symbolType);
        }
//        Create Board
        Board board = new Board(p1.getSymbol(), p2.getSymbol());
//        Conduct Game
        boolean player1Turn = true;
        int status = Board.INVALID;
        while (status == Board.INCOMPLETE || status == Board.INVALID) {
            if (player1Turn) {
                System.out.println("Player 1 - " + p1.getName() + "'s turn");
                System.out.println("Enter x-coordinate: ");
                int x = sc.nextInt();
                System.out.println("Enter y-coordinate: ");
                int y = sc.nextInt();
                status = board.move(p1.getSymbol(), x, y);
                if (status != Board.INVALID) {
                    player1Turn = false;
                    board.print();
                } else {
                    System.out.println("Invalid Move! Try Again!!");
                }
            } else {
                System.out.println("Player 2 - " + p2.getName() + "'s turn");
                System.out.println("Enter x-coordinate: ");
                int x = sc.nextInt();
                System.out.println("Enter y-coordinate: ");
                int y = sc.nextInt();
                status = board.move(p2.getSymbol(), x, y);
                if (status != Board.INVALID) {
                    player1Turn = true;
                    board.print();
                } else {
                    System.out.println("Invalid Move! Try Again!!");
                }
            }
            if (status == Board.PLAYER_1_WINS) {
                System.out.println("Player 1 - " + p1.getName() + " wins !!");
            } else if (status == Board.PLAYER_2_WINS) {
                System.out.println("Player 2 - " + p2.getName() + " wins !!");
            } else if (status == Board.DRAW) {
                System.out.println("Draw !!");
            }
        }
    }

    private Player takePlayerInput(int num) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter player " + num + " name: ");
        String playerName = sc.next();
        System.out.println("Enter player " + num + " symbol: ");
        char symbolType = sc.next().charAt(0);
        return new Player(playerName, symbolType);
//        sc.close();
    }
}
