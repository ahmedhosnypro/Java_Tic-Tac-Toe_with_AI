package tictactoe;

import java.util.Arrays;

public class GridState {
    private GridState() {
        throw new IllegalStateException("GridState class");
    }

    private static final String impossible = "Impossible";
    private static final String xWins = "X wins";
    private static final String oWins = "O wins";
    private static final String xTurn = "xStart";
    private static final String oTurn = "oStart";

    static String state(Grid grid) {
        String check = "";
        int xCnt = 0;
        int oCnt = 0;
        int empty = 0;
        for (char[] row : grid.getGrid()) {
            for (char ch : row) {
                if (ch == 'X') {
                    xCnt++;
                } else if (ch == 'O') {
                    oCnt++;
                } else if (ch == ' ') {
                    empty++;
                }
            }
        }

        if (Math.abs(xCnt - oCnt) > 1) {
            check = impossible;
        } else {
            switch (checkSides(grid)) {
                case impossible:
                    check = impossible;
                    break;
                case xWins:
                    check = xWins;
                    break;
                case oWins:
                    check = oWins;
                    break;
                default:
                    if (empty == 0) {
                        check = "Draw";
                    } else if (empty > 0) {
                        if (xCnt <= oCnt) {
                            check = xTurn;
                        } else {
                            check = oTurn;
                        }
                    }
                    break;
            }
        }
        return check;
    }

    //return simple array representing fulfilled side with special case (X, O)
    static char[] simplifySides(Grid grid) {
        char[][] side = grid.getSides();
        char[] simpleSides = new char[grid.getSides().length];
        Arrays.fill(simpleSides, 'f');
        for (int i = 0; i < side.length; i++) {
            char last = side[i][0];
            boolean isInRow = true;
            for (int j = 1; j < 3; j++) {
                if (side[i][j] == last) {
                    last = side[i][j];
                } else {
                    isInRow = false;
                    break;
                }
            }
            if (isInRow) {
                simpleSides[i] = side[i][0];
            }
        }
        return simpleSides;
    }

    static String checkSides(Grid grid) {
        String check = "";
        char[] sides;
        sides = simplifySides(grid);
        int xCnt = 0;
        int oCnt = 0;
        for (char side : sides) {
            if (side == 'X') {
                xCnt++;
            } else if (side == 'O') {
                oCnt++;
            }
        }
        if ((xCnt > 0 && oCnt > 0)) {
            check = impossible;
        } else if (xCnt == 1 && oCnt == 0) {
            check = xWins;
        } else if (oCnt == 1 && xCnt == 0) {
            check = oWins;
        }
        return check;
    }
}