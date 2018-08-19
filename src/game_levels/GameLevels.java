package game_levels;

import java.awt.Graphics2D;

public abstract class GameLevels {

	public abstract void init();

	public abstract void draw(Graphics2D graphics);

	public abstract void update();

	public abstract void keyPressed(int key);

	public abstract void keyReleased(int key);
}
