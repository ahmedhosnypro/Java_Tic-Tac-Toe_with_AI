package tictactoe;

import java.util.Scanner;

import static tictactoe.GridState.state;
import static tictactoe.GridState.stateF;
import static tictactoe.Print.printGrid;
import static tictactoe.Move.move;

public class Fight {
    static void startFight(Grid grid) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the cells: ");
        String cells = scanner.nextLine();
        if (grid.setGrid(cells)){
            OuterFor: for (int i = 0; i < 9; i++) {
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
                    case "Xstart":
                        while (move(grid, 'X'));
                        printGrid(grid);
                        System.out.println(stateF(grid));
                        break OuterFor;
                    case "Ostart":
                        while (move(grid, 'O'));
                        printGrid(grid);
                        System.out.println(stateF(grid));
                        break OuterFor;
                    default:
                        char XO = ' ';
                        if (i % 2 == 0) {
                            XO = 'X';
                        } else {
                            XO = 'O';
                        }
                        while (move(grid, XO));
                        printGrid(grid);
                }
            }
        }
        else
            System.out.println("invalid Cells");

//        System.out.println(state(grid));
    }
}
