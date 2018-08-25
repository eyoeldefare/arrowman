package game_entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import entry.Panel;

public abstract class Entities {

	protected double x, y, dx, dy;
	protected int collisionWidth, collisionHeight;

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

	// calculate the bounds for entities
	protected void calcBounds() {
		int windowWidth = Panel.WIDTH;
		if (this.x > windowWidth - 100)
			this.x = windowWidth - 100;
		if (this.x < 20)
			this.x = 20;
	}

	// Rectangle or Ellipse2D for collision
	public Rectangle createRect() {
		return new Rectangle((int) this.x, (int) this.y, this.collisionWidth, this.collisionHeight); // x, y, width, //
																										// // // height
	}

	// Getters
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getDx() {
		return dx;
	}

	public double getDy() {
		return dy;
	}

	// Setters
	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

}
