package tictactoe;

public class Print {
    static void printGrid(Grid grid){
        System.out.println("---------");
        for (char[] row: grid.getGrid()){
            System.out.print("| ");
            for (char ch: row){
                System.out.print(ch + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
}
