package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import static tictactoe.AIMove.*;
import static tictactoe.GridState.*;
import static tictactoe.Move.*;
import static tictactoe.Print.*;

public class Game {
    private Game() {
        throw new IllegalStateException("Game class");
    }

    static final String badParameters = "Bad parameters!";
    private static final Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    static void chooseCommand() {
        System.out.print("Input command: > ");
        ArrayList<String> input = Arrays
                .stream(scanner.nextLine().trim().split("\\s+"))
                .map(String::toUpperCase)
                .collect(Collectors.toCollection(ArrayList::new));
        PlayerLevel[] players = new PlayerLevel[2];
        if (!input.isEmpty()) {
            switch (input.get(0)) {
                case "START":
                    if (input.size() == 3) {
                        try {
                            players[0] = PlayerLevel.valueOf(input.get(1));
                            players[1] = PlayerLevel.valueOf(input.get(2));
                            Grid grid = new Grid();
                            printGrid(grid);
                            playing(players, grid);
                        } catch (IllegalArgumentException e) {
                            System.out.println(badParameters);
                            chooseCommand();
                        }
                    } else {
                        System.out.println(badParameters);
                        chooseCommand();
                    }
                    break;
                case "EXIT":
                    break;
                default:
                    chooseCommand();
                    break;
            }
        } else {
            System.out.println(badParameters);
            chooseCommand();
        }
    }

    private static void playing(PlayerLevel[] players, Grid grid) {
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
                    int j = i % 2;
                    switch (players[j]) {
                        case EASY:
                            easy(grid, i);
                            break;
                        case USER:
                            user(grid, i);
                            break;
                        case MEDIUM:
                            medium(grid, i);
                            break;
                        case HARD:
                            hard(grid, i);
                            break;
                        default:
                            break;
                    }
            }
            if (i == 8) {
                System.out.println(state(grid));
            }
        }
    }

    private static void easy(Grid grid, int i) {
        char currentTurnXO;
        if (i % 2 == 0)
            currentTurnXO = 'X';
        else
            currentTurnXO = 'O';
        startAIMove(grid, currentTurnXO, "EASY");
        printGrid(grid);
    }

    private static void user(Grid grid, int i) {
        char currentTurnXO;
        if (i % 2 == 0) {
            currentTurnXO = 'X';
        } else {
            currentTurnXO = 'O';
        }
        while (playerMove(grid, currentTurnXO)) ;
        printGrid(grid);
    }

    private static void medium(Grid grid, int i) {
        char currentTurnXO;
        if (i % 2 == 0) {
            currentTurnXO = 'X';
        } else {
            currentTurnXO = 'O';
        }
        startAIMove(grid, currentTurnXO, "MEDIUM");
        printGrid(grid);
    }

    private static void hard(Grid grid, int i) {
        char currentTurnXO;
        if (i % 2 == 0) {
            currentTurnXO = 'X';
        } else {
            currentTurnXO = 'O';
        }
        startAIMove(grid, currentTurnXO, "HARD");
        printGrid(grid);
    }
}