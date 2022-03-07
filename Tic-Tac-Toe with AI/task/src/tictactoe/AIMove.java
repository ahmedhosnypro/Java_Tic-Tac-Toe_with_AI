package tictactoe;

import java.util.Random;

import static tictactoe.Move.chekMove;
import static tictactoe.Move.makeMove;


public class AIMove {
    private AIMove() {
        throw new IllegalStateException("AIMove class");
    }

    static void startAIMove(Grid grid, char currentTurnXO, String level) {
        System.out.print("Making move level \"" + level + "\"");
        switch (level) {
            case "EASY":
                while (playEasy(grid, currentTurnXO)) ;
                break;
            case "MEDIUM":
                while (playMedium(grid, currentTurnXO)) ;
                break;
            case "HARD":
                while (hardMove(grid, currentTurnXO)) ;
                break;
            default:
                break;
        }
    }

    static boolean playEasy(Grid grid, char currentTurnXO) {
        boolean isContinue = true;
        int[] cord = calcEasyMove();
        if (chekMove(cord, grid)) {
            makeMove(cord, grid, currentTurnXO);
            isContinue = false;
        }
        return isContinue;
    }

    static boolean playMedium(Grid grid, char currentTurnXO) {
        boolean isContinue = true;
        int[] cord = calcMediumMove(grid, currentTurnXO);
        if (chekMove(cord, grid)) {
            makeMove(cord, grid, currentTurnXO);
            isContinue = false;
        }
        return isContinue;
    }

    static boolean hardMove(Grid grid, char currentTurnXO) {
        boolean isContinue = true;
        int[] cord;
        cord = calcHardMove(grid, currentTurnXO);
        if (chekMove(cord, grid)) {
            makeMove(cord, grid, currentTurnXO);
            isContinue = false;
        }
        return isContinue;
    }

    private static int[] calcEasyMove() {
        Random random = new Random();
        return new int[]{random.nextInt(3), random.nextInt(3)};
    }

    static int[] calcMediumMove(Grid grid, char currentTurnXO) {
        //getting simplified version of game board cell as 3 rows, columns, main and side diagonal
        var sides = grid.getSides();

        //check against losing
        int[] cord = getSemiFullSideCoord(sides, currentTurnXO, 'X', 'O');

        if (cord[0] == -1) {  //if there's no chance for opposite player to win, it can choose any cell
            cord = calcEasyMove();
        }

        return cord;
    }

    static int[] calcHardMove(Grid grid, char currentTurnXO) {
        int[] cord;      //fantasy coords
        //getting simplified version of game board cell as 3 rows, columns, main and side diagonal
        var sides = grid.getSides();

        //check for winning
        cord = getSemiFullSideCoord(sides, currentTurnXO, 'O', 'X');

        //check against losing
        if (cord[0] == -1) {
            cord = getSemiFullSideCoord(sides, currentTurnXO, 'X', 'O');
        }

        //check diagonals for high priority cell
        if (cord[0] == -1) {
            cord = getSemiEmptySideCoord(sides, currentTurnXO);
        }

        if (cord[0] == -1) {
            cord = calcEasyMove();
        }

        return cord;
    }

    private static int[] getSemiFullSideCoord(char[][] sides, char currentTurnXO, char o, char x) {
        int sideIndex = 0;
        for (var side : sides) {
            int xCnt = 0;       //counting how many X for each side
            int oCnt = 0;       //counting how many O for each side
            var sideStatistics = getSemiFullSideStatistics(side);
            xCnt += sideStatistics[0];
            oCnt += sideStatistics[1];
            int yEmptyCell = sideStatistics[2];

            //check priority
            if ((xCnt == 0 && oCnt == 2 && currentTurnXO == o)
                    || (oCnt == 0 && xCnt == 2 && currentTurnXO == x)) {
                return getCordFromSideCoords(side, sideIndex, yEmptyCell);
            }
            sideIndex++;
        }
        return new int[]{-1, -1};
    }

    private static int[] getSemiEmptySideCoord(char[][] sides, char currentTurnXO) {
        var sideIndex = 0;
        for (var side : sides) {
            int xCnt = 0;       //counting how many X for each side
            int oCnt = 0;       //counting how many O for each side
            var sideStatistics = getSemiEmptySideStatistics(side);
            xCnt += sideStatistics[0];
            oCnt += sideStatistics[1];
            int yEmptyCell = sideStatistics[2];

            //check for winning
            if (((xCnt == 0 && oCnt == 1 && currentTurnXO == 'X')
                    || (oCnt == 0 && xCnt == 1 && currentTurnXO == 'O'))) {
                return getCordFromSideCoords(side, sideIndex, yEmptyCell);
            }
            sideIndex++;
        }
        return new int[]{-1, -1};
    }

    //getting board coordinate from simplified side coordinate
    private static int[] getCordFromSideCoords(char[] side, int sideIndex, int yEmptyCell) {
        int row = -1;
        int column = -1;
        int[] cord = {row, column};
        if (side[yEmptyCell] == ' ') {      //rechecking if the cell is empty
            switch (sideIndex) {        //getting the coordinate according to sides order in the list
                case 0:     //main diagonal
                    row = yEmptyCell;
                    column = yEmptyCell;
                    break;
                case 1:     //side diagonal
                    if (yEmptyCell == 0) {      //re-reverse for side diagonal
                        row = 0;
                        column = 2;
                    } else if (yEmptyCell == 1) {
                        row = 1;
                        column = 1;
                    } else {
                        row = 2;
                        column = 0;
                    }
                    break;
                case 2:     //fst Row
                case 3:     //snd Row
                case 4:     // 3rd Row
                    row = sideIndex - 2;
                    column = yEmptyCell;
                    break;
                case 5:     //fst Column
                case 6:     //snd Column
                case 7:     //3rd Column
                    row = yEmptyCell;
                    column = sideIndex - 5;
                    break;

                default:
                    break;
            }
            cord[0] = row;
            cord[1] = column;
            return cord;
        }

        return cord;
    }

    private static int[] getSemiFullSideStatistics(char[] side) {
        int yEmptyCell = 0;     //Y coordinate for empty cell in the row
        int xCnt = 0;       //counting how many X for each side
        int oCnt = 0;       //counting how many O for each side
        for (int elem = 0; elem < 3; elem++) {
            if (side[elem] == 'X') {
                xCnt++;
            } else if (side[elem] == 'O') {
                oCnt++;
            } else if (side[elem] == ' ') {
                yEmptyCell = elem;
            }
        }
        return new int[]{xCnt, oCnt, yEmptyCell};
    }

    private static int[] getSemiEmptySideStatistics(char[] side) {
        int yEmptyCell = 1;     //Y coordinate for empty cell in the row
        int xCnt = 0;       //counting how many X for each side
        int oCnt = 0;       //counting how many O for each side
        for (int elem = 0; elem < 3; elem++) {
            if (side[elem] == 'X') {
                xCnt++;
            } else if (side[elem] == 'O') {
                oCnt++;
            }
        }
        return new int[]{xCnt, oCnt, yEmptyCell};
    }
}