package sprites;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

// This class will help us mimic the player and zombie objects
// are walking on a ground in the background image

public class Ground {
	private Line2D line1;
	private Line2D line2;
	private Line2D line3;
	private Line2D line4;
	Color cLine = new Color(0, 0, 0, 0);

	// constructor
	public Ground() {
		/*
		 * These lines just represent a ground. They are an attempt to draw on the
		 * ground. If you want to see what the lines look like, change the this.cLine =
		 * Color.white or some visiable color, which will show you what the ground looks
		 * like.
		 */
		this.line1 = new Line2D.Double(0, 350, 350, 353);
		this.line2 = new Line2D.Double(350, 363, 550, 310);
		this.line3 = new Line2D.Double(550, 307, 708, 320);
		this.line4 = new Line2D.Double(710, 324, 800, 350);
	}

	public void draw(Graphics2D graphics) {

		graphics.setColor(this.cLine);
		graphics.draw(line1);
		graphics.draw(line2);
		graphics.draw(line3);
		graphics.draw(line4);

	}
	
	// Getters for the lines
	public Line2D getLine1() {
		return line1;
	}

	public Line2D getLine2() {
		return line2;
	}

	public Line2D getLine3() {
		return line3;
	}

	public Line2D getLine4() {
		return line4;
	}
}
