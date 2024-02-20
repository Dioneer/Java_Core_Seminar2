import Pegas.module.Module;
import Pegas.View.DrawGame;
import Pegas.simpleLogic.SimpleMethods;

public class Main {
    public static void main(String[] args) {
        DrawGame drawGame= new DrawGame();
        SimpleMethods simpleMethods = new SimpleMethods();
        Module module = new Module(drawGame, simpleMethods);
        drawGame.StartGame();
    }
}
