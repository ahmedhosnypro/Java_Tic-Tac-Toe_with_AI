package tictactoe;

import java.util.Arrays;

public class GridState {
    static String state(Grid grid) {
        String check = "";
        int X = 0;
        int O = 0;
        int empty = 0;
        for (char[] row : grid.getGrid()) {
            for (char ch : row) {
                if (ch == 'X') {
                    X++;
                } else if (ch == 'O') {
                    O++;
                } else if (ch == ' ') {
                    empty++;
                }
            }
        }

        if (Math.abs(X - O) > 1) {
            check = "Impossible";
        } else {
            switch (checkSides(grid)) {
                case "Impossible":
                    check = "Impossible";
                    break;
                case "X wins":
                    check = "X wins";
                    break;
                case "O wins":
                    check = "O wins";
                    break;
                default:
                    if (empty == 0) {
                        check = "Draw";
                    } else if (empty > 0) {
                        if (X <= O){
                            check = "Xstart";
                        }else {
                            check = "Ostart";
                        }
                    }
                    break;
            }
        }
        return check;
    }

    static String stateF(Grid grid) {
        String check = "";
        int X = 0;
        int O = 0;
        int empty = 0;
        for (char[] row : grid.getGrid()) {
            for (char ch : row) {
                if (ch == 'X') {
                    X++;
                } else if (ch == 'O') {
                    O++;
                } else if (ch == ' ') {
                    empty++;
                }
            }
        }

        if (Math.abs(X - O) > 1) {
            check = "Impossible";
        } else {
            switch (checkSides(grid)) {
                case "Impossible":
                    check = "Impossible";
                    break;
                case "X wins":
                    check = "X wins";
                    break;
                case "O wins":
                    check = "O wins";
                    break;
                default:
                    if (empty == 0) {
                        check = "Draw";
                    } else if (empty > 0) {
                        check = "Game not finished";
                    }
                    break;
            }
        }
        return check;
    }

    static char[] simpleSides(Grid grid) {
        char[][] sides = grid.getSides();
        char[] simpleSides = new char[grid.getSides().length];
        Arrays.fill(simpleSides, 'A');
        for (int i = 0; i < sides.length; i++) {
            char last = sides[i][0];
            boolean isInRow = true;
            for (int j = 1; j < 3; j++) {
                if (sides[i][j] == last) {
                    last = sides[i][j];
                } else {
                    isInRow = false;
                    break;
                }
            }
            if (isInRow) {
                simpleSides[i] = sides[i][0];
            }
        }
        return simpleSides;
    }

    static String checkSides(Grid grid) {
        String check = "";
        char[] Sides = new char[8];
        Sides = simpleSides(grid);
        int X = 0;
        int O = 0;
        for (int i = 0; i < Sides.length; i++) {
            if (Sides[i] == 'X') {
                X++;
            } else if (Sides[i] == 'O') {
                O++;
            }
        }
        if ((X > 0 && O > 0)) {
            check = "Impossible";
        } else if (X == 1 && O == 0) {
            check = "X wins";
        } else if (O == 1 && X == 0) {
            check = "O wins";
        }
        return check;
    }
}
