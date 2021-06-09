package tictactoe;

import static tictactoe.GridState.state;
import static tictactoe.Move.*;
import static tictactoe.AIMove.*;
import static tictactoe.Print.printGrid;

public class Fight {
    static void startFight(Grid grid) {
        OuterFor:
        for (int i = 0; i < 9; i++) {
            switch (state(grid)) {
                case "X wins":
                    System.out.println("X wins");
                    break OuterFor;
                case "O wins":
                    System.out.println("O wins");
                    break OuterFor;
                case "Draw":
                    System.out.println("Draw");
                    break OuterFor;
                default:
                    char XO = ' ';
                    if (i % 2 == 0) {
                        while (playerMove(grid));
                        printGrid(grid);
                    } else {
                        while (startAIMove(grid));
                        printGrid(grid);
                    }

            }
        }
    }
}
