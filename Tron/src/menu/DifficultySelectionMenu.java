package menu;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class DifficultySelectionMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public DifficultySelectionMenu() {
		setTitle("Select Difficulty");
		setSize(400, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(4, 1, 10, 10));
		
		JLabel difficultyLabel = new JLabel("Choose AI Difficulty", SwingConstants.CENTER);
        difficultyLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(difficultyLabel);
        
        JButton btnEasy = new JButton("Easy");
        JButton btnMedium = new JButton("Medium");
        JButton btnHard = new JButton("Hard");

        btnEasy.addActionListener(e -> startGame("Easy"));
        btnMedium.addActionListener(e -> startGame("Medium"));
        btnHard.addActionListener(e -> startGame("Hard"));

        add(btnEasy);
        add(btnMedium);
        add(btnHard);

	}
	
	private void startGame(String difficulty) {
        dispose(); // close difficulty window
        new TronFrame(difficulty).setVisible(true);
    }

}
