package game_levels;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import game_entities.Actions;
import game_entities.ArrowMan;
import game_entities.Arrows;
import game_entities.Zombie;
import sprites.Background;
import sprites.Ground;
import sprites.LivesCount;

public abstract class GameLevels {

	// Custom classes
	protected ArrowMan arrowMan;
	protected List<Zombie> zombies;
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
		Zombie zombie;
		this.gameLevelManager = gameLevelManager;

		if (bg != null)
			this.background = new Background(bg);
		if (lc != null)
			this.livesCount = new LivesCount(lc);

		this.drawer = new Ground();

		this.arrowMan = new ArrowMan();
		this.arrowMan.setPosition(0, 282);

		this.zombies = new ArrayList<Zombie>();

		Point[] points = new Point[] { new Point(480, 259), new Point(510, 259), new Point(620, 259),
				new Point(760, 259), new Point(780, 259), };

		for (int i = 0; i < points.length; i++) {
			zombie = new Zombie();
			zombie.setPosition(points[i].x, points[i].y);
			this.zombies.add(zombie);
		}

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
			// Controller
			this.arrows.setControllerReleased(true);

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
		for (int i = 0; i < this.zombies.size(); i++) {
			this.zombies.get(i).draw(graphics);
		}
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
		for (int i = 0; i < this.zombies.size(); i++) {
			this.zombies.get(i).update();
		}
		this.livesCount.update();
		this.arrows.update();

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
	protected void gameOver(int setLevel, double zombieSpeed, int arrowCount, int livesCount) {
		Zombie zombie;
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
			Point[] points = new Point[] { new Point(400, 259), new Point(450, 259), new Point(500, 259),
					new Point(550, 259), new Point(600, 259), };

			for (int i = 0; i < points.length; i++) {
				zombie = new Zombie();
				zombie.setPosition(points[i].x, points[i].y);
				this.zombies.add(zombie);
			}
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
		Rectangle rZombie;
		for (int i = 0; i < this.zombies.size(); i++) {
			rZombie = this.zombies.get(i).createRect();

			if (this.ground1.intersects(rZombie)) {
				this.zombies.get(i).setY(this.zombies.get(i).getY() - 1);
			}
			if (this.ground2.intersects(rZombie)) {
				this.zombies.get(i).setY(this.zombies.get(i).getY() - 1);
			}
			if (this.ground3.intersects(rZombie)) {
				this.zombies.get(i).setY(this.zombies.get(i).getY() - 1);
			}
			if (this.ground4.intersects(rZombie)) {
				this.zombies.get(i).setY(this.zombies.get(i).getY() - 1);
			}
		}

	}

	// the zombie and arrowman made interaction
	protected void zombieXArrowman() {
		Rectangle rZombie;
		Rectangle rArrowman = this.arrowMan.createRect();
		for (int i = 0; i < this.zombies.size(); i++) {
			rZombie = this.zombies.get(i).createRect();
			if (rArrowman.intersects(rZombie)) {
				this.zombies.get(i).setAction(Actions.ATTACKING);
			} else {
				this.arrowMan.setBeingAttacked();
				this.livesCount.setAttacked(true);
			}
		}

	}

	// arrowman and the zombie made interaction

	protected void zombieXArrow() {
		// Create a rect for both the zombie and arrow
		Rectangle rZombie;
		Rectangle rArrow = this.arrows.createRect(this.arrows.getArrowX(), this.arrows.getArrowY());
		Rectangle rArrowman = this.arrowMan.createRect();

		for (int i = 0; i < this.zombies.size(); i++) {
			rZombie = this.zombies.get(i).createRect();
			double difference = rZombie.getX() - rArrowman.getX();
			if (rArrow.intersects(rZombie) & difference > 63) {

				/*
				 * kill the zombie
				 */
				this.arrows.resetCoordinates();
				this.zombies.get(i).setAction(Actions.DYING);
			}

		}

	}

	// the arrowman shall not pass the zombie
	protected void playerShallNotPass() {
		Rectangle rZombie;
		double arrowmanXDirection = this.arrowMan.getX();
		for (int i = 0; i < this.zombies.size(); i++) {
			rZombie = this.zombies.get(i).createRect();
			double zombieXDirection = this.zombies.get(i).getX();
			if (arrowmanXDirection >= zombieXDirection) {
				this.arrowMan.setX(arrowmanXDirection - 1);
			}
		}
	}

	protected void setZombieSpeed(double speed) {
		for (int i = 0; i < this.zombies.size(); i++) {
			this.zombies.get(i).setSpeed(speed);
		}

	}

}
