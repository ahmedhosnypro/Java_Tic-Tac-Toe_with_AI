package tictactoe;

import java.util.Random;
import java.util.Scanner;

import static tictactoe.Move.chekMove;
import static tictactoe.Move.makeMove;


public class AIMove {
    static void startAIMove(Grid grid, char XO, String level) {
        int[] cord = new int[2];
        switch (level) {
            case "EASY":
                while (easyMove(grid, XO)) ;
                break;
            case "MEDIUM":
                while (mediumMove(grid, XO)) ;
                break;
            default:
                break;
        }
    }

    static boolean easyMove(Grid grid, char XO) {
        boolean isContinue = true;
        System.out.print("Making move level \"easy\"");
        int[] cord = easyMoveCord(grid);
        if (chekMove(cord, grid)) {
            makeMove(cord, grid, XO);
            isContinue = false;
        }
        return isContinue;
    }

    static boolean mediumMove(Grid grid, char XO) {
        boolean isContinue = true;
        System.out.print("Making move level \"medium\"");
        int[] cord = mediumMoveCord(grid, XO);
        if (chekMove(cord, grid)) {
            makeMove(cord, grid, XO);
            isContinue = false;
        }
        return isContinue;
    }

    static int[] easyMoveCord(Grid grid) {
        Random random = new Random();
        return new int[]{random.nextInt(3), random.nextInt(3)};
    }

    static int[] mediumMoveCord(Grid grid, char currentTurnXO) {
        int[] cord = {-1, -1};
        char[][] side = grid.getSides();
        OUTER:
        for (int i = 0; i < side.length; i++) {
            int X = 0, Y = 0;
            for (int j = 0; j < 3; j++) {
                if (side[i][j] == 'X') {
                    X++;
                } else if (side[i][j] == 'Y') {
                    Y++;
                }
                if ((X == 2 && currentTurnXO == 'O') || (Y == 2 && currentTurnXO == 'X')) {
                    for (int k = 0; k < 3; k++) {
                        if (side[i][k] == ' ') {
                            int x = -1;
                            int y = -1;
                            switch (i){
                                case 0:
                                case 1:
                                case 2:
                                    x = i;
                                    y = k;
                                    break;
                                case 3:
                                case 4:
                                case 5:
                                    x = k;
                                    y = i - 3;
                                    break;
                                case 6:
                                    x = k;
                                    y = k;
                                    break;
                                case 7:
                                    if (k == 0){
                                        x = 0;
                                        y = 2;
                                    }
                                    else if (k == 1){
                                        x = 1;
                                        y = 1;
                                    }
                                    else {
                                        x = 2;
                                        y = 0;
                                    }
                                    break;
                            }
                            cord[0] = x;
                            cord[1] = y;
                            break OUTER;
                        }
                    }
                }
            }
        }
        if (cord[0] == -1) {
            cord = easyMoveCord(grid);
        }

        return cord;
    }
}
