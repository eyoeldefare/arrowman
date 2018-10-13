package game_levels;

import java.awt.Graphics2D;
import java.awt.Point;

/*
 * Note that this class will be used to manage the levels we will build going forward.
 * Every level will be stored in some kind of collection and this class will determine 
 * which level shall be displayed based on the situation.
 * */
public class GameLevelsManager {

	private int level;
	private GameLevels[] levels;
	private boolean isDead;

	// Constructor
	public GameLevelsManager() {
		// The levels will store all the game levels
		this.levels = new GameLevels[5];
		// Add all the levels
		this.levels[0] = new LevelZero(this);
		this.levels[1] = new LevelOne(this);
		this.levels[2] = new LevelTwo(this);
		this.levels[3] = new LevelThree(this);
		this.levels[4] = new LevelFour(this);

	}

	// We will need a way to set the level eg. when finishing a level

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	// We are just init, drawing, updating, and handling the keyboard
	// pressing events based on the level we are on.
	// Note the level is being set from other classes that will
	// handle whether we need to move to the next level or not.
	public void init() {
		for (int i = 0; i < this.levels.length; i++) {
			if (i == this.getLevel()) {
				this.levels[this.getLevel()].init();
			}
		}
	}

	public void draw(Graphics2D graphics) {
		for (int i = 0; i < this.levels.length; i++) {
			if (i == this.getLevel()) {
				this.levels[this.getLevel()].draw(graphics);
			}
		}
	}

	public void update() {
		this.isDead = false;
		for (int i = 0; i < this.levels.length; i++) {
			if (i == this.getLevel()) {
				this.levels[this.getLevel()].update();
			}
		}

		// For now we will use this as a game over
		if (this.isDead) {
			this.levels = new GameLevels[5];
			
			this.levels[0] = new LevelZero(this);
			this.levels[1] = new LevelOne(this);
			this.levels[2] = new LevelTwo(this);
			this.levels[3] = new LevelThree(this);
			this.levels[4] = new LevelFour(this);

		}

	}

	public void keyPressed(int key) {
		for (int i = 0; i < this.levels.length; i++) {
			if (i == this.getLevel()) {
				this.levels[this.getLevel()].keyPressed(key);
			}
		}
	}

	public void keyReleased(int key) {
		for (int i = 0; i < this.levels.length; i++) {
			if (i == this.getLevel()) {
				this.levels[this.getLevel()].keyReleased(key);
			}
		}
	}

	// Mouse
	public void mousePressed(int mouse, Point coordinates) {
		for (int i = 0; i < this.levels.length; i++) {
			if (i == this.getLevel()) {
				this.levels[this.getLevel()].mousePressed(mouse, coordinates);
			}
		}
	}

	public void mouseReleased(int mouse) {
		for (int i = 0; i < this.levels.length; i++) {
			if (i == this.getLevel()) {
				this.levels[this.getLevel()].mouseReleased(mouse);
			}
		}
	}

	public void mouseDragged(int mouse, Point coordinates) {
		for (int i = 0; i < this.levels.length; i++) {
			if (i == this.getLevel()) {
				this.levels[this.getLevel()].mouseDragged(mouse, coordinates);
			}
		}
	}

	// setter and getter
	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

}
