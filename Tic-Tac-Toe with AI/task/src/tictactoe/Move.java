package tictactoe;

import java.util.Scanner;

public class Move {
    static boolean playerMove(Grid grid){
        boolean isContinue = true;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the coordinates: ");
        String[] coordinates = scanner.nextLine().trim().split(" ");
        String check = "";
        check = checkCoordinates(parseCoordinates(coordinates));
        switch (check){
            case "notNumber":
            case "oneParam":
                System.out.println("You should enter numbers!");
                break;
            case "outOfRange":
                System.out.println("Coordinates should be from 1 to 3!");
                break;
            case "available":
                if (chekMove(parseCoordinates(coordinates), grid)){
                    makeMove(parseCoordinates(coordinates), grid, 'X');
                    isContinue = false;
                }
                else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
                break;
            default:
                break;
        }
        return isContinue;
    }
    static int[] parseCoordinates(String[] coordinates){
        int[] coordinateToInt = new int[]{-1, -1};
        if (coordinates.length == 2){
            try {
                coordinateToInt[0] = Integer.parseInt(coordinates[0]) - 1;
                coordinateToInt[1] = Integer.parseInt(coordinates[1]) - 1;
            }
            catch (IllegalArgumentException ignored){
            }
        }
        else {
            coordinateToInt[0] = -2;
            coordinateToInt[1] = -2;
        }
        return coordinateToInt;
    }
    static String checkCoordinates(int[] coordinateToInt){
        String check = "";
        for (int j : coordinateToInt) {
            if (j == -1) {
                check = "notNumber";
                break;
            }
            else if (j == -2){
                check = "oneParam";
            }
            else if (j >= 0 && j < 3) {
                check = "available";
            } else {
                check = "outOfRange";
                break;
            }
        }
        return check;
    }
    static boolean chekMove(int[] coordinateToInt, Grid grid){
        return grid.getGrid()[coordinateToInt[0]][coordinateToInt[1]] == ' ';
    }
    static void makeMove(int[] coordinateToInt ,Grid grid, char XO){
        grid.setGrid(coordinateToInt, XO);
    }


}

