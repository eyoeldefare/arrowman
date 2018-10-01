package game_levels;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

import game_entities.ArrowMan;
import game_entities.Arrows;
import game_entities.Zombies;
import sprites.Background;
import sprites.Ground;
import sprites.LivesCount;

public abstract class GameLevels {

	// Custom classes
	protected ArrowMan arrowMan;
	protected Zombies zombies;
	protected Background background;
	protected Ground drawer;
	protected LivesCount livesCount;
	protected GameLevelsManager gameLevelManager;
	protected Arrows arrows;

	// The 4 Grounds each Levels will have
	protected Line2D ground1;
	protected Line2D ground2;
	protected Line2D ground3;
	protected Line2D ground4;

	protected GameLevels(GameLevelsManager gameLevelManager, String bg, String lc, int arrowCount) {
		this.gameLevelManager = gameLevelManager;

		if (bg != null)
			this.background = new Background(bg);
		if (lc != null)
			this.livesCount = new LivesCount(lc);

		this.drawer = new Ground();

		this.arrowMan = new ArrowMan();
		this.arrowMan.setPosition(0, 282);

		this.zombies = new Zombies();

		// bow and arrow
		this.arrows = new Arrows(arrowCount);

	}

	// abstracts
	protected abstract void init();

	// Key Events
	public void keyPressed(int key) {
		if (key == KeyEvent.VK_A) {
			this.arrowMan.setLeft(true);
		}
		if (key == KeyEvent.VK_D) {
			this.arrowMan.setRight(true);
		}
	}

	public void keyReleased(int key) {
		if (key == KeyEvent.VK_A) {
			this.arrowMan.setLeft(false);
		}

		if (key == KeyEvent.VK_D) {
			this.arrowMan.setRight(false);
		}
	}

	// Mouse Events
	public void mousePressed(int mouse, Point coordinates) {
		if (mouse == MouseEvent.MOUSE_PRESSED) {

			this.arrowMan.setStartX(coordinates.getX());
			this.arrowMan.setStartY(coordinates.getY());
			this.arrowMan.setDragging(true);

			this.arrows.setStartX(coordinates.getX());
			this.arrows.setStartY(coordinates.getY());
			this.arrows.setDragging(true);
		}
	}

	public void mouseReleased(int mouse) {
		if (mouse == MouseEvent.MOUSE_RELEASED) {

			this.arrowMan.setDragging(false);
			this.arrows.setDragging(false);
			// already been dragged
			this.arrows.setDragged(true);
		}
	}

	public void mouseDragged(int mouse, Point coordinates) {
		if (mouse == MouseEvent.MOUSE_DRAGGED) {
			this.arrowMan.setEndX(coordinates.getX());
			this.arrowMan.setEndY(coordinates.getY());

			this.arrows.setEndX(coordinates.getX());
			this.arrows.setEndY(coordinates.getY());
		}
	}

	// Drawing the listed objects
	protected void draw(Graphics2D graphics) {
		this.background.draw(graphics);
		this.drawer.draw(graphics);
		this.arrowMan.draw(graphics);
		this.arrows.draw(graphics);

		this.zombies.draw(graphics);

		this.livesCount.draw(graphics);

	}

	// Every level will update their level according to this super class

	protected void update() {
		// Get the reference for ground objects (which are lines) from the drawer class
		// So we can check their collision with the rectangle around the arrowman and
		// zombie.
		this.ground1 = this.drawer.getLine1();
		this.ground2 = this.drawer.getLine2();
		this.ground3 = this.drawer.getLine3();
		this.ground4 = this.drawer.getLine4();

		// Update
		this.arrowMan.update();
		this.livesCount.update();
		this.arrows.update();
		this.zombies.update();


		// Collisions
		this.arrowmanXGround();
		this.zombieXGround();
		this.zombieXArrowman();
		this.zombieXArrow();

		// Arrowman shall not pass
		this.playerShallNotPass();

		// Bow and arrows stuff
		this.arrows.setX(this.arrowMan.getX() + 30);
		this.arrows.setY(this.arrowMan.getY() + 17);

	}

	// Arrowman must have died - game over
	public void gameOver(int setLevel, double zombieSpeed, int arrowCount, int livesCount) {
		if (this.livesCount.isDead()) {
			try {
				this.livesCount = new LivesCount("/standalones/d_heart.gif");
				this.arrowMan = new ArrowMan();
				this.arrows = new Arrows(arrowCount);
			} catch (Exception ex) {
				ex.printStackTrace();
			} 

			this.livesCount.setLivesCount(livesCount);
			this.arrowMan.setPosition(0, 282);
			this.zombies.gameOver(zombieSpeed);
			this.arrows.setOkToFire(true);
			this.arrowMan.setOkToFire(true);
			this.gameLevelManager.setLevel(setLevel);

		}
	}

	// The arrowman made contact with the ground due to gravity

	protected void arrowmanXGround() {

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

	// The zombie made contact with the ground due to gravity
	protected void zombieXGround() {
		this.zombies.zombieXGround(ground1, ground2, ground3, ground4);

	}

	// the zombie and arrowman made interaction
	protected void zombieXArrowman() {
		this.zombies.zombieXArrowman(arrowMan, livesCount);

	}

	// arrowman and the zombie made interaction

	protected void zombieXArrow() {
		// Create a rect for both the zombie and arrow
		this.zombies.zombieXArrow(arrows, arrowMan);

	}

	// the arrowman shall not pass the zombie
	protected void playerShallNotPass() {
		this.zombies.playerShallNotPassZombie(arrowMan);
	}

	protected void setZombieSpeed(double speed) {
		this.zombies.setZombieSpeed(speed);
	}

}
