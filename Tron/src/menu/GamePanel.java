package menu;

import Players.*;
import javax.swing.*;
import javax.swing.Timer;

import Game.Direction;
import Game.GameState;
import Game.InputHandler;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GamePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Timer timer;
	private GameState gameState;

	// Grid properties
	private final int CELL_SIZE = 10;
	private final int GRID_WIDTH = 60;
	private final int GRID_HEIGHT = 40;

	// Visible panel area
	private final int BORDER_SIZE = 50; // padding around grid

	private HumanPlayer human;
	private AIPlayer ai;
	private InputHandler input;

	private boolean gameOver = false;
	private String difficulty;

	public GamePanel(String difficulty) {
		this.difficulty = difficulty;
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.setPreferredSize(
				new Dimension(GRID_WIDTH * CELL_SIZE + BORDER_SIZE * 2, GRID_HEIGHT * CELL_SIZE + BORDER_SIZE * 2));

		// Set up game state
		gameState = new GameState(GRID_WIDTH, GRID_HEIGHT);

		// Input handler
		input = new InputHandler();

		// Set up key bindings for player
		Map<Integer, Direction> keyBindings = new HashMap<>();
		keyBindings.put(KeyEvent.VK_UP, Direction.UP);
		keyBindings.put(KeyEvent.VK_DOWN, Direction.DOWN);
		keyBindings.put(KeyEvent.VK_LEFT, Direction.LEFT);
		keyBindings.put(KeyEvent.VK_RIGHT, Direction.RIGHT);

		// Create players
		human = new HumanPlayer("Human", 10, GRID_HEIGHT / 2, Direction.RIGHT, keyBindings, input);
		AIDifficulty aiDiff = switch (difficulty.toUpperCase()) {
		case "EASY" -> AIDifficulty.EASY;
		case "MEDIUM" -> AIDifficulty.MEDIUM;
		case "HARD" -> AIDifficulty.HARD;
		default -> AIDifficulty.EASY;
		};
		ai = new AIPlayer("AI", GRID_WIDTH - 10, GRID_HEIGHT / 2, Direction.LEFT, aiDiff);

		addKeyListener(input);
	}

	public void startGameLoop() {
		timer = new Timer(100, this);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (gameOver)
			return;

		// Each player decides next move
		human.decideDirection(gameState);
		ai.decideDirection(gameState);

		// Move each player
		movePlayer(human);
		movePlayer(ai);

		repaint();
	}

	private void movePlayer(Player player) {
		if (!player.isAlive())
			return;

		player.move();

		int x = player.getX();
		int y = player.getY();

		// Check for wall collision
		if (x < 0 || x >= GRID_WIDTH || y < 0 || y >= GRID_HEIGHT) {
			player.kill();
			checkGameOver();
			return;
		}

		// Check for cell collision
		if (gameState.isCellOccupied(x, y)) {
			player.kill();
			checkGameOver();
			return;
		}

		// Occupy new cell
		gameState.occupyCell(x, y);
	}

	private void checkGameOver() {
		if (!human.isAlive() || !ai.isAlive()) {
			gameOver = true;
			timer.stop();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int offsetX = BORDER_SIZE;
		int offsetY = BORDER_SIZE;

		// Draw outer border
		g.setColor(Color.GRAY);
		g.drawRect(offsetX - 1, offsetY - 1, GRID_WIDTH * CELL_SIZE + 1, GRID_HEIGHT * CELL_SIZE + 1);

		// Draw grid background
		g.setColor(Color.DARK_GRAY);
		for (int i = 0; i <= GRID_WIDTH; i++) {
			g.drawLine(offsetX + i * CELL_SIZE, offsetY, offsetX + i * CELL_SIZE, offsetY + GRID_HEIGHT * CELL_SIZE);
		}
		for (int j = 0; j <= GRID_HEIGHT; j++) {
			g.drawLine(offsetX, offsetY + j * CELL_SIZE, offsetX + GRID_WIDTH * CELL_SIZE, offsetY + j * CELL_SIZE);
		}

		// Draw occupied cells (trails)
		g.setColor(Color.CYAN);
		for (Point p : gameState.getOccupiedCells()) {
			g.fillRect(offsetX + p.x * CELL_SIZE, offsetY + p.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
		}

		// Draw players
		drawPlayer(g, human, Color.WHITE, offsetX, offsetY);
		drawPlayer(g, ai, Color.ORANGE, offsetX, offsetY);

		// Game over text
		if (gameOver) {
			g.setColor(Color.RED);
			g.setFont(new Font("Tahoma", Font.BOLD, 36));
			g.drawString("GAME OVER", offsetX + GRID_WIDTH * CELL_SIZE / 2 - 100,
					offsetY + GRID_HEIGHT * CELL_SIZE / 2);
		}
	}

	private void drawPlayer(Graphics g, Player p, Color color, int offsetX, int offsetY) {
		if (p.isAlive()) {
			g.setColor(color);
			g.fillRect(offsetX + p.getX() * CELL_SIZE, offsetY + p.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
		}
	}
}
