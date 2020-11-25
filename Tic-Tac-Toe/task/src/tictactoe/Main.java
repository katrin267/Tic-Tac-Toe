package tictactoe;

import java.util.Scanner;


public class Main {
    final static Scanner scanner = new Scanner(System.in);
    static char[][] field;
    final static int ROWS = 3;
    final static char X = 'X';
    final static char O = 'O';
    static char currentMove = X;

    public static void main(String[] args) {
        field = new char[ROWS][ROWS];

        fillField();
        printField();

        do {
            makeMove();
        } while (continueGame());
    }

    private static void makeMove() {
        String input1;
        String input2;
        do {
            System.out.print("Enter the coordinates: ");
            input1 = scanner.next();
            input2 = scanner.next();
        } while (!checkInput(input1, input2));

        int x = Integer.parseInt(input1);
        int y = Integer.parseInt(input2);

        addMove(x, y);
        printField();
    }

    private static boolean checkInput(String input1, String input2) {
        if (!input1.matches("\\d") || !input2.matches("\\d")) {
            System.out.println("You should enter numbers!");
            return false;
        }

        int xCoord = Integer.parseInt(input1);
        int yCoord = Integer.parseInt(input2);

        if (xCoord < 0 || xCoord > 3 || yCoord < 0 || yCoord > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }

        if (isOccupied(xCoord, yCoord)) {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }

        return true;
    }

    private static boolean isOccupied(int x, int y) {
        int row = ROWS - y;
        int col = x - 1;
        return field[row][col] != '_';
    }

    private static boolean continueGame() {
        boolean xWin = false;
        boolean oWin = false;
        int numOfX = 0;
        int numOfO = 0;
        int numOf_ = 0;

        for (int i = 0; i < ROWS; i++) {
            xWin = xWin || checkRow(i) == X || checkCol(i) == X;
            oWin = oWin || checkRow(i) == O || checkCol(i) == O;
        }

        for (int i = 0; i < 2; i++) {
            xWin = xWin || checkDiag(i) == X;
            oWin = oWin || checkDiag(i) == O;
        }

        if (xWin && oWin) {
            System.out.println("Impossible");
            return false;
        }

        for (char[] row :
                field) {
            for (char place :
                    row) {
                switch (place) {
                    case X:
                        numOfX++;
                        break;
                    case O:
                        numOfO++;
                        break;
                    case '_':
                        numOf_++;
                        break;
                    default:
                        System.out.println("Impossible");
                        return false;
                }
            }
        }

        if (Math.abs(numOfO - numOfX) > 1) {
            System.out.println("Impossible");
            return false;
        }

        if (!xWin && !oWin) {
            if (numOf_ == 0) {
                System.out.println("Draw");
                return false;
            } else {
//                System.out.println("Game not finished");
                return true;
            }
        }

        System.out.println(xWin ? "X wins" : "O wins");
        return false;
    }

    private static char checkRow(int num) {
        for (int i = 1; i < ROWS; i++) {
            if (field[num][i - 1] != field[num][i]) {
                return 0;
            }
        }
        return field[num][0];
    }

    private static char checkCol(int num) {
        for (int i = 1; i < ROWS; i++) {
            if (field[i - 1][num] != field[i][num]) {
                return 0;
            }
        }
        return field[0][num];
    }

    private static char checkDiag(int num) {
        for (int i = 1; i < ROWS; i++) {
            if (field[i - 1][Math.abs(i - 1 - (ROWS - 1) * num)] != field[i][Math.abs(i - (ROWS - 1) * num)]) {
                return 0;
            }
        }
        return field[0][(ROWS - 1) * num];
    }


    public static void printField() {
        System.out.println("---------");
        for (char[] row :
                field) {
            System.out.print("| ");
            for (char place :
                    row) {
                System.out.print(place + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static void fillField() {
//        System.out.print("Enter cells: ");
//        String str = scanner.nextLine();
        String str = "_________";

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < ROWS; j++) {
                field[i][j] = str.charAt(i * ROWS + j);
            }
        }
    }

    public static void addMove(int x, int y) {
        int row = ROWS - y;
        int col = x - 1;

        field[row][col] = currentMove;
        currentMove = currentMove == X ? O : X;
    }
}
