package game_levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import sprites.Background;

public class LevelZero extends GameLevels {

	// We will need one or two form of the background in every
	// level going forward.
	private Background background;
	// This will represent the option of the current option
	// which will be to START the game or EXIT the game
	private int option;
	// Here are the choices
	private String[] options = { "Start", "Exit" };

	// There are basically to write dome description and title of
	// the game when it begins
	private Color tColor;
	private Font tFont, dFont;

	// Custome class instances
	private GameLevelsManager gameLevelManager;

	// Constructor
	public LevelZero() {

		try {
			
			this.background = new Background("/background/bg-0.jpg");
			this.tColor = new Color(123, 0, 0);
			this.tFont = new Font("Arial", Font.PLAIN, 34);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//We will set the background seperate from the other levels
	//because, the background is not part of the levels.
	//
	@Override
	public void init() {
	}

	@Override
	public void draw(Graphics2D graphics) {
		this.background.draw(graphics);
	}

	@Override
	public void update() {

	}

	@Override
	public void keyPressed(int key) {

	}

	@Override
	public void keyReleased(int key) {

	}

}
