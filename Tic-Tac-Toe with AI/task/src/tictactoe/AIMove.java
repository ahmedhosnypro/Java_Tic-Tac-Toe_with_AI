package tictactoe;

import java.util.Random;
import java.util.Scanner;

import static tictactoe.Move.chekMove;
import static tictactoe.Move.makeMove;


public class AIMove {
    static boolean startAIMove(Grid grid) {
        boolean isContinue = true;
        String level = "EASY";
        int[] cords = new int[2];
        switch (level){
            case "EASY":
                cords = easyMove(grid);
                break;
            default:
                break;
        }
        if (chekMove(cords, grid)) {
            makeMove(cords, grid, 'O');
            isContinue = false;
        }

        return isContinue;
    }

    private static int[] easyMove(Grid grid){
        System.out.print("Making move level \"easy\"");
        int[] cords = easyMoveCords(grid);
        return cords;
    }

    static int[] easyMoveCords(Grid grid) {
        Random random = new Random();
        return new int[]{random.nextInt(3), random.nextInt(3)};
    }
}
