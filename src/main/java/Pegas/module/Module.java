package Pegas.module;

import java.util.*;

public class Module implements Observer{
    private final View view;
    private final SimpleLogic simpleLogic;
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '*';
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static char[][] field;
    private static int fieldSizeX;
    private static int fieldSizeY;

    public Module(View view, SimpleLogic simpleLogic) {
        this.view = view;
        this.simpleLogic = simpleLogic;
        this.view.setObserver(this);
    }
    public void init(int x, int y) {
        do {
            initialize(x, y);
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
    /**
     *
     * @param x горизонталь
     * @param y вертикаль
     */
    public void initialize(int x, int y){
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
    public void drawField(){
        simpleLogic.printField(fieldSizeX, fieldSizeY, field);
    }

    /**
     * human step
     */
    public void humanTurn(){
        int x; int y;
        do{
            System.out.println("Enter X and Y step coordinates\n(from 1 to 3) with a space: ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        }while (!checkCellValid(x,y) || !checkCellEmpty(x,y));
        field[x][y] = DOT_HUMAN;
        drawField();
    }

    /**
     * aistep
     */
    public void aiTurn(){
        int x; int y;
        do{
            System.out.println("Now ai turn: ");
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        }
        while (!checkCellEmpty(x,y));
        field[x][y] = DOT_AI;
        drawField();
    }

    /**
     * check empty cell
     */
    public boolean checkCellEmpty(int x, int y){
        return simpleLogic.isCellEmpty(x, y, field, DOT_EMPTY);
    }

    /**
     * check valid cell
     */
    public boolean checkCellValid(int x, int y){
        return simpleLogic.isCellValid(x, y, fieldSizeX, fieldSizeY);
    }

    /**
     * function for check winner
     * @param dot for check
     * @return true if we have the winner or false if not
     */
    public boolean checkWinforThree(char dot){
        return simpleLogic.checkWinforThree(dot, field);
    }
    public boolean check1(int x, int y, char dot, int count){
        return false;
    }
    /**
     * friendship is win!
     * @return true if all cells are fill
     */
    public boolean checkDraw(){
        return simpleLogic.checkDraw(fieldSizeX, fieldSizeY, field, DOT_EMPTY);
    }

    /**
     * check status win or not
     * @param dot dot of player
     * @param s just string for congratulation
     * @return result
     */
    public boolean checkState(char dot, String s){
        if(checkWinforThree(dot)){
            System.out.println(s);
            return true;
        } else if (checkDraw()) {
            System.out.println("friendship is win!");
        }
        return false;
    }
}
