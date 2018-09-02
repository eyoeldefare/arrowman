package game_levels;

import java.awt.Graphics2D;

//We will only define level specific properties and methods in each level and let the 
//super class GameLevels handle every other common thing levels share
public class LevelOne extends GameLevels {

	// Constructor
	public LevelOne(GameLevelsManager gameLevelManager) {
		super(gameLevelManager, "/background/bg-1.jpg", "/standalones/d_heart.gif", "/standalones/d_arrow.png");
		super.zombie.setSpeed(-.5);
	}

	@Override
	public void init() {}

	@Override
	public void draw(Graphics2D graphics) {
		super.draw(graphics);
	}

	@Override
	public void update() {
		super.update();
		super.gameOver(0);
	}

}
