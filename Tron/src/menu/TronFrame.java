package menu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TronFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public TronFrame(String difficulty) {
        setTitle("Tron - " + difficulty + " Mode");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel tron = new GamePanel(difficulty);
        add(tron);

        // You could start the game loop here
        tron.startGameLoop();
    }

}
