package Players;

import Game.*; // importing Game package so Direction enum can be used

public abstract class Player {

	protected int x, y; // coordinates of the grid

	protected Direction direction; // imported enum

	protected boolean isAlive; // is player alive

	protected String name;

	public Player(String name, int startX, int startY, Direction startDir) {
		this.name = name;
		this.x = startX;
		this.y = startY;
		this.direction = startDir;
		this.isAlive = true;
	}

	// movement logic
	public void move() {
		switch (direction) {
			case UP -> y--;
			case DOWN -> y++;
			case LEFT -> x--;
			case RIGHT -> x++;
		}
	}
	
	// Each subclass must define how it chooses its next direction
    public abstract void decideDirection(GameState state);
	
	// method to check if player is alive
	public boolean isAlive() {
		return isAlive;
	}

	// method to be used when a player should die (game over)
	public void kill() {
		this.isAlive = false;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void setDirection(Direction newDir) {
		this.direction = newDir;
	}
	
}
