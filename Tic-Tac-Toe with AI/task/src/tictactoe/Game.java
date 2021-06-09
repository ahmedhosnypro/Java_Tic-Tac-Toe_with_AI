package tictactoe;

import java.util.Locale;
import java.util.Scanner;

import static tictactoe.AIMove.*;
import static tictactoe.GridState.*;
import static tictactoe.Move.*;
import static tictactoe.Print.*;

public class Game {
    private static final Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    static boolean chooseCommand() {
        System.out.print("Input command: > ");
        String input = scanner.nextLine().trim().toUpperCase();
        input = input.replaceAll(" ", "");

        boolean isBadCommand = true;
        Command command = Command.NOTCOMMAND;
        try {
            command = Command.valueOf(input);
            isBadCommand = false;
        } catch (IllegalArgumentException e) {
            System.out.println("Bad parameters!");
        }
        if (!command.equals(Command.NOTCOMMAND)) {
            Grid grid = new Grid();
            printGrid(grid);
            OuterFor:
            for (int i = 0; i < 9; i++) {
                switch (command) {
                    case STARTEASYEASY:
                        if (startEasyEasy(grid, i))
                            break OuterFor;
                        break;
                    case STARTEASYUSER:
                        if (startEasyUser(grid, i))
                            break OuterFor;
                        break;
                    case STARTUSEREASY:
                        if (startUserEasy(grid, i))
                            break OuterFor;
                        break;
                    case STARTUSERUSER:
                        if (startUserUser(grid, i))
                            break OuterFor;
                        break;
                    case EXIT:
                        break OuterFor;
                    default:
                        break;
                }
            }
        }
        return isBadCommand;
    }

    private static boolean startEasyEasy(Grid grid, int i) {
        switch (state(grid)) {
            case "X wins":
                System.out.println("X wins");
                return true;
            case "O wins":
                System.out.println("O wins");
                return true;
            case "Draw":
                System.out.println("Draw");
                return true;
            default:
                char XO = ' ';
                if (i % 2 == 0)
                    XO = 'X';
                else
                    XO = 'O';
                while (startAIMove(grid, XO));
                printGrid(grid);
                return false;
        }
    }

    private static boolean startEasyUser(Grid grid, int i) {
        switch (state(grid)) {
            case "X wins":
                System.out.println("X wins");
                return true;
            case "O wins":
                System.out.println("O wins");
                return true;
            case "Draw":
                System.out.println("Draw");
                return true;
            default:
                char XO = ' ';
                if (i % 2 == 0) {
                    XO = 'X';
                    while (startAIMove(grid, XO));
                } else {
                    XO = 'O';
                    while (playerMove(grid, XO));
                }
                printGrid(grid);
                return false;
        }


    }

    private static boolean startUserEasy(Grid grid, int i) {
        switch (state(grid)) {
            case "X wins":
                System.out.println("X wins");
                return true;
            case "O wins":
                System.out.println("O wins");
                return true;
            case "Draw":
                System.out.println("Draw");
                return true;
            default:
                char XO = ' ';
                if (i % 2 == 0) {
                    XO = 'X';
                    while (playerMove(grid, XO));
                } else {
                    XO = 'O';
                    while (startAIMove(grid, XO));
                }
                printGrid(grid);
                return false;
        }
    }

    private static boolean startUserUser(Grid grid, int i) {
        switch (state(grid)) {
            case "X wins":
                System.out.println("X wins");
                return true;
            case "O wins":
                System.out.println("O wins");
                return true;
            case "Draw":
                System.out.println("Draw");
                return true;
            default:
                char XO = ' ';
                if (i % 2 == 0)
                    XO = 'X';
                else
                    XO = 'O';

                while (playerMove(grid, XO));
                printGrid(grid);
                return false;
        }
    }
}
