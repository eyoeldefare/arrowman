package game_entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import entry.Panel;

/*
	This is our abstract super class that will be inherited by all entities 
*/
public abstract class Entities {

	// props
	protected double x, y, dx, dy;
	protected double startX, startY, endX, endY;
	protected boolean dead, dragging, dragged;
	protected int lives;
	protected int collisionWidth, collisionHeight;
	protected double angle;
	protected boolean okToFire;

	// Constructor
	protected Entities() {
		this.okToFire = true;
	}

	// We will implement these abstract methods in each entity classes
	public abstract void init();

	public abstract void draw(Graphics2D graphics);

	public abstract void update();

	// set position
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
		this.dy = .2;
	}

	// set vector, we mainly will be working in the x direction in this game as it
	// doesn't seem needed to include y vector for this particular game.
	public void setSpeed(double dx) {
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

	// Create Rectangle for Collision
	public Rectangle createRect() {
		return new Rectangle((int) this.x, (int) this.y, this.collisionWidth, this.collisionHeight);
	}

	// This createRect is made so we can visualize the white lines surrounding the
	// arrow

	public Rectangle createRect(int x, int y) {
		return new Rectangle(x, y, this.collisionWidth, this.collisionHeight);
	}

	protected void getAngle() {
		// Trying to calculate the angle using the values from dragging
		// Formula we will implement is:
		// Theta = 2*arctan((y2-y1)/x2-x1+(sqrt((x2-x1)^2+(y2-y1)^2)));

		double _x = Math.pow((this.startX - this.endX), 2);
		double _y = Math.pow((this.startY - this.endY), 2);
		double angle = 2 * (Math.atan((this.startY - this.endY) / (this.startX - this.endX + Math.sqrt(_x + _y))));

		if (angle >= -.95 && angle <= 0) {
			this.angle = 2 * (Math.atan((this.startY - this.endY) / (this.startX - this.endX + Math.sqrt(_x + _y))));
		}
 
	}

	// getters
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

	public void setStartX(double startX) {
		this.startX = startX;
	}

	public void setStartY(double startY) {
		this.startY = startY;
	}

	public void setEndX(double endX) {
		this.endX = endX;
	}

	public void setEndY(double endY) {
		this.endY = endY;
	}

	public void setDragging(boolean dragging) {
		this.dragging = dragging;
	}

	public void setOkToFire(boolean okToFire) {
		this.okToFire = okToFire;
	}

	public void setDragged(boolean dragged) {
		this.dragged = dragged;
	}
}
