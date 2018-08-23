package game_entities;

import java.awt.Graphics2D;

import entry.Panel;

public abstract class Entities {

	protected double x, y, dx;

	// Constructor
	protected Entities() {
	}

	protected abstract void init();

	protected abstract void draw(Graphics2D graphics);

	protected abstract void update();

	// set position
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// set vector
	protected void setVector(double dx, double dy) {
		this.dx = dx;
	}

	// calc the bounds play can move
	protected void calcBounds() {
		int windowWidth = Panel.WIDTH;
		int windowHeight = Panel.HEIGHT;
		if (this.x > windowWidth)
			this.x = windowWidth - 50;
		if (this.x < 20)
			this.x = 20;
	}

}
