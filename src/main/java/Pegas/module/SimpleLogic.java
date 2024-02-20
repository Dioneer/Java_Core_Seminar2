package Pegas.module;

public interface SimpleLogic {
    boolean isCellValid(int x, int y, int sizeX, int sizeY);
    boolean isCellEmpty(int x, int y, char[][] field, char dot);
    void printField(int sizeX, int sizeY, char[][] field);
    boolean checkDraw(int sizeX, int sizeY, char[][] field, char dot);
    boolean checkWinforThree(char dot,char[][] field);
}
