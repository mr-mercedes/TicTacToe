package academy.devonline.java.basic.section99_profskills;

import java.util.Random;
import java.util.Scanner;

public class TicTakToe {
    public static void main(String[] args) {
        System.out.println("Use the following mapping table to specify a cell using numbers from 1 to 9: ");
        printTableMapping();
        char[][] gameTable = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '},
        };
        if (new Random().nextBoolean()) {
            makeComputerMove(gameTable);
            printGameTable(gameTable);
        }
        while (true) {
            makeUserMove(gameTable);
            printGameTable(gameTable);
            if (isUserWin(gameTable)) {
                System.out.println("YOU WIN");
                break;
            }
            if (isDraw(gameTable)) {
                System.out.println("Sorry Draw");
                break;
            }
            makeComputerMove(gameTable);
            printGameTable(gameTable);
            if (isComputerWin(gameTable)) {
                System.out.println("COMPUTER WIN");
                break;
            }
            if (isDraw(gameTable)) {
                System.out.println("Sorry Draw");
                break;
            }
        }
        System.out.println("GAME OVER");

    }

    private static void printTableMapping() {
        char[][] mappingTable = {
                {'7', '8', '9'},
                {'4', '5', '6'},
                {'1', '2', '3'},
        };
        printGameTable(mappingTable);

    }

    private static void printGameTable(char[][] gameTable) {
        for (int i = 0; i < 3; i++) {
            System.out.println("-------------");
            for (int j = 0; j < 3; j++) {
                System.out.print("| " + gameTable[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("-------------");
    }

    private static void makeUserMove(char[][] gameTable) {
        while (true) {
            System.out.println("Please type number between 1 and 9:");
            Scanner scanner = new Scanner(System.in);
            String string = scanner.nextLine();
            if (string.length() == 1) {
                char digit = string.charAt(0);
                if (makeUserMoveToCell(gameTable, digit)) {
                    return;
                }
            }
        }

    }

    private static boolean makeUserMoveToCell(char[][] gameTable, char digit) {
        char[][] mappingTable = {
                {'7', '8', '9'},
                {'4', '5', '6'},
                {'1', '2', '3'},
        };
        for (int i = 0; i < mappingTable.length; i++) {
            for (int j = 0; j < mappingTable[i].length; j++) {
                if (mappingTable[i][j] == digit) {
                    if (gameTable[i][j] == ' ') {
                        gameTable[i][j] = 'X';
                        return true;
                    } else {
                        System.out.println("Can't make a move, because the cell is not free! Try again!");
                        return false;
                    }
                }
            }
        }
        return false;
    }

    private static void makeComputerMove(char[][] gameTable) {
        Random random = new Random();
        System.out.println("Computer made a move.");
        boolean flag = true;
        while (flag) {
            int row = random.nextInt(3);
            int col = random.nextInt(3);
            if (gameTable[row][col] == ' ') {
                flag = logic(gameTable, row, col);

            }
        }
    }

    private static boolean logic(char[][] gameTable, int row, int col) {
        for (int i = 0; i < 3; i++) {
            if (gameTable[i][0] == gameTable[i][1] && gameTable[i][0] == 'X') {
                if (gameTable[i][2] == ' ') {
                    gameTable[i][2] = '0';
                    return false;
                }
            } else if (gameTable[i][1] == gameTable[i][2] && gameTable[i][1] == 'X') {
                if (gameTable[i][0] == ' ') {
                    gameTable[i][0] = '0';
                    return false;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (gameTable[0][i] == gameTable[1][i] && gameTable[0][i] == 'X') {
                if (gameTable[2][i] == ' ') {
                    gameTable[2][i] = '0';
                    return false;
                }
            } else if (gameTable[1][i] == gameTable[2][i] && gameTable[1][i] == 'X') {
                if (gameTable[0][i] == ' ') {
                    gameTable[0][i] = '0';
                    return false;
                }
            }
        }
        if (gameTable[0][0] == gameTable[1][1] && gameTable[0][0] == 'X') {
            if (gameTable[2][2] == ' ') {
                gameTable[2][2] = '0';
            } else {
                gameTable[row][col] = '0';
            }
            return false;
        } else if (gameTable[1][1] == gameTable[2][2] && gameTable[1][1] == 'X') {
            if (gameTable[0][0] == ' ') {
                gameTable[0][0] = '0';
            } else {
                gameTable[row][col] = '0';
            }
            return false;
        } else if (gameTable[0][2] == gameTable[1][1] && gameTable[0][2] == 'X') {
            if (gameTable[2][0] == ' ') {
                gameTable[2][0] = '0';
            } else {
                gameTable[row][col] = '0';
            }
            return false;
        } else if (gameTable[1][1] == gameTable[2][0] && gameTable[1][1] == 'X') {
            if (gameTable[0][2] == ' ') {
                gameTable[0][2] = '0';
            } else {
                gameTable[row][col] = '0';
            }
            return false;
        } else {
            gameTable[row][col] = '0';
            return false;
        }
    }

    private static boolean isUserWin(char[][] gameTable) {
        return isWinner(gameTable, 'X');
    }

    private static boolean isComputerWin(char[][] gameTable) {
        return isWinner(gameTable, '0');
    }

    private static boolean isWinner(char[][] gameTable, char ch) {
        for (int i = 0; i < 3; i++) {
            if (gameTable[i][0] == gameTable[i][1] && gameTable[i][0] == gameTable[i][2] && gameTable[i][0] == ch) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (gameTable[0][i] == gameTable[1][i] && gameTable[0][i] == gameTable[2][i] && gameTable[0][i] == ch) {
                return true;
            }
        }
        if (gameTable[0][0] == gameTable[1][1] && gameTable[0][0] == gameTable[2][2] && gameTable[0][0] == ch) {
            return true;
        }
        if (gameTable[0][2] == gameTable[1][1] && gameTable[0][2] == gameTable[2][0] && gameTable[0][2] == ch) {
            return true;
        }

        return false;
    }

    private static boolean isDraw(char[][] gameTable) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameTable[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

}
