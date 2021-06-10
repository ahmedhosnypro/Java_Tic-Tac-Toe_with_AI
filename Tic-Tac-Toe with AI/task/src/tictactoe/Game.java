package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import static tictactoe.AIMove.*;
import static tictactoe.GridState.*;
import static tictactoe.Move.*;
import static tictactoe.Print.*;

public class Game {
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
        Player[] players = new Player[2];
        if (input.size() > 0) {
            switch (input.get(0)) {
                case "START":
                    if (input.size() == 3) {
                        try {
                            players[0] = Player.valueOf(input.get(1));
                            players[1] = Player.valueOf(input.get(2));
                            Grid grid = new Grid();
                            printGrid(grid);
                            playing(players, grid);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Bad parameters!");
                            chooseCommand();
                        }
                    } else {
                        System.out.println("Bad parameters!");
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
            System.out.println("Bad parameters!");
            chooseCommand();
        }
    }

    private static void playing(Player[] players, Grid grid) {
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
                        default:
                            break;
                    }
            }
            if (i == 8){
                System.out.println(state(grid));
            }
        }
    }

    private static void easy(Grid grid, int i) {
        char XO = ' ';
        if (i % 2 == 0)
            XO = 'X';
        else
            XO = 'O';
        startAIMove(grid, XO, "EASY");
        printGrid(grid);
    }

    private static void user(Grid grid, int i) {
        char XO = ' ';
        if (i % 2 == 0) {
            XO = 'X';
        } else {
            XO = 'O';
        }
        while (playerMove(grid, XO)) ;
        printGrid(grid);
    }

    private static void medium(Grid grid, int i) {
        char XO = ' ';
        if (i % 2 == 0) {
            XO = 'X';
        } else {
            XO = 'O';
        }
        startAIMove(grid, XO, "MEDIUM");
        printGrid(grid);
    }

    private static void hard(Grid grid, int i) {
        char XO = ' ';
        if (i % 2 == 0) {
            XO = 'X';
        } else {
            XO = 'O';
        }
        startAIMove(grid, XO, "HARD");
        printGrid(grid);
    }
}
