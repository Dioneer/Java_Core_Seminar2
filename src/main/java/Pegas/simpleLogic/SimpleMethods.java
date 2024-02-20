package Pegas.simpleLogic;

import Pegas.module.SimpleLogic;

public class SimpleMethods implements SimpleLogic {
    /**
     * check valid cell
     * @param x horizontal
     * @param y vertical
     * @return true if valid
     */
    public boolean isCellValid(int x, int y, int sizeX, int sizeY){
        boolean rez = x>=0&& x <sizeX && y>=0 && y <sizeY;
        if(!rez){
            System.out.println("Your coordinates are not valid");
        }
        return  rez;
    }

    /**
     * check empty cell
     * @param x horizontal
     * @param y vertical
     * @param field field for check
     * @param dot what we check
     * @return result
     */
    @Override
    public boolean isCellEmpty(int x, int y, char[][] field, char dot) {
        boolean rez = field[x][y] == dot;
        if(!rez){
            System.out.println("This cell is not empty");
        }
        return  rez;
    }

    /**
     * simple print field
     * @param sizeX horizontal size
     * @param sizeY vertical size
     * @param field arr for print
     */
    @Override
    public void printField(int sizeX, int sizeY, char[][] field){
        System.out.print("+");
        for (int i = 0; i < sizeX; i++) {
            System.out.print("-"+(i+1));
        }
        System.out.println("-");
        for (int k = 0; k < sizeX; k++) {
            System.out.print(k+1 +"|");
            for (int j = 0; j < sizeY; j++) {
                System.out.print(field[k][j]+"|");
            }
            System.out.println();
        }
    }
    /**
     * friendship is win!
     * @return true if all cells are fill
     */
    public boolean checkDraw(int sizeX, int sizeY, char[][] field, char dot){
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                if(isCellEmpty(x, y, field, dot)) return false;
            }
        }
        return true;
    }
    /**
     * function for check winner
     * @param dot for check
     * @return true if we have the winner or false if not
     */
    public boolean checkWinforThree(char dot,char[][] field){
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
}
