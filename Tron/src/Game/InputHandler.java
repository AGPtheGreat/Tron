package Game;
import java.awt.event.KeyEvent;

public class InputHandler {
    private int lastKeyPressed = -1;

    public void keyPressed(KeyEvent e) {
        lastKeyPressed = e.getKeyCode();
    }

    public int getLastKeyPressed() {
        return lastKeyPressed;
    }
}
