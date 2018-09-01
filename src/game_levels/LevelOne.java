package game_levels;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;

import game_entities.Actions;
import game_entities.ArrowMan;
import game_entities.Zombie;
import sprites.Background;
import sprites.Ground;

public class LevelOne extends GameLevels {

	private ArrowMan arrowMan;
	private Zombie zombie;
	private Background background;
	private Ground drawer;

	// Ground stuff
	Line2D ground1;
	Line2D ground2;
	Line2D ground3;
	Line2D ground4;

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
		// Get the reference for ground objects (which are lines) from the drawer class
		// So we can check their collision with the rectangle around the arrowman and
		// zombie.
		this.ground1 = this.drawer.getLine1();
		this.ground2 = this.drawer.getLine2();
		this.ground3 = this.drawer.getLine3();
		this.ground4 = this.drawer.getLine4();
		// Update
		this.arrowMan.update();
		this.zombie.update();

		// Collisions
		this.arrowmanXGround();
		this.zombieXGround();
		this.zombieXArrowman();

		// Arrowman shall not pass
		this.playerShallNotPass();

	}

	// Local logic

	private void arrowmanXGround() {

		Rectangle rArrowman = this.arrowMan.createRect();

		if (this.ground1.intersects(rArrowman)) {
			this.arrowMan.setY(this.arrowMan.getY() - 1);
		}
		if (this.ground2.intersects(rArrowman)) {
			this.arrowMan.setY(this.arrowMan.getY() - 1);
		}
		if (this.ground3.intersects(rArrowman)) {
			this.arrowMan.setY(this.arrowMan.getY() - 1);
		}
		if (this.ground4.intersects(rArrowman)) {
			this.arrowMan.setY(this.arrowMan.getY() - 1);
		}
	}

	private void zombieXGround() {

		Rectangle rZombie = this.zombie.createRect();

		if (this.ground1.intersects(rZombie)) {
			this.zombie.setY(this.zombie.getY() - 1);
		}
		if (this.ground2.intersects(rZombie)) {
			this.zombie.setY(this.zombie.getY() - 1);

		}
		if (this.ground3.intersects(rZombie)) {
			this.zombie.setY(this.zombie.getY() - 1);

		}
		if (this.ground4.intersects(rZombie)) {
			this.zombie.setY(this.zombie.getY() - 1);

		}
	}

	private void zombieXArrowman() {
		// the zombie and arrowman made interaction
		Rectangle rArrowman = this.arrowMan.createRect();
		Rectangle rZombie = this.zombie.createRect();

		if (rArrowman.intersects(rZombie)) {
			this.zombie.setAction(Actions.ATTACKING);
		} else
			this.arrowMan.setBeingAttacked();
	}

	private void playerShallNotPass() {
		// the arrowman shall not pass the zombie
		double arrowmanXDirection = this.arrowMan.getX();
		double zombieXDirection = this.zombie.getX();

		if (arrowmanXDirection >= zombieXDirection) {
			this.arrowMan.setX(arrowmanXDirection - 1);
		}
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
