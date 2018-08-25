package sprites;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

// This class will help us mimic the player and zombie objects
// are walking on a ground in the background image

public class Drawer {
	private Line2D line;
	private Line2D line1;
	private Line2D line2;

	public Drawer() {
		this.line = new Line2D.Double(0, 350, 350, 360);
		this.line1 = new Line2D.Double(350, 360, 550, 310);
		this.line2 = new Line2D.Double(550, 310, 800, 345);
	}

	public void draw(Graphics2D graphics) {

		graphics.setColor(Color.white);
		graphics.draw(line);
		graphics.draw(line1);
		graphics.draw(line2);

	}

	public void update() {

	}

	// Getters for the lines
	public Line2D getLine() {
		return line;
	}

	public Line2D getLine1() {
		return line1;
	}

	public Line2D getLine2() {
		return line2;
	}
}
