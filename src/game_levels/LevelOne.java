package game_levels;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;

import game_entities.ArrowMan;
import game_entities.Arrows;
import game_entities.Zombies;
import sprites.Background;
import sprites.Drawer;

public class LevelOne extends GameLevels {

	private GameLevelsManager gameLevelManager;
	private ArrowMan arrowMan;
	private Zombies[] zombies;
	private Arrows[] arrows;
	private Background background;
	private Drawer drawer;
	private int zombieCount;
	private int arrowCount;

	// Constructor
	public LevelOne(GameLevelsManager gameLevelManager) {
		// init everything here when the constructor first called

		this.background = new Background("/background/bg-1.jpg");

		this.drawer = new Drawer();

		this.arrowMan = new ArrowMan();
		this.arrowMan.setPosition(500, 200);

		this.zombies = new Zombies[this.zombieCount];

		this.arrows = new Arrows[this.arrowCount];

	}

	@Override
	public void init() {
	}

	@Override
	public void draw(Graphics2D graphics) {
		this.background.draw(graphics);
		this.drawer.draw(graphics);
		this.arrowMan.draw(graphics);
	}

	@Override
	public void update() {
		this.arrowMan.update();
		Rectangle arrowman = this.arrowMan.createRect();
		Line2D ground1 = this.drawer.getLine();
		Line2D ground2 = this.drawer.getLine1();
		Line2D ground3 = this.drawer.getLine2();

		// check if the player and the ground made contact
		if (ground1.intersects(arrowman)) {
			double line1 = 1 / 35 * ((ground1.getX1() + ground1.getX2()) / 2) + 295;
			this.arrowMan.setY(line1);
		}
		if (ground2.intersects(arrowman)) {
			double line2 = -.25 * ((ground2.getX1() + ground2.getX2()) / 2) + 382;
			this.arrowMan.setY(line2);
		}
		if (ground3.intersects(arrowman)) {
			double line3 = (7 / 50) * ((ground3.getX1() + ground3.getX2()) / 2) + 244;
			this.arrowMan.setY(line3);
		}

	}

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
