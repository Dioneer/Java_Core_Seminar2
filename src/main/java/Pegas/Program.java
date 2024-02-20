package Pegas;

import java.util.*;

public class Program {
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '*';
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static char[][] field;
    private static int fieldSizeX;
    private static int fieldSizeY;

    /**
     *
     * @param x горизонталь
     * @param y вертикаль
     */
    static void initialize(int x, int y){
        fieldSizeX = x>0?x:3;
        fieldSizeY = y>0?y:3;
        field = new char[fieldSizeX][fieldSizeY];

        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                field[i][j] = DOT_EMPTY;
            }
        }
    }
    /**
     * Print field
     */
    static void printField(){
        System.out.print("+");
        for (int i = 0; i < fieldSizeX; i++) {
            System.out.print("-"+(i+1));
        }
        System.out.println("-");
        for (int k = 0; k < fieldSizeX; k++) {
            System.out.print(k+1 +"|");
            for (int j = 0; j < fieldSizeY; j++) {
                System.out.print(field[k][j]+"|");
            }
            System.out.println();
        }
    }
    static void humanTurn(){
        int x; int y;
        do{
            System.out.println("Enter X and Y step coordinates\n(from 1 to 3) with a space: ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        }while (!isCellValid(x,y) || !isCellEmpty(x,y));
        field[x][y] = DOT_HUMAN;
        printField();
    }
    static void aiTurn(){
        int x; int y;
        do{
            System.out.println("Now ai turn: ");
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        }
        while (!isCellEmpty(x,y));
        field[x][y] = DOT_AI;
        printField();
    }
    static boolean isCellEmpty(int x, int y){
        boolean rez = field[x][y] == DOT_EMPTY;
        if(!rez){
            System.out.println("This cell is not empty");
        }
        return  rez;
    }

    /**
     * check valid cell
     * @param x horizontal
     * @param y vertical
     * @return true if valid
     */
    static boolean isCellValid(int x, int y){
        boolean rez = x>=0&& x <fieldSizeX && y>=0 && y <fieldSizeY;
        if(!rez){
            System.out.println("Your coordinates are not valid");
        }
        return  rez;
    }

    /**
     * function for check winner
     * @param dot
     * @return
     */
    static boolean checkWin(char dot){
        if(field[0][0] == dot &&field[0][1] == dot&&field[0][2] == dot) return true;
        if(field[1][0] == dot &&field[1][1] == dot&&field[1][2] == dot) return true;
        if(field[2][0] == dot &&field[2][1] == dot&&field[2][2] == dot) return true;

        if(field[0][0] == dot &&field[1][0] == dot&&field[2][0] == dot) return true;
        if(field[0][1] == dot &&field[1][1] == dot&&field[2][1] == dot) return true;
        if(field[0][2] == dot &&field[1][2] == dot&&field[2][2] == dot) return true;

        if(field[0][0] == dot &&field[1][1] == dot&&field[2][2] == dot) return true;
        if(field[0][2] == dot &&field[1][1] == dot&&field[2][0] == dot) return true;
        return false;
    }

    /**
     * friendship is win!
     * @return true if all cells are fill
     */
    static boolean checkDraw(){
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if(isCellEmpty(x, y)) return false;
            }
        }
        return true;
    }

    /**
     *
     * @param dot dot of player
     * @param s
     * @return
     */
    static boolean checkState(char dot, String s){
        if(checkWin(dot)){
            System.out.println(s);
            return true;
        } else if (checkDraw()) {
            System.out.println("friendship is win!");
        }
        return false;
    }
    public static void main(String[] args) {
        do {
            initialize(3, 3);
            while (true) {
                humanTurn();
                if (checkState(DOT_HUMAN, "You win!"))
                    break;
                aiTurn();
                if (checkState(DOT_AI, "You win!"))
                    break;
            }
            System.out.println("Do you want to continue(Y/N)? ");
        } while (!scanner.next().equalsIgnoreCase("N"));
    }
}
