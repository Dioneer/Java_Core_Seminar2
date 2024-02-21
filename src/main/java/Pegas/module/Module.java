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
            x=scanner.nextInt()-1;
            y=scanner.nextInt()-1;
        }while (!checkCellValid(x,y) || !checkCellEmpty(x, y));
        field[x][y] = DOT_HUMAN;
        drawField();
    }

    /**
     * aistep
     */
    public void aiTurn(){
        int x ; int y;
        do{
            System.out.println("Now ai turn: ");
            List<Integer> coord1 = new ArrayList<>();
            for (int i = 0; i < fieldSizeX; i++) {
                for (int j = 0; j < fieldSizeY; j++) {
                    if(!checkStep1(i, j).isEmpty()) {
                        coord1 = checkStep1(i, j);
                        break;
                    }
                    if(!checkStep2(i, j).isEmpty()) {
                        coord1 = checkStep2(i, j);
                        break;
                    }
                    if(!checkStep3(i, j).isEmpty()) {
                        coord1= checkStep3(i, j);
                        break;
                    }
                    if(!checkStep4(i, j).isEmpty()) {
                        coord1= checkStep4(i, j);
                        break;
                    }
                }
            }
            if(!coord1.isEmpty()){
                System.out.println(coord1);
                x= coord1.get(0);
                y= coord1.get(1);
                coord1.clear();
                if(!checkCellEmpty(x, y)){
                    if(x<fieldSizeX && y < fieldSizeY){
                        ++x;
                        ++y;
                    }
                }
            }else{
                x = random.nextInt(fieldSizeX);
                y = random.nextInt(fieldSizeY);
            }
            System.out.println(x+" "+y);
        }while (!checkCellEmpty(x, y));
        field[x][y] = DOT_AI;
        drawField();
    }

    /**
     * block noodles code with blood from eyes
     * @param i - vertical
     * @param j - horiz
     * @return
     */
    public List<Integer> checkStep1(int i, int j){
        int count = 2;
        List<Integer> arr= new ArrayList<>();
                if(field[i][j] == DOT_HUMAN){
                    int z = 0;
                    while (true){
                        if(j+z> fieldSizeX-1 || i+z > fieldSizeY-1 || field[j+z][i+z] != DOT_HUMAN) {
                            break;
                        }else{
                            z++;
                            if(z>=count){
                                arr.add(j+z);
                                arr.add(i+z);
                            }
                        }
                    }
                }
        return arr;
    }
    public List<Integer> checkStep2(int i, int j){
        int count = 2;
        List<Integer> arr= new ArrayList<>();
                if(field[i][j] == DOT_HUMAN){
                    int z = 0;
                    while (true){
                        if(i+z> fieldSizeX-1 || field[j][i+z] != DOT_HUMAN) {
                            break;
                        }else{
                            z++;
                            if(z>=count){
                                arr.add(j);
                                arr.add(i+z);
                            }
                        }
                    }
                }
        return arr;
    }
    public List<Integer> checkStep3(int i, int j){
        int count = 2;
        List<Integer> arr= new ArrayList<>();
        if(field[i][j] == DOT_HUMAN){
            int z = 0;
            while (true){
                if(j+z> fieldSizeX-1 || field[j+z][i] != DOT_HUMAN) {
                    break;
                }else{
                    z++;
                    if(z>=count){
                        arr.add(j+z);
                        arr.add(i);
                    }
                }
            }
        }
        return arr;
    }
    public List<Integer> checkStep4(int i, int j){
        int count = 2;
        List<Integer> arr= new ArrayList<>();
        if(field[i][j] == DOT_HUMAN){
            int z = 0;
            while (true){
                if(j+z> fieldSizeX-1 || i-z < 0 || field[j+z][i-z] != DOT_HUMAN) {
                    break;
                }else{
                    z++;
                    if(z>=count){
                        arr.add(j+z);
                        arr.add(i-z);
                        System.out.println(arr);
                    }
                }
            }
        }
        return arr;
    }

    /**
     * check empty cell
     */
    public boolean checkCellEmpty(int x, int y){
        return simpleLogic.isCellEmpty(x,y, field, DOT_EMPTY);
    }

    /**
     * check valid cell
     */
    public boolean checkCellValid(int x, int y) {
        return simpleLogic.isCellValid(x,y, fieldSizeX, fieldSizeY);
    }

    /**
     * function for check winner
     * @param dot for check
     * @return true if we have the winner or false if not
     */
    public boolean checkWinforThree(char dot){
        return simpleLogic.checkWinforThree(dot, field);
    }
    /**
     * function check winner for all fields
     * @param dot for check
     * @return true if we have the winner or false if not
     */
    public boolean checkWin(char dot){
        int count = fieldSizeX-1;
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if(simpleLogic.checkRight(x, y, dot, count, field))return true;
                if(simpleLogic.checkDown(x, y, dot, count, field))return true;
                if(simpleLogic.checkDiagUp(x, y, dot, count, field))return true;
                if(simpleLogic.checkDiagDown(x, y, dot, count, field))return true;
            }
        }
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
        if(fieldSizeX==3) {
            if (checkWinforThree(dot)) {
                System.out.println(s);
                return true;
            } else if (checkDraw()) {
                System.out.println("friendship is win!");
            }
        }else{
            if (checkWin(dot)) {
                System.out.println(s);
                return true;
            } else if (checkDraw()) {
                System.out.println("friendship is win!");
            }
        }
        return false;
    }
}
