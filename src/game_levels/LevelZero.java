package game_levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import entry.Panel;

/*This class will be responsible for creating the menu which we 
 * count as the level since they have so much similarity*/
public class LevelZero extends GameLevels {
	// This will represent the option of the current option
	// which will be to START the game or EXIT the game
	private int option;
	// Here are the choices
	private String[] options = { "Start", "Exit" };

	// There are basically to write dome description and title of
	// the game when it begins
	private Color tColor;
	private Font tFont, dFont;

	// Constructor
	public LevelZero(GameLevelsManager gameLevelManager) {
		super(gameLevelManager, "/background/bg-0.jpg", null, 0, 0);

		// When we instantiate LevelZero in GameLevelsManager, the GameLevelsManager
		// constructor will be passed into
		// LevelZero when its first created in which case we can use here to access its
		// methods. The benefit of doing
		// this is, we create our Levels with the current state of Level Manager.

		super.gameLevelManager = gameLevelManager;
		try {
			// bg
			super.background.setDx(-0.5);
			this.tColor = new Color(222, 243, 249);
			this.tFont = new Font("Monospaced", Font.BOLD, 30);
			this.dFont = new Font("Monospaced", Font.PLAIN, 16);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// We will set the background separate from the other levels
	// because, the background is not part of the levels as it most likely appear
	// all the time.
	//
	@Override
	public void init() {
	}

	@Override
	public void draw(Graphics2D graphics) {
		// Draw the background on panel
		super.background.draw(graphics);

		// Draw the title on the panel
		graphics.setColor(this.tColor);
		graphics.setFont(this.tFont);
		graphics.drawString("Man vs Zombies", Panel.WIDTH / 2 - 120, (int) (Panel.HEIGHT * 0.08));

		// Draw option selection
		graphics.setFont(this.dFont);
		for (int i = 0; i < this.options.length; i++) {
			if (i == this.option) {
				graphics.setColor(Color.blue);
			} else {
				graphics.setColor(Color.black);
			}
			graphics.drawString(this.options[i], Panel.WIDTH / 2 - 30, (int) (Panel.HEIGHT * 0.4) + i * 15);
		}
	}

	@Override
	public void update() {
		super.background.update();
	}

	@Override
	public void keyReleased(int key) {

	}

	@Override
	public void keyPressed(int key) {
		if (key == KeyEvent.VK_ENTER)
			// If pressed enter, we will manage the level selection
			this.optionSelect();
		if (key == KeyEvent.VK_UP) {
			// We will make sure the option is within bound of the array
			// options

			// We are going upward in the array, which is toward the index 0;
			this.option--;
			// Make sure we don't get a negative array which will cause out-of-bound
			// exception
			if (this.option < 0) {
				// Note that we are setting it to the length of the array so it can go
				// back to the bottom option
				this.option = this.options.length - 1;
			}

		}
		if (key == KeyEvent.VK_DOWN) {
			// The same logic is going on here, except we are going upward and
			// the array index is increasing
			this.option++;

			if (this.option > this.options.length - 1) {
				this.option = 0;
			}
		}
	}

	// We will handle the options logic here. It will either start
	// the game or exit the game
	private void optionSelect() {
		if (this.option == 0)
			// If the option is 0, it means we selected the option
			// "START" in our array, options. So we set the level
			// to 1 in our gamelevelManager so level 1 starts.
			super.gameLevelManager.setLevel(1);

		if (this.option == 1)
			// If the person selects the second option, it means he wants to exist
			// So we exit him from the game
			System.exit(0);

	}

}
