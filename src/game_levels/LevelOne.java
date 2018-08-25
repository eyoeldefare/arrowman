package game_levels;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;

import game_entities.ArrowMan;
import game_entities.Arrows;
import game_entities.Zombies;
import sprites.Background;
import sprites.Ground;

public class LevelOne extends GameLevels {

	private ArrowMan arrowMan;
	private Zombies[] zombies;
	private Arrows[] arrows;
	private Background background;
	private Ground drawer;
	private int zombieCount;
	private int arrowCount;

	// Constructor
	public LevelOne(GameLevelsManager gameLevelManager) {
		// init everything here when the constructor first called

		this.background = new Background("/background/bg-1.jpg");

		this.drawer = new Ground();

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
		Rectangle rArrowman = this.arrowMan.createRect();
		Line2D ground1 = this.drawer.getLine1();
		Line2D ground2 = this.drawer.getLine2();
		Line2D ground3 = this.drawer.getLine3();
		Line2D ground4 = this.drawer.getLine4();
		// check if the player and the ground made contact
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
