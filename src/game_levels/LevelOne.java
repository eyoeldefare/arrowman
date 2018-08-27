package game_levels;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import game_entities.ArrowMan;
import game_entities.Zombie;
import sprites.Background;
import sprites.Ground;

public class LevelOne extends GameLevels {

	private ArrowMan arrowMan;
	private Zombie zombie;
	private Background background;
	private Ground drawer;
	
	// Constructor
	public LevelOne(GameLevelsManager gameLevelManager) {
		// init everything here when the constructor first called

		this.background = new Background("/background/bg-1.jpg");

		this.drawer = new Ground();

		this.arrowMan = new ArrowMan();
		this.arrowMan.setPosition(0, 282);

		this.zombie = new Zombie();
		this.zombie.setPosition(650, 259); 
	}

	@Override
	public void init() {
	}

	// Drawing the listed objects
	@Override
	public void draw(Graphics2D graphics) {
		this.background.draw(graphics);
		this.drawer.draw(graphics);
		this.arrowMan.draw(graphics);
		this.zombie.draw(graphics);
	}

	// Updating Objects
	@Override
	public void update() {
		this.arrowMan.update();

		// Referencing rectangle object we defined in arrowman - this rectangle
		// encapsulated the arrowman bounds
		Rectangle rArrowman = this.arrowMan.createRect();
		// Here we are referencing the lines we draw on the ground to mimic a ground in
		// Ground class.
		Line2D ground1 = this.drawer.getLine1();
		Line2D ground2 = this.drawer.getLine2();
		Line2D ground3 = this.drawer.getLine3();
		Line2D ground4 = this.drawer.getLine4();
		// check if the player and the ground made contact
		// and set the arrowman's y position to where the contact we made
		// Do it for 4 of the grounds we built

		if (ground1.intersects(rArrowman)) {
			this.arrowMan.setY(this.arrowMan.getY() - 1);
		}
		if (ground2.intersects(rArrowman)) {
			this.arrowMan.setY(this.arrowMan.getY() - 1);
		}
		if (ground3.intersects(rArrowman)) {
			this.arrowMan.setY(this.arrowMan.getY() - 1);
		}
		if (ground4.intersects(rArrowman)) {
			this.arrowMan.setY(this.arrowMan.getY() - 1);

		}

		// Zombie stuff
		this.zombie.update();

	}

	// Key Events checker
	@Override
	public void keyPressed(int key) {
		if (key == KeyEvent.VK_A) {
			this.arrowMan.setLeft(true);
		}
		if (key == KeyEvent.VK_D) {
			this.arrowMan.setRight(true);
		}

	}

	@Override
	public void keyReleased(int key) {
		if (key == KeyEvent.VK_A) {
			this.arrowMan.setLeft(false);
		}

		if (key == KeyEvent.VK_D) {
			this.arrowMan.setRight(false);
		}

	}

}
