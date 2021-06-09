package tictactoe;

import java.util.Scanner;

public class Grid {
    private char[][] grid = new char[3][3];
    char[] diagonal1 = new char[3];
    char[] diagonal2 = new char[3];
    private char[][] rotatedGrid = new char[3][3];
    private char[][] sides;


    Grid() {
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                grid[i][j] = ' ';
            }
        }
        setRotatedGrid();
        setDiagonal1();
        setDiagonal2();
        setSides();
    }
    boolean setGrid(String symbols) {
        if (!symbols.matches("[^XO_]")){
            if (symbols.length() == 9) {
                symbols = symbols.replaceAll("_", " ");
                int j = 0;
                for (int i = 0; i < 3; i++) {
                    grid[0][i] = symbols.charAt(j);
                    j++;
                }
                for (int i = 0; i < 3; i++) {
                    grid[1][i] = symbols.charAt(j);
                    j++;
                }
                for (int i = 0; i < 3; i++) {
                    grid[2][i] = symbols.charAt(j);
                    j++;
                }
                setRotatedGrid();
                setDiagonal1();
                setDiagonal2();
                setSides();
                return true;
            }
            else return false;
        }
        else return false;

    }
    public void setGrid(char[][] grid) {
        this.grid = grid;
        setRotatedGrid();
        setDiagonal1();
        setDiagonal2();
        setSides();
    }
    public void setGrid(int[] coordinates, char XO) {
        grid[coordinates[0]][coordinates[1]] = XO;
        setRotatedGrid();
        setDiagonal1();
        setDiagonal2();
        setSides();
    }
    public char[][] getGrid() {
        return grid;
    }
    void setRotatedGrid() {
        for (int i = 0; i < 3; i++){
            for (int k = 0; k < 3; k++){
                rotatedGrid[i][k] = grid[k][i];
            }
        }
    }
    void setDiagonal1() {
        for (int i = 0; i < 3; i++){
            diagonal1[i] = grid[i][i];
        }
    }
    void setDiagonal2() {
        diagonal2[0] = grid[0][2];
        diagonal2[1] = grid[1][1];
        diagonal2[2] = grid[2][0];
    }
    public void setSides() {
        sides = new char[][]{grid[0], grid[1], grid[2], rotatedGrid[0], rotatedGrid[1], rotatedGrid[2],
                diagonal1, diagonal2};
    }
    public char[][] getSides() {
        return sides;
    }
}
