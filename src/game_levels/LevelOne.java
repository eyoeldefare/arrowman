package game_levels;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import game_entities.ArrowMan;
import game_entities.Arrows;
import game_entities.Zombies;
import sprites.Background;

public class LevelOne extends GameLevels {

	private GameLevelsManager gameLevelManager;
	private ArrowMan arrowMan;
	private Zombies[] zombies;
	private Arrows[] arrows;
	private Background background;
	private int zombieCount;
	private int arrowCount;

	// Constructor
	public LevelOne(GameLevelsManager gameLevelManager) {
		// init everything here when the constructor first called
		this.background = new Background("/background/bg-1.jpg");
		this.arrowMan = new ArrowMan();
		this.arrowMan.setPosition(100, 240);
		this.zombies = new Zombies[this.zombieCount];
		this.arrows = new Arrows[this.arrowCount];

	}

	@Override
	public void init() {
	}

	@Override
	public void draw(Graphics2D graphics) {
		this.background.draw(graphics);
		this.arrowMan.draw(graphics);
	}

	@Override
	public void update() {
		this.arrowMan.update();
	}

	@Override
	public void keyPressed(int key) {
		if (key == KeyEvent.VK_A) {
			this.arrowMan.setLeft(true);
		}
		if (key == KeyEvent.VK_W) {
			this.arrowMan.setUp(true);
		}
		if (key == KeyEvent.VK_D) {
			this.arrowMan.setRight(true);
		}
		if (key == KeyEvent.VK_S) {
			this.arrowMan.setDown(true);
		}
	}

	@Override
	public void keyReleased(int key) {
		if (key == KeyEvent.VK_A) {
			this.arrowMan.setLeft(false);
		}
		if (key == KeyEvent.VK_W) {
			this.arrowMan.setUp(false);
		}
		if (key == KeyEvent.VK_D) {
			this.arrowMan.setRight(false);
		}
		if (key == KeyEvent.VK_S) {
			this.arrowMan.setDown(false);
		}
	}
}
