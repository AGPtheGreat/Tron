package Players;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Game.Direction;
import Game.GameState;

public class AIPlayer extends Player {
	private AIDifficulty difficulty;

	private Random random = new Random();

	public AIPlayer(String name, int startX, int startY, Direction startDir, AIDifficulty difficulty) {
		super(name, startX, startY, startDir);
		this.difficulty = difficulty;
	}

	@Override
	public void decideDirection(GameState state) {
		// TODO Auto-generated method stub
		switch (difficulty) {
		case EASY -> easyAI(state);
		case MEDIUM -> mediumAI(state);
		case HARD -> hardAI(state);
		}

	}

	private void easyAI(GameState state) {
		// AI turns randomly, 20% chance for a turn
		if (random.nextDouble() < 0.2) {
			direction = randomDirection();
		}
	}

	private void mediumAI(GameState state) {
		// AI tries to avoid walls/trails in front of it
		if (wouldCollide(direction, state)) {
			List<Direction> safeMoves = getSafeDirections(state);
			if (!safeMoves.isEmpty()) {
				direction = safeMoves.get(random.nextInt(safeMoves.size()));
			}
		}
	}

	private void hardAI(GameState state) {
		// AI looks to pick the direction with the longest
		// open path ahead of it
		
		List<Direction> safeMoves = getSafeDirections(state);
		if (safeMoves.isEmpty()) {
			return;
		}
		
		Direction bestDirection = direction;
		int bestDistance = -1;
		
		for (Direction dir : safeMoves) {
			int dist = countSafeCells(dir, state);
			if(dist > bestDistance) {
				bestDistance = dist;
				bestDirection = dir;
			}
		}
		direction = bestDirection;
		
	}

	// Helping Methods for AI difficulty levels

	private Direction randomDirection() {
		Direction[] directions = Direction.values();
		return directions[random.nextInt(directions.length)];
	}

	private boolean wouldCollide(Direction dir, GameState state) {
		int nextX = x;
		int nextY = y;

		switch (dir) {
		case UP -> nextY--;
		case DOWN -> nextY++;
		case LEFT -> nextX--;
		case RIGHT -> nextX++;
		}

		return nextX < 0 || nextX >= state.getWidth() || nextY < 0 || nextY >= state.getHeight()
				|| state.isCellOccupied(nextX, nextY);
	}

	private List<Direction> getSafeDirections(GameState state){
		List<Direction> safe = new ArrayList<>();
		for(Direction dir : Direction.values()){
			if(!wouldCollide(dir, state)) {
				safe.add(dir);
			}
		}
		return safe;
	}

	private int countSafeCells(Direction dir, GameState state) {
		int nextX = x;
		int nextY = y;
		int distance = 0;

		while (true) {
			switch (dir) {
			case UP -> nextY--;
			case DOWN -> nextY++;
			case LEFT -> nextX--;
			case RIGHT -> nextX++;
			}
			if (nextX < 0 || nextX >= state.getWidth() || nextY < 0 || nextY >= state.getHeight()
					|| state.isCellOccupied(nextX, nextY)) {
				break;
			}
			distance++;
		}
		return distance;
	}

}
