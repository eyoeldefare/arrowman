package game_levels;

import java.awt.Graphics2D;

/*
 * Note that this class will be used to manage the levels we will build going forward.
 * Every level will be stored in some kind of collection and this class will determine 
 * which level shall be displayed based on the situation.
 * */
public class GameLevelsManager extends GameLevels {

	private int level;
	private GameLevels[] levels;

	// Constructor
	public GameLevelsManager() {
		// The levels will store all the game levels
		this.levels = new GameLevels[4];
		// Every level will start from the menu
		this.level = 0;

		// Add all the levels
		this.levels[0] = new LevelZero();
		this.levels[1] = new LevelOne();
		this.levels[2] = new LevelTwo();
		this.levels[3] = new LevelThree();
	}

	// We will need a way to set the level eg. when finishing a level
	public void setLevel(int level) {
		this.level = level;
	}
	
	//We are just init, drawing, updating, and handling the keyboard 
	//pressing events based on the level we are on.
	//Note the level is being set from other classes that will
	// handle whether we need to move to the next level or not.
	@Override
	public void init() {
		for (int i = 0; i < this.levels.length; i++) {
			if (i == this.level) {
				this.levels[this.level].init();
			}
		}
	}

	@Override
	public void draw(Graphics2D graphics) {
		for (int i = 0; i < this.levels.length; i++) {
			if (i == this.level) {
				this.levels[this.level].draw(graphics);
			}
		}
	}

	@Override
	public void update() {
		for (int i = 0; i < this.levels.length; i++) {
			if (i == this.level) {
				this.levels[this.level].update();
			}
		}
	}

	@Override
	public void keyPressed(int key) {
		for (int i = 0; i < this.levels.length; i++) {
			if (i == this.level) {
				this.levels[this.level].keyPressed(key);
			}
		}
	}

	@Override
	public void keyReleased(int key) {
		for (int i = 0; i < this.levels.length; i++) {
			if (i == this.level) {
				this.levels[this.level].keyReleased(key);
			}
		}
	}

}
