package Pegas.View;

import Pegas.module.Observer;
import Pegas.module.View;

import java.util.Scanner;

public class DrawGame implements View {
    private Observer observer;
    private Scanner scanner = new Scanner(System.in);
    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public void StartGame(){
        System.out.println("Please enter size of field X and Y with space: ");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        observer.init(x,y);
    }

}
