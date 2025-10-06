package menu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class MainMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setBackground(Color.WHITE);
		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel GameName = new JLabel("Tron");
		GameName.setFont(new Font("Tahoma", Font.PLAIN, 32));
		panel.add(GameName);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JPanel playLbl = new JPanel();
		panel_1.add(playLbl);
		
		JButton playBtn = new JButton("Play");
		playBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		playLbl.add(playBtn);
		
		JPanel quitLbl = new JPanel();
		panel_1.add(quitLbl);
		
		JButton btnNewButton = new JButton("Quit");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		quitLbl.add(btnNewButton);

	}

}
