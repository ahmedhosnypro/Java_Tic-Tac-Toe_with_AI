package tictactoe;

import java.util.Scanner;

import static tictactoe.Fight.startFight;
import static tictactoe.GridState.state;
import static tictactoe.Move.move;
import static tictactoe.Print.printGrid;

public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid();
        printGrid(grid);
        startFight(grid);
    }
}

