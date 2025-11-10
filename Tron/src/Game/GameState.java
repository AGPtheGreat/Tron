package Game;

import java.util.List;

import Players.Player;

public class GameState {
	private boolean[][] trailGrid;
	private List<Player> players;
	private int width, height;

	public GameState(int width, int height, List<Player> players) {
		this.width = width;
		this.height = height;
		this.trailGrid = new boolean[width][height];
		this.players = players;
	}

	public void update() {
		for (Player p : players) {
			if (!p.isAlive())
				continue;

			p.decideDirection(this);
			p.move();

			if (checkCollision(p)) {
				p.kill();
			} else {
				trailGrid[p.getX()][p.getY()] = true;
			}
		}
	}

	private boolean checkCollision(Player p) {
		int x = p.getX();
		int y = p.getY();

		return (x < 0 || x >= width || y < 0 || y >= height || trailGrid[x][y]);
	}

	public boolean isCellOccupied(int x, int y) {
		return trailGrid[x][y];
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
