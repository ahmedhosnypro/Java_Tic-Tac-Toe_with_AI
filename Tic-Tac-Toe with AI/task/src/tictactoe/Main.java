package tictactoe;

import java.util.Scanner;


import static tictactoe.Fight.*;
import static tictactoe.Game.chooseCommand;
import static tictactoe.GridState.*;
import static tictactoe.Move.*;
import static tictactoe.Print.*;

public class Main {
    public static void main(String[] args) {
//        Grid grid = new Grid();
//        printGrid(grid);
//        startFight(grid);
        while (chooseCommand());
    }
}

