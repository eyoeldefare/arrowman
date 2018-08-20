package game_levels;

import java.awt.Graphics2D;

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
	public LevelOne() {
		// init everything here when the constructor first called
		this.background = new Background("/background/bg-1.jpg");
		this.arrowMan = new ArrowMan();
		this.zombies = new Zombies[this.zombieCount];
		this.arrows = new Arrows[this.arrowCount];
	}

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
