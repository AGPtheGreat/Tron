package Players;
import java.awt.event.KeyEvent;
import java.util.Map;

import Game.Direction;
import Game.GameState;
import Game.InputHandler;

public class HumanPlayer extends Player {
    private Map<Integer, Direction> keyBindings;
    private InputHandler input;

    public HumanPlayer(String name, int startX, int startY, Direction startDir,
                       Map<Integer, Direction> keyBindings, InputHandler input) {
        super(name, startX, startY, startDir);
        this.keyBindings = keyBindings;
        this.input = input;
    }

    @Override
    public void decideDirection(GameState state) {
        // Get the last key pressed from InputHandler
        int lastKey = input.getLastKeyPressed();

        if (keyBindings.containsKey(lastKey)) {
            Direction newDir = keyBindings.get(lastKey);

            // Prevent reversing into your own trail
            if (!isOppositeDirection(newDir, direction)) {
                direction = newDir;
            }
        }
    }

    private boolean isOppositeDirection(Direction d1, Direction d2) {
        return (d1 == Direction.UP && d2 == Direction.DOWN)
            || (d1 == Direction.DOWN && d2 == Direction.UP)
            || (d1 == Direction.LEFT && d2 == Direction.RIGHT)
            || (d1 == Direction.RIGHT && d2 == Direction.LEFT);
    }
}
