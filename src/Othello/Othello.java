package Othello;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Player {
    private String name;
    private int color;

    public Player(String name, int color) {
        setName(name);
        setColor(color);
    }

    public void setName(String name) {
        if (!name.isEmpty())
            this.name = name;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }
}

class Board {
    private int boardSize = 8;
    private int[][] board;
    private int count;
    private final int p1Color;
    private final int p2Color;
    public static final int PLAYER_1_WON = 1;
    public static final int PLAYER_2_WON = 2;
    public static final int DRAW = 3;
    public static final int INCOMPLETE = 4;
    public static final int INVALID = 5;
    public int p1Checked;
    public int p2Checked;

    public Board(int p1Color, int p2Color) {
        board = new int[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = 0;
            }
        }
        board[3][3] = 1;
        board[4][4] = 1;
        board[3][4] = 2;
        board[4][3] = 2;
        this.p1Color = p1Color;
        this.p2Color = p2Color;
        this.p1Checked = 2;
        this.p2Checked = 2;
    }

    public int move(int color, int x, int y) {
        if (x < 0 || x > boardSize - 1 || y < 0 || y > boardSize - 1 || board[x][y] != 0) {
            return Board.INVALID;
        } else {
            int[] xDir = {0, 1, 1, 1, 0, -1, -1, -1};
            int[] yDir = {1, 1, 0, -1, -1, -1, 0, 1};
            int opponentColor = color == 1 ? 2 : 1;
            boolean tryAgain = true;
            for (int i = 0; i < xDir.length; i++) {
                int xStep = xDir[i];
                int yStep = yDir[i];
                int currX = x + xStep;
                int currY = y + yStep;
                boolean isFound = false;
                ArrayList<Integer> xCoord = new ArrayList<>();
                ArrayList<Integer> yCoord = new ArrayList<>();
                while ((currX > -1 && currY > -1) && (currX < boardSize && currY < boardSize) && board[currX][currY] != 0) {
                    if (board[currX][currY] == opponentColor) {
                        xCoord.add(currX);
                        yCoord.add(currY);
                        currX += xStep;
                        currY += yStep;
                    } else if (board[currX][currY] == color && !xCoord.isEmpty()) {
                        isFound = true;
                        break;
                    } else {
                        break;
                    }
                }
                if (isFound && !xCoord.isEmpty()) {
                    tryAgain = false;
                    board[x][y] = color;
                    for (int itr = 0; itr < xCoord.size(); itr++) {
                        board[xCoord.get(itr)][yCoord.get(itr)] = color;
                        if (color == p1Color) {
                            p1Checked++;
                        } else {
                            p2Checked++;
                        }
                    }
                    count++;
                }
            }
            if (tryAgain) {
                System.out.println("false");
                return Board.INCOMPLETE;
            }
            if (count == boardSize * boardSize) {
                if (p1Checked == p2Checked) {
                    return DRAW;
                }
                return p1Checked > p2Checked ? PLAYER_1_WON : PLAYER_2_WON;
            }
        }
        return Board.INCOMPLETE;
    }

    public void print() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}

public class Othello {
    private Player p1, p2;
    private Board board;

    public void startGame() {
        Scanner sc = new Scanner(System.in);
        p1 = createUser(1);
        p2 = createUser(2);
        board = new Board(p1.getColor(), p2.getColor());
        boolean player1Turn = true;
        int status = Board.INCOMPLETE;
        while (status == Board.INCOMPLETE || status == Board.INVALID) {
            if (player1Turn) {
                System.out.println("Enter Player 1 - " + p1.getName() + "'s x coordinate");
                int x = sc.nextInt();
                System.out.println("Enter Player 1 - " + p1.getName() + "'s y coordinate");
                int y = sc.nextInt();
                status = board.move(p1.getColor(), x, y);
                if (status != Board.INVALID) {
                    player1Turn = false;
                    board.print();
                } else {
                    System.out.println("Invalid Move! Try Again !!");
                }
            } else {
                System.out.println("Enter Player 2 - " + p2.getName() + "'s x coordinate");
                int x = sc.nextInt();
                System.out.println("Enter Player 2 - " + p2.getName() + "'s y coordinate");
                int y = sc.nextInt();
                status = board.move(p2.getColor(), x, y);
                if (status != Board.INVALID) {
                    player1Turn = true;
                    board.print();
                } else {
                    System.out.println("Invalid Move! Try Again !!");
                }
            }
            if (status == Board.PLAYER_1_WON) {
                System.out.println("Player 1 - " + p1.getName() + " wins !!");
            } else if (status == Board.PLAYER_2_WON) {
                System.out.println("Player 2 - " + p2.getName() + " wins !!");
            } else if (status == Board.DRAW) {
                System.out.println("Draw !!");
            }
        }
    }

    private Player createUser(int num) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter player " + num + " name");
        String name = sc.next();
        System.out.println("Enter player " + num + " color");
        int color = sc.nextInt();
        return new Player(name, color);
    }
}
